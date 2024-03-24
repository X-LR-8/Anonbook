package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Database {
    private static Database instance;
    public static Database getInstance(){
        if(instance == null){
            instance=new Database();
        }
        return instance;
    }
    public Database(){
    }
    public List<Post> read() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persis");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.select(root);
        TypedQuery<Post> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Post> list = typedQuery.getResultList();
        return list;
    }

    public void write(Post post) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persis");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
    public void update(int id, String comment) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persis");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Post post = entityManager.find(Post.class, id);
        if (post != null) {
            String newcomment=post.getComments()+'\n'+comment;
            post.setComments(newcomment);
            entityManager.merge(post);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
}
