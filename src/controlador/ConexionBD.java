package controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by valdemar ramos oon 14/10/15.
 */
public class ConexionBD {

    private static EntityManager em;

    public static void conectar() {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("tallerPU");
        em = emf.createEntityManager();
    }

    public static EntityManager getEm() {
        return em;
    }
}
