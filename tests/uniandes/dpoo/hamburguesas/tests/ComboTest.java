package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ComboTest {
	
	private Combo combo1;
	
	@BeforeEach
	void setUp() {
		
		ProductoMenu producto1 = new ProductoMenu("Malteada", 10000);
		ProductoMenu producto2 = new ProductoMenu("Papas fritas medianas", 6000);
		ProductoMenu producto3 = new ProductoMenu("Hamburguesa Suprema", 30000);
		ArrayList<ProductoMenu> productos = new ArrayList <> ();
		productos.add(producto3);
		productos.add(producto2);
		productos.add(producto1);
		combo1 = new Combo("Especial",0.1,productos);
	}

	@Test
	void testGetNombre() {
		assertEquals("Especial",combo1.getNombre(),"El nombre no coincide");
	}
	
	@Test
	void testGetPrecio() {
		double respuesta = (10000+6000+30000)*(1-0.1);
		assertEquals(respuesta,combo1.getPrecio(),"El precio del combo no es correcto");
	}

	@Test
	void testGenerarTextoFactura() {
		String respuesta = "Combo " + combo1.getNombre() + "\n";
		respuesta +=  " Descuento: " + combo1.getDescuento() + "\n";
		respuesta += "            " + combo1.getPrecio() + "\n";
		assertEquals(respuesta, combo1.generarTextoFactura(), "Texto de la factura generado incorrectamente");
		
	}
}
