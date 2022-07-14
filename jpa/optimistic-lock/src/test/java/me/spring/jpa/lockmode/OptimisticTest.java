package me.spring.jpa.lockmode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Rollback
public class OptimisticTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    @Rollback(value = false)
    void setUp() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Optimistic("optimistic"));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Entity
    public static class Optimistic {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private final Long id;

        private String name;

        @Version
        private long version;

        public Optimistic(String name) {
            this.id = null;
            this.name = name;
        }

        public Optimistic() {
            this.id = null;
        }
    }

    @Test
    void change_the_version_if_entity_updated() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Optimistic optimistic = entityManager.find(Optimistic.class, 1L);
        optimistic.name = "changeName";
        entityManager.flush();
        entityManager.close();
        assertThat(optimistic.version).isEqualTo(1);
    }

}
