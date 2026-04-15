package br.com.gelatos;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GelatoDAO {
	
	public void incluirGelato(Gelato gelato) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		entityManager.persist(gelato);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        	
	}
	
	public Gelato atualizarGelato(Gelato gelato) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		Gelato gelatoRetorno = entityManager.merge(gelato);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        
        return gelatoRetorno;
	}
	
	
	public void removerGelato(Gelato gelato) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		 
		entityManager.getTransaction().begin();		
		entityManager.remove(gelato);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
        
	}
	
	public void removerPorId(Gelato gelato) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Gelato gelatoDelecao = entityManager.find(Gelato.class, gelato.getId());
		 
		entityManager.getTransaction().begin();		
		entityManager.remove(gelatoDelecao);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
       
	}
	
	
	public List<Gelato> getListEntity() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	
		List<Gelato> listaGelato = entityManager.createQuery("FROM Gelato ORDER BY id ASC", Gelato.class).getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return listaGelato;
		
	}

}
