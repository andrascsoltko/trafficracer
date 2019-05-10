package Model;

import javax.persistence.*;

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

    public static tbl_user loginUser(String username, String password){



        try {
            TypedQuery<tbl_user> loginQuery = em.createQuery(
                    "SELECT tu FROM tbl_user tu WHERE tu.username ='" + username + "'AND tu.password='" + password + "'", tbl_user.class);

            System.out.println(loginQuery.getSingleResult());


        } catch (NoResultException ex){
            throw ex;
        }
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

}
