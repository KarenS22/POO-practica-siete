
package ec.edu.ups.practica.siete.quitokaren.penasofia.controlador;

import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICantanteDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICompositorDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Compositor;
import java.util.List;

public class ControladorCompositor {
    
    private Compositor compositor;
    private ICompositorDAO compositorDAO;
    
    private Cantante cantante;
    private ICantanteDAO cantanteDAO;
    
    public ControladorCompositor(ICompositorDAO compositorDAO) {
        this.compositorDAO = compositorDAO;
    }
    
    public void crear(Compositor compositor) {
        compositorDAO.create(compositor);
    }
    
    public Compositor buscar(int codigo) {
        this.compositor = compositorDAO.read(codigo);
        return this.compositor;
    }
    
    public boolean actualizar(Compositor compositor) {
        Compositor compositorEncontrado = this.buscar(compositor.getCodigo());
        if (compositorEncontrado != null) {
            compositorDAO.update(compositor);
            return true;
        }
        return false;
    }
    
    public boolean eliminar(int codigo) {
        Compositor compositorEncontrado = this.buscar(compositor.getCodigo());
        if (compositorEncontrado != null) {
            compositorDAO.delete(compositorEncontrado);
            return true;
        }
        return false;
    }
    
    public List<Compositor> listar() {
        return compositorDAO.findALL();
    }
    
    public boolean ingresarCancion(int codigoCompositor, int codigoCancion, String titulo, String letra, double tiempoEnMinutos) {
        Compositor c = this.buscar(codigoCompositor);
        if (c != null) {
            c.agregarCancion(codigoCancion, titulo, letra, tiempoEnMinutos);
            compositorDAO.update(c);
            return true;
        }
        return false;
    }
    
    public Cancion verCancion(int codidoCompositor, int codigoCancion) {
        Compositor c = this.buscar(codidoCompositor);
        Cancion ca = c.buscarCancion(codigoCancion);
        return ca;
    }
    
    public boolean actualizarCancion(int codigoCompositor, int codigoCancion, String titulo, String letra, double tiempoEnMinutos) {
        Cancion cancion = this.verCancion(codigoCompositor, codigoCancion);
        if (cancion != null) {
            this.compositor.actualizarCancion(codigoCancion, titulo, letra, tiempoEnMinutos);
            compositorDAO.update(compositor);
            return true;
        }
        return false;
    }
    
    public boolean eliminarCancion(int codidoCompositor, int codigoCancion) {
        Cancion cancion = this.verCancion(codidoCompositor, codigoCancion);
        if (cancion != null) {
            this.compositor.eliminarCancion(cancion);
            compositorDAO.update(compositor);
            return true;
        }
        return false;
    }
    
    public List<Cancion> verCanciones(int codidoCompositor) {
        Compositor c = this.buscar(codidoCompositor);
        return c.listarCanciones();
    }
//

    public boolean ingresarCliente(int codigoCompositor, Cantante cantante) {
        Compositor c = this.buscar(codigoCompositor);
        if (c != null) {
            c.agregarCliente(cantante);
            compositorDAO.update(c);            
            return true;
        }
        return false;
    }
    
    public Cantante verCliente(int codidoCompositor, int codigoCantante) {
        Compositor c = this.buscar(codidoCompositor);
        Cantante ca = c.buscarCli(codigoCantante);
        return ca;
    }
    
    public boolean actualizarCliente(int codidoCompositor, int codigoCantante, String nombre, String apellido) {
        Cantante ca = this.verCliente(codidoCompositor, codigoCantante);
        if (ca != null) {
            this.compositor.actualizarCliente(codigoCantante, nombre, apellido);
            compositorDAO.update(compositor);
            return true;
        }
        return false;
    }
    
    public boolean eliminarCliente(int codidoCompositor, int codigoCancion) {
        Cancion cancion = this.verCancion(codidoCompositor, codigoCancion);
        if (cancion != null){
            this.compositor.eliminarCancion(cancion);
        compositorDAO.update(compositor);
        return true;
        }
        return false;
    }
    
    public List<Cantante> verClientes(int codidoCompositor) {
        Compositor c = this.buscar(codidoCompositor);
        return c.listarClientes();
    }
    
}
