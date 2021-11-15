package facades;

import dtos.RenameMeDTO;
import dtos.UserDTO;
import entities.Corona;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entities.User;
import utils.EMF_Creator;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeExample() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RenameMeDTO create(RenameMeDTO rm) {
        RenameMe rme = new RenameMe(rm.getDummyStr1(), rm.getDummyStr2());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RenameMeDTO(rme);
    }

    public RenameMeDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        RenameMe rm = em.find(RenameMe.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new RenameMeDTO(rm);
    }

    //TODO Remove/Change this before use
    public long getRenameMeCount() {
        EntityManager em = getEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }
    }

    public List<RenameMeDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<RenameMe> query = em.createQuery("SELECT r FROM RenameMe r", RenameMe.class);
        List<RenameMe> rms = query.getResultList();
        return RenameMeDTO.getDtos(rms);
    }


    public String getcovid(String user) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT r FROM User r where r.userName =:name", User.class);
        query.setParameter("name", user);
        User res = query.getSingleResult();
        em.refresh(res);


        int lastindex = res.getCorona().size() - 1;
        if (res.getCorona().get(lastindex).isCovidstatus()) {

            return "Corona positive";
        } else {

            return "Corona negative";
        }
    }


//    public UserDTO setCovid(String u) {
//        EntityManager em = emf.createEntityManager();
//        TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.userName =:name", User.class);
//        query.setParameter("name", u);
//        User res = query.getSingleResult();
//
////        if (status) {
////            Corona newRona = new Corona(true);
////            res.getCorona().add(newRona);
////        } else {
////            Corona newRona = new Corona(false);
////            res.getCorona().add(newRona);
////        }
//
//
//            em.getTransaction().begin();
//            em.persist(res);
//            em.getTransaction().commit();
//            em.close();
//
//
//
//        return new UserDTO(res);
//    }


    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getAll().forEach(dto -> System.out.println(dto));
    }

}
