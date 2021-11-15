package utils;


import entities.Corona;
import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class SetupTestUsers {

    public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        // Also, either delete this file, when users are created or rename and add to .gitignore
        // Whatever you do DO NOT COMMIT and PUSH with the real passwords

        User user = new User("user", "sorteper1");
        User admin = new User("admin", "sorteper2");
        User kofoed = new User("Kofoed", "sorteper3");

        List<Corona> coronaPos = new ArrayList<>();
        List<Corona> coronaNeg = new ArrayList<>();
        Corona neg = new Corona(false);
        Corona tru = new Corona(true);
        coronaPos.add(tru);
        coronaNeg.add(neg);

        kofoed.setCorona(coronaPos);
        user.setCorona(coronaNeg);
        admin.setCorona(coronaNeg);


        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        kofoed.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(kofoed);
        em.persist(admin);
        em.getTransaction().commit();
        em.close();


    }

}
