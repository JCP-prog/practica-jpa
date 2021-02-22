/**
 * 
 */
package com.devpredator.practicajpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.devpredator.practicajpa.dao.RestauranteDAO;
import com.devpredator.practicajpa.entity.Restaurante;

/**
 * @author JimmyC
 * Clase que implementa el CRUD para las transacciones a la tabla de restaurante.
 */
public class RestauranteDAOImpl implements RestauranteDAO{
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("persistenceDevPredator");
	
	@Override
	public void guardar(Restaurante restaurante) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(restaurante);
			et.commit();
		} catch (Exception e) {
			if(et != null) {
				et.rollback();
			}
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}

	@Override
	public void actualizar(Restaurante restaurante) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.merge(restaurante);
			et.commit();
		} catch (Exception e) {
			if(et != null) {
				et.rollback();
			}
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}

	@Override
	public void eliminar(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		Restaurante restauranteConsultado = em.find(Restaurante.class, id);
		
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.remove(restauranteConsultado);
			et.commit();
		} catch (Exception e) {
			if(et != null) {
				et.rollback();
			}
			e.printStackTrace();
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<Restaurante> consultar() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		TypedQuery<Restaurante> queryRestaurante = (TypedQuery<Restaurante>) em.createQuery("FROM Restaurante ORDER BY nombre");
		
		return queryRestaurante.getResultList();
	}

	@Override
	public Restaurante consultarById(Long id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		return em.find(Restaurante.class, id);
	}


	
}
