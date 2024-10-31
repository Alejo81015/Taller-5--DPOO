package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class PedidoTest {
	
	private Pedido pedido1;
	private int NumPedidos=-1;
	
	@BeforeEach
	void setUp() throws Exception {
		pedido1 = new Pedido("Julio Iglesias","Carrea 1 #18a-10");
		NumPedidos = pedido1.getNumIdPedido();
		
	    }
	@AfterEach
	void SetDown() throws Exception {
		pedido1.setNumIdPedido(NumPedidos);
	}

	@Test
	void testGetIdPedido() {
		int respuesta = pedido1.getNumIdPedido()-1;
		assertEquals(respuesta, pedido1.getIdPedido(),"El id no coincide");
	}
	
	@Test
	void testGetNombreCliente() {
		assertEquals("Julio Iglesias",pedido1.getNombreCliente(),"El nombre del cileinte no coincide");
	}
	
	@Test
	void testAgregarProducto() {
		ArrayList<Producto> prueba = new ArrayList<Producto>();
		ProductoMenu producto1 = new ProductoMenu("Malteada", 10000);
		ProductoMenu producto2 = new ProductoMenu("Papas", 6000);
		ArrayList <ProductoMenu> productos = new ArrayList <ProductoMenu>();
		Combo combo1 = new Combo("SENECA",0.2, productos);
		prueba.add(producto1);
		prueba.add(combo1);
		pedido1.agregarProducto(producto1);
		pedido1.agregarProducto(combo1);
		assertEquals(prueba,pedido1.GetProdcutos(),"No se agregaron correctamente");
	}
	
	@Test
	void testGetPrecioTotalPedido() {
		ProductoMenu producto1 = new ProductoMenu("Malteada", 10000);
		ProductoMenu producto2 = new ProductoMenu("Papas", 6000);
		ArrayList <ProductoMenu> productos = new ArrayList <ProductoMenu>();
		productos.add(producto2);
		productos.add(producto1);
		Combo combo1 = new Combo("SENECA",0.2, productos);
		pedido1.agregarProducto(producto1);
		pedido1.agregarProducto(combo1);
		
		int respuesta = (int) ((10000+6000)*(1-0.2) + 10000);
		respuesta = (int) (respuesta + respuesta * 0.19);
		assertEquals(respuesta,pedido1.getPrecioTotalPedido(),"El precio total no coincide");
	}
	//Con este test se esta probando precioIva y precioNeto porque estan contenidos en la funcion

	
	@Test
	void TestgenerarTextoFactura() {
		ProductoMenu producto1 = new ProductoMenu("Malteada", 10000);
	    ProductoMenu producto2 = new ProductoMenu("Papas", 6000);
	    ArrayList<ProductoMenu> productos = new ArrayList<>();
	    productos.add(producto2);
	    productos.add(producto1);
	    Combo combo1 = new Combo("SENECA", 0.2, productos);
	    pedido1.agregarProducto(producto1);
	    pedido1.agregarProducto(combo1);

	    StringBuilder r = new StringBuilder();
	    r.append("Cliente: Julio Iglesias\n");
	    r.append("Dirección: Carrea 1 #18a-10\n");
	    r.append("----------------\n");
	    r.append(producto1.generarTextoFactura());
	    r.append(combo1.generarTextoFactura());
	    r.append("----------------\n");
	    r.append("Precio Neto:  22800\n");
	    r.append("IVA:          4332\n");
	    r.append("Precio Total: 27132\n");

	    assertEquals(r.toString(), pedido1.generarTextoFactura(), "El texto de la factura no coincide");
       
	}
	
	@Test
	void testGuardarFactura() throws FileNotFoundException {

	    ProductoMenu producto1 = new ProductoMenu("Malteada", 10000);
	    ProductoMenu producto2 = new ProductoMenu("Papas", 6000);
	    ArrayList<ProductoMenu> productos = new ArrayList<>();
	    productos.add(producto2);
	    productos.add(producto1);
	    Combo combo1 = new Combo("SENECA", 0.2, productos);
	    pedido1.agregarProducto(producto1);
	    pedido1.agregarProducto(combo1);

	    File archivo = new File("factura_test.txt");

	    pedido1.guardarFactura(archivo);

	    StringBuilder contenidoArchivo = new StringBuilder();
	    try (Scanner scanner = new Scanner(archivo)) {
	        while (scanner.hasNextLine()) {
	            contenidoArchivo.append(scanner.nextLine()).append("\n");
	        }
	    } catch (FileNotFoundException e) {
	        fail("No se pudo encontrar el archivo de factura");
	    }
	    String textoEsperado = pedido1.generarTextoFactura();
	    assertEquals(textoEsperado, contenidoArchivo.toString(), "El contenido del archivo no coincide con la factura esperada");
	}

}
