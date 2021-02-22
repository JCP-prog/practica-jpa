/**
 * 
 */
package com.devpredator.practicajpa.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.devpredator.practicajpa.dao.RestauranteDAO;
import com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl;
import com.devpredator.practicajpa.entity.Menu;
import com.devpredator.practicajpa.entity.Restaurante;
import com.devpredator.practicajpa.entity.TipoRestaurante;

/**
 * @author JimmyC
 * Clase Test para comprobar el funcionamiento de los metodos del CRUD del restaurante
 */
class RestauranteDAOTest {
	private RestauranteDAO restauranteDAO = new RestauranteDAOImpl();
	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#guardar(com.devpredator.practicajpa.entity.Restaurante)}.
	 */
	@Test
	void testGuardar() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Chifa Pon Tu");
		restaurante.setSlogan("Lo mejor del Peru");
		restaurante.setImagen("imagenchifa.png");
		restaurante.setFechaCreacion(LocalDateTime.now());
		restaurante.setEstatus(true);
		
		TipoRestaurante tipoRestaurante = new TipoRestaurante();
		tipoRestaurante.setDescripcion("Español");
		tipoRestaurante.setFechaCreacion(LocalDateTime.now());
		tipoRestaurante.setEstatus(true);

		Menu menu = new Menu();
		menu.setClave("MENU_EMP");
		menu.setDescripcion("Menu Empresarial");
		menu.setFechaCreacion(LocalDateTime.now());
		menu.setEstatus(true);
		
		restaurante.setTipoRestaurante(tipoRestaurante);
		restaurante.setMenu(menu);
		
		this.restauranteDAO.guardar(restaurante);
	}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#actualizar(com.devpredator.practicajpa.entity.Restaurante)}.
	 */
	@Test
	void testActualizar() {
		Restaurante restauranteConsultado = this.restauranteDAO.consultarById(10l);
		restauranteConsultado.setNombre("El chilaquilpower");
		restauranteConsultado.getTipoRestaurante().setDescripcion("Mexicano");
		restauranteConsultado.getMenu().setClave("CVE_CTONIO");
		
		this.restauranteDAO.actualizar(restauranteConsultado);
	}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#eliminar(java.lang.Long)}.
	 */
	@Test
	void testEliminar() {
		this.restauranteDAO.eliminar(15l);
	}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#consultar()}.
	 */
	@Test
	void testConsultar() {
		List<Restaurante> restaurantesConsultados = this.restauranteDAO.consultar();
		
		assertTrue(restaurantesConsultados.size() > 0);
		
		for (Restaurante restaurante : restaurantesConsultados) {
			System.out.println("Restaurante "+restaurante.getNombre());
			System.out.println("Tipo Restaurante "+restaurante.getTipoRestaurante().getDescripcion());
			if(restaurante.getMenu() != null)
				System.out.println("Menu "+ restaurante.getMenu().getDescripcion());
		}
	}

	/**
	 * Test method for {@link com.devpredator.practicajpa.dao.impl.RestauranteDAOImpl#consultarById(java.lang.Long)}.
	 */
	@Test
	void testConsultarById() {
		Restaurante restaurante = this.restauranteDAO.consultarById(10l);
		
		System.out.println("Restaurante "+restaurante.getNombre());
		System.out.println("Imagen "+restaurante.getImagen());
		System.out.println("Slogan "+restaurante.getSlogan());
		System.out.println("Tipo Restaurante "+restaurante.getTipoRestaurante().getDescripcion());
		
		if(restaurante.getMenu() != null) {
			System.out.println("Clave Menu "+restaurante.getMenu().getClave());
			System.out.println("Menu "+restaurante.getMenu().getDescripcion());
		}
	}

}
