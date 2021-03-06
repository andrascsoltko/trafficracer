package Model;

import org.tinylog.Logger;

import javax.persistence.*;
import java.util.ArrayList;


/**
 * Everything related to database are happening here.
 * */
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
        Logger.info("Database connection initialised and connected.");
    }

    /**
     * Database connection closer.
     * Gets called by a stage.setOnCloseRequest, closes the database connection.
     * */
    public static void Dispose(){
        em.close();
        emf.close();
        Logger.info("Database connection closed.");
    }

    /**
     * Authentication sequence.
     * Tries to select data by given parameters.
     * @param username is the username of the user.
     * @param password is the password of the user.
     * @return is null, because
     * @throws NoResultException solves login issues for us.
     * */
    public static tbl_user loginUser(String username, String password) throws NoResultException {

        TypedQuery<tbl_user> loginQuery = em.createQuery(
                "SELECT tu FROM tbl_user tu WHERE tu.username = :username AND tu.password= :password", tbl_user.class)
                .setParameter("username", username)
                .setParameter("password", password);

        Logger.info(loginQuery.getSingleResult());

        return null;
    }

    /**
     * Registration sequence.
     * Initialises a new transaction, and creates a new account with the given parameters:
     * @param username the username of the user and
     * @param password password of the user.
     * */
    public static void registerUser(String username, String password) {

        tbl_user newUser = new tbl_user(username, password);
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
            Logger.info("Created new user!");
        } catch (Exception ex) {
            em.getTransaction().rollback();
            Logger.info(ex);
            throw ex;
        }
    }

    /**
     * Initialises a new transaction, saves the achieved score.
     * @param username to associate the score with the player.
     * @param score the achieved score.
     * */
    public static void saveScore(String username, int score){

        tbl_score newScore = new tbl_score(username, score);
        try {
            em.getTransaction().begin();
            em.persist(newScore);
            em.getTransaction().commit();
            Logger.info("Score saved");

        } catch (Exception ex){
            em.getTransaction().rollback();
            Logger.info("Score saving failed.");
            Logger.info(ex);
            throw ex;
        }

    }

    /**
     * Pulls the values from the Scores object into a list for filling the HighScores view.
     * @return the list of scores in a descending order.
     * */
    public static ArrayList<tbl_score> getScores(){

        TypedQuery<tbl_score> highscores = em.createQuery(
               "SELECT s FROM tbl_score s ORDER BY s.score DESC",tbl_score.class);

        Logger.info("Loaded high scores.");
        return new ArrayList<>(highscores.getResultList());


    }

}
