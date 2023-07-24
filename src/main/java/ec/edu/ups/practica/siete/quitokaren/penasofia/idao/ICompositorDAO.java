
package ec.edu.ups.practica.siete.quitokaren.penasofia.idao;

import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Compositor;
import java.util.List;


public interface ICompositorDAO {
    //metodos abstractos que seran modificafos 
    public abstract void create(Compositor compositor);
    public abstract Compositor read(int codigo);
    public abstract void update(Compositor compositor);
    public abstract void delete(Compositor compositor);
    public List<Compositor> findALL();
    public abstract Compositor buscarPorTituloDeCancion(String valor);
    
    public void createCancion (int codigoCompositor, int codigoCancion, String titulo, String letra, double tiempoEnMinutos);
    public Cancion readCancion(int codigoCompositor, int codigoCancion);
    public void updateCancion(int codigoCompositor,int codigoCancion, String titulo, String letra, double tiempoEnMinutos);
    public void deleteCancion(int codigoCompositor, int codigo);
    public List<Cancion> findAllCancion(int codigoCompositor);
    
    
    public void agregarCliente(int codigoCompositor, Cantante cliente);
    //metodo actualiza datos de cliente
    public void actualizarCliente(int codigoCompositor ,int codigoCantante, String nombre, String apellido);
    //metodo elimina cliente
    public void eliminarCliente(int codigoCompositor, int codigoCliente);
    //lista de clientes
    public List<Cantante> listarClientes(int codigoCompositor);
    public Cantante buscarCli(int codigoCompositor ,int idCliente);
}
