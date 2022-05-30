package uniandes.cupi2.almacen.test;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.Marca;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


class AlmacenTest {

	private Almacen almacen;
	private Categoria categoria;

	@BeforeEach
	public void setUp() throws AlmacenException
	{
		this.almacen = new Almacen(new File("data/datos.txt"));
		this.categoria = new Categoria("testIdentificador","testNombre");
		this.almacen.agregarProducto("1112", "codigoTest", "nombreTest", "descripcionTest", 18519198);
		this.almacen.agregarNodo("111", "Marca", "1313", "Frailejon");
	}
	
	@Test
	void TestDarCategoriaRaiz() 
	{
		assertEquals("Cupi2", this.almacen.darCategoriaRaiz().buscarNodo("1").darNombre());
	}
	
	//Este test da error en consola
	@Test
	void TestExceptionCargar() throws AlmacenException
	{
		assertThrows(AlmacenException.class,()->this.almacen = new Almacen(new File("data/nodatos.txt")));
	}
	
	
	//Aqui se hace test tambien de Categoria.buscarNodo
	@Test
	void TestAgregarNodo() throws AlmacenException
	{
		assertEquals("Frailejon",this.almacen.buscarNodo("1313").darNombre());				
	}
	
	//Aqui se hace test tambien de Categoria.buscarProducto
	
	@Test
	void TestAgregarProducto() throws AlmacenException
	{
		assertEquals("codigoTest",this.almacen.darCategoriaRaiz().buscarProducto("codigoTest").darCodigo());
		assertEquals("nombreTest",this.almacen.darCategoriaRaiz().buscarProducto("codigoTest").darNombre());
		assertEquals("descripcionTest",this.almacen.darCategoriaRaiz().buscarProducto("codigoTest").darDescripcion());
		assertEquals(18519198,this.almacen.darCategoriaRaiz().buscarProducto("codigoTest").darPrecio());
	}
	@Test
	void TestAgregarProductoExcepcion() throws AlmacenException
	{
		
		assertThrows(AlmacenException.class,()->this.almacen.agregarProducto("1112", "30557851", "cualquierVaina", "cualquierVaina_2", 18519198));
		

	}
	@Test
	void TestVenderProducto() throws AlmacenException
	{
		this.almacen.venderProducto("30557851", 13);
		assertEquals(13,this.almacen.darCategoriaRaiz().buscarProducto("30557851").darCantidadUnidadesVendidas());
		
	}
	
	@Test
	void TestEliminarProducto() 
	{
		this.almacen.eliminarProducto("30557851");
		assertNull(this.almacen.darCategoriaRaiz().buscarNodo("30557851"));
	}
	
	
	
	@Test
	void TestMetodo1() {
		assertEquals("Respuesta 1",this.almacen.metodo1());
	}
	
	@Test
	void TestMetodo2() {
		assertEquals("Respuesta 2",this.almacen.metodo2());
	}
	
	//Pruebas raras de german que claramente funcionan bien
    @Test
    void pruebaRetornoCategoria()throws AlmacenException{
        var miAlmacen = new Almacen(new File("./data/datos.txt"));
        assertEquals(new Categoria("1","Cupi2").darIdentificador(),miAlmacen.darCategoriaRaiz().darIdentificador());
    }

    @Test
    void noSepuedeEliminarException()throws AlmacenException{
        var miAlmacen = new Almacen(new File("./data/datos.txt"));
        assertThrows(AlmacenException.class,()->miAlmacen.eliminarNodo("1"));
    }

    @Test
    void eliminadoCorrecto()throws AlmacenException{
        var miAlmacen = new Almacen(new File("./data/datos.txt"));
        assertEquals(miAlmacen.darCategoriaRaiz(),miAlmacen.eliminarNodo("11"));
    }

    @Test
    void venderFunciona() throws  AlmacenException{
        var miAlmacen = new Almacen(new File("./data/datos.txt"));

    }
    
    
    
    
    //Pruebas de Categoria
    
    @Test
    public void darLista() {
    	assertFalse(this.almacen.darCategoriaRaiz().darNodos().isEmpty());
    	assertFalse(this.almacen.darCategoriaRaiz().darProductos().isEmpty());
    	assertFalse(this.almacen.darCategoriaRaiz().darPosorden().isEmpty());
    	assertFalse(this.almacen.darCategoriaRaiz().darPreorden().isEmpty());
    }
    
    @Test
    public void valorVentas() {
    	assertEquals(0, this.almacen.buscarNodo("112").darValorVentas(),0);
    }
    
    @Test
    public void agregarNodoNuevaCategoria() throws AlmacenException {
    	this.categoria.agregarNodo("11223344", "Categoria", "12043", "Alo");
    }
    
    @Test
    void testBuscarPadre() {
    	Categoria cat;
    	cat = this.almacen.darCategoriaRaiz().buscarPadre("1111");
    	assertEquals("111",cat.darIdentificador());
    }
    
    
    @Test
	public void agregarUnProductoExistente() {
		assertThrows(AlmacenException.class,() -> this.almacen.agregarProducto("1112", "codigoTest", "nombreTest", "descripcionTest", 18519198));
	}
    @Test
	public void agregarUnNodoLanzaExcepcion() {
		
		assertThrows(AlmacenException.class,() -> this.almacen.agregarNodo("111", "Marca", "1313", "Frailejon"));
	}
	
    
}