package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class dbFunctions {

    private static EntityManagerFactory emf;
    private static EntityManager em;


    public static void DBInit(){
        emf = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        em = emf.createEntityManager();
    }

    public static void Dispose(){
        em.close();
        emf.close();
    }

    public static tbl_user loginUser(String username, String password) throws NoResultException {

        TypedQuery<tbl_user> loginQuery = em.createQuery(
                "SELECT tu FROM tbl_user tu WHERE tu.username ='" + username + "'AND tu.password='" + password + "'", tbl_user.class);

        System.out.println(loginQuery.getSingleResult());


        return null;
    }

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

    public static ArrayList<tbl_score> getScores(){

        TypedQuery<tbl_score> topTenQuery = em.createQuery(
               "SELECT s FROM tbl_score s ORDER BY s.score DESC",tbl_score.class);
        ArrayList<tbl_score> result = new ArrayList<>(topTenQuery.getResultList());

        return result;


    }

}
