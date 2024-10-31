package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoAjustadoTest {
	
	private ProductoAjustado ProductoAjustado1; 
	private ProductoMenu Productobase;
	
	@BeforeEach
	void setUp () throws Exception 
	{
		Productobase = new ProductoMenu("TODOTERRENO", 35000);
		ProductoAjustado1 = new ProductoAjustado (Productobase);
		Ingrediente ingrediente1= new Ingrediente("Champinon",2000);
		Ingrediente ingrediente2= new Ingrediente("Queso azul",5000);
		Ingrediente ingrediente3= new Ingrediente("Lechuga",1500);
		ProductoAjustado1.EliminarIngrediente(ingrediente3);
		ProductoAjustado1.AgregarIngrediente(ingrediente2);
		ProductoAjustado1.AgregarIngrediente(ingrediente1);
	}

	@Test
	void testGetNombre() {
		assertEquals("TODOTERRENO",ProductoAjustado1.getNombre(),"Nombre incorrecto");
	}
	
	@Test
	void testGetPrecio() {
		assertEquals(42000,ProductoAjustado1.getPrecio(),"Precio incorrecto");
		System.out.println(ProductoAjustado1.getPrecio());
	}
	
	@Test
	void testGenerarTextoFactura() 
	{
		String respuesta = ProductoAjustado1.getNombre();
		respuesta = respuesta + "    +" + "Queso azul" + "                " + "5000";
		respuesta = respuesta + "    +" + "Champinon" + "                " + "2000";
		respuesta = respuesta + "    -" + "Lechuga";
		respuesta = respuesta + "            " + ProductoAjustado1.getPrecio()  + "\n";
		
		assertEquals(respuesta, ProductoAjustado1.generarTextoFactura(),"Texto generado incorrectamente");
	}
	
	

}
