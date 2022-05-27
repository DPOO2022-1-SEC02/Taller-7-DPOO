package uniandes.cupi2.almacen.test;
import org.junit.*;
import uniandes.cupi2.almacen.mundo.Categoria;

public class CategoriaTest {
	private Categoria categoria;
	
	//Creacion del mock object
	@Before
	public void setUp()
	{
		categoria = new Categoria("01","Categoria01");
		
	}
	
	@Test
	public void testDarNodos()
	{
		
		categoria.darNodos();
	}
	
	@Test
	public void testTieneHijo() 
	{
		
	}
	@Test
	public void testBuscarPadre() 
	{
		
	}
	@Test
	public void testBuscarNodo() 
	{
		
	}
	
	@Test
	public void testAgregarNodo() 
	{
		
	}

	
	
}
