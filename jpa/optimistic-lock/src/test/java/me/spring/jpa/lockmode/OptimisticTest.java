package me.spring.jpa.lockmode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OptimisticTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private final long id = 1L;

    @BeforeEach
    void setUp() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Optimistic(id, "optimistic"));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @AfterEach
    void truncate() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table optimistic").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    void change_the_version_if_entity_updated() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Optimistic optimistic = entityManager.find(Optimistic.class, id);
        long versionBeforeUpdate = optimistic.version;
        optimistic.name = "changeName";
        entityManager.getTransaction().commit();
        entityManager.close();
        assertThat(optimistic.version).isNotEqualTo(versionBeforeUpdate);
    }

    @Test
    void not_change_the_version_if_just_only_select() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Optimistic optimistic = entityManager.find(Optimistic.class, id);
        long version = optimistic.version;
        entityManager.flush();
        entityManager.close();
        assertThat(optimistic.version).isEqualTo(version);
    }

    @Test
    void when_lock_conflicts_in_entities_then_throw_OptimisticLockException() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList<Throwable> throwables = new ArrayList<>();
        Runnable runnable = () -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Optimistic optimistic = entityManager.find(Optimistic.class, id);
            optimistic.name = Thread.currentThread().getName();
            sleep(100);
            try {
                entityManager.getTransaction().commit();
            } catch (Throwable e) {
                throwables.add(e);
            }
            entityManager.close();
            countDownLatch.countDown();
        };
        for (int i = 0; i < 2; i++) {
            executorService.execute(runnable);
        }
        countDownLatch.await();
        assertThat(throwables.size()).isOne();
        assertThat(throwables.get(0)).isInstanceOf(RollbackException.class);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
