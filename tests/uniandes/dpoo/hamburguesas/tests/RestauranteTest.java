package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
            restaurante.iniciarPedido("Carlos Pérez", "Calle 123 #45-67");
            restaurante.iniciarPedido("Maria López", "Carrera 9 #10-11");
        });
    }

}
