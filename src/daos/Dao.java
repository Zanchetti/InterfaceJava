package daos;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aluno
 */
public class Dao {
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("InterfaceCatalogoPU");
    protected EntityManager em = emf.createEntityManager();
}
