package uniandes.cupi2.almacen.test;

import org.junit.jupiter.api.Test;
import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AlmacenTest {
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
}