package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

class RestauranteTest {

	private Restaurante restaurante;
	
	@BeforeEach
	void setUp() {
		restaurante = new Restaurante();
		
	}
	
    @Test
    void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Cristiano Ronaldo", "Calle del Olimpo #7c-9");
        assertNotNull(restaurante.getPedidoEnCurso());
        
    }
    
    
    @Test
    void testIniciarPedidoConPedidoEnCurso() {
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Cristiano Ronaldo", "Calle del Olimpo #7c-9");
            restaurante.iniciarPedido("Lionel Messi", "Carrera del Olimpo  #10l-30");
        });
        
    }
    
    
    
    @Test
    void testCerrarYGuardarPedidoSinPedidoEnCurso() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> restaurante.cerrarYGuardarPedido());
    }
    
    
    @Test
    void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        restaurante.iniciarPedido("Cristiano Ronaldo", "Calle del Olimpo #7c-9");
        restaurante.cerrarYGuardarPedido();
        
        assertNull(restaurante.getPedidoEnCurso());
        assertFalse(restaurante.getPedidos().isEmpty());
        
        
    }
    @Test
    void testCargarIngredientes() throws IngredienteRepetidoException, IOException {
        File archivoIngredientes = File.createTempFile("ingredientes", ".txt");
        try (FileWriter writer = new FileWriter(archivoIngredientes)) {
            writer.write("Champinon;2000\n");
            writer.write("Queso azul;5000\n");
            
        }

        restaurante.cargarIngredientes(archivoIngredientes);
        assertEquals(2, restaurante.getIngredientes().size());

        
    }
    
    
    @Test
    void testCargarIngredientesRepetidos() throws IOException {
   
        File archivoIngredientes = File.createTempFile("ingredientes", ".txt");
        try (FileWriter writer = new FileWriter(archivoIngredientes)) {
            writer.write("Champinon;2000\n");
            writer.write("Champinon;2000\n");
        }

        assertThrows(IngredienteRepetidoException.class, () -> restaurante.cargarIngredientes(archivoIngredientes));
 
        
    }
    
    @Test
    void testCargarMenu() throws ProductoRepetidoException, IOException {

        File archivoMenu = File.createTempFile("menu", ".txt");
        try (FileWriter writer = new FileWriter(archivoMenu)) {
            writer.write("hamburguesa Suprema;30000\n");
            writer.write("papas fritas medianas;6000\n");
        }

        restaurante.cargarMenu(archivoMenu);
        assertEquals(2, restaurante.getMenuBase().size());

    }
    @Test
    void testCargarMenuRepetido() throws IOException {
        File archivoMenu = File.createTempFile("menu", ".txt");
        try (FileWriter writer = new FileWriter(archivoMenu)) {
            writer.write("hamburguesa Suprema;30000\n");
            writer.write("hamburguesa Suprema;30000\n");
        }

        assertThrows(ProductoRepetidoException.class, () -> restaurante.cargarMenu(archivoMenu));

        
        
    }

    @Test
    void testCargarCombos() throws ProductoRepetidoException, ProductoFaltanteException, IOException {
        File archivoMenu = File.createTempFile("menu", ".txt");
        File archivoCombos = File.createTempFile("combos", ".txt");
        try (FileWriter writerMenu = new FileWriter(archivoMenu)) {
            writerMenu.write("hamburguesa Suprema;30000\n");
            writerMenu.write("papas fritas medianas;6000\n");
        }
        try (FileWriter writerCombo = new FileWriter(archivoCombos)) {
            writerCombo.write("Combo Basico;0.1;hamburguesa Suprema;papas fritas medianas\n");
        }

        restaurante.cargarMenu(archivoMenu);
        restaurante.cargarCombos(archivoCombos);
        assertEquals(1, restaurante.getMenuCombos().size());

    }

    @Test
    void testCargarCombosProductoFaltante() throws IOException, ProductoRepetidoException {

        File archivoCombos = File.createTempFile("combos", ".txt");
        try (FileWriter writer = new FileWriter(archivoCombos)) {
            writer.write("Combo Basico;0.1;hamburguesa Inexistente;papas fritas medianas\n");
        }

        assertThrows(ProductoFaltanteException.class, () -> restaurante.cargarCombos(archivoCombos));
        
        
       
    }
    
    
    
    

}
