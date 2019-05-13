package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class dbFunctions {

    private static EntityManagerFactory emf;
    private static EntityManager em;


    /**
     * Database connection initializer.
     * Creates the persistence database connection.
     * */
    public static void DBInit(){
        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();
    }

    /**
     * Database connection closer.
     * Gets called by a stage.setOnCloseRequest, closes the database connection.
     * */
    public static void Dispose(){
        em.close();
        emf.close();
    }

    /**
     * Authentication sequence.
     * Tries to select data by given parameters.
     * */
    public static tbl_user loginUser(String username, String password) throws NoResultException {

        TypedQuery<tbl_user> loginQuery = em.createQuery(
                "SELECT tu FROM tbl_user tu WHERE tu.username ='" + username + "'AND tu.password='" + password + "'", tbl_user.class);

        System.out.println(loginQuery.getSingleResult());


        return null;
    }

    /**
     * Registration sequence.
     * Initialises a new transaction, and creates a new account with the given parameters.
     * */
    public static void registerUser(String username, String password) {

        tbl_user newUser = new tbl_user(username, password);
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    /**
     * Initialises a new transaction, saves the achieved score.
     * */
    public static void saveScore(String username, int score){

        tbl_score newScore = new tbl_score(username, score);
        try {
            em.getTransaction().begin();
            em.persist(newScore);
            em.getTransaction().commit();
            System.out.println("score saved");
        } catch (Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }

    }

    /**
     * Pulls the values from the Scores object into a list for filling the HighScores view.
     * */
    public static ArrayList<tbl_score> getScores(){

        TypedQuery<tbl_score> topTenQuery = em.createQuery(
               "SELECT s FROM tbl_score s ORDER BY s.score DESC",tbl_score.class);

        return new ArrayList<>(topTenQuery.getResultList());


    }

}
