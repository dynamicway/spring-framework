package me.spring.jpa.lockmode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OptimisticForceIncrementTest {

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
    void change_the_version_when_just_only_select() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Optimistic optimistic = entityManager.find(Optimistic.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        long versionBeforeSelect = optimistic.version;
        entityManager.getTransaction().commit();
        entityManager.close();
        assertThat(optimistic.version).isNotEqualTo(versionBeforeSelect);
    }

}
