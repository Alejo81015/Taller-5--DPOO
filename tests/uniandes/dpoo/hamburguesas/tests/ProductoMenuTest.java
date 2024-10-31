package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoMenuTest {
	
	private  ProductoMenu ProductoMenu1;
	@BeforeEach
	public void setUp() throws Exception
{
		ProductoMenu1 = new ProductoMenu( "amvorguesa", 20000 );
	}
	  @Test
	    void testGetNombre( )
	    {
		  System.out.println(ProductoMenu1.getNombre());
	        assertEquals( "amvorguesa", ProductoMenu1.getNombre( ), "El nombre del producto del menu no es el esperado." );
	    }	
	  
	  @Test
	  void testGetPrecio()
	  {
		  assertEquals(20000,ProductoMenu1.getPrecio(),"El precio del producto menu es incorrecto");
	  }
	  
	  
	  @Test
	  void testGenerarTextoFactura() 
	  {
		  String respuesta = "amvorguesa" + "\n" + "            " + "20000" + "\n";
		  assertEquals(respuesta,ProductoMenu1.generarTextoFactura(), "Texto factura generado incorrectamente");
	  }
	  
	

}
