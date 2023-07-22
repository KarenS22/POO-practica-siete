
package ec.edu.ups.practica.siete.quitokaren.penasofia.controlador;

import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICantanteDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Disco;
import java.util.List;

public class ControladorCantante {

    private Cantante cantante;
    private ICantanteDAO cantanteDAO;

    public ControladorCantante(ICantanteDAO cantanteDAO) {
        this.cantanteDAO = cantanteDAO;
    }

    public void crear(Cantante cantante) {
        cantanteDAO.create(cantante);
    }

    public Cantante buscar(int codigo) {
        this.cantante = cantanteDAO.read(codigo);
        return this.cantante;
    }

    public boolean actualizar(Cantante cantante) {
        Cantante cantanteEncontrado = this.buscar(cantante.getCodigo());
        if (cantanteEncontrado != null) {
            cantanteDAO.update(cantante);
            return true;
        }
        return false;
    }

    public boolean eliminar(int codigo) {
        Cantante cantanteEncontrado = this.buscar(codigo);
        if (cantanteEncontrado != null) {
            cantanteDAO.delete(cantanteEncontrado);
            return true;
        }
        return false;
    }

    public List<Cantante> listar() {
        return cantanteDAO.findALL();
    }

    public boolean ingresarDisco(int codigoCantante, int codigoDisco, String nombreDisco, int anioDeLanzamiento) {
        Cantante c = this.buscar(codigoCantante);
        if (c != null) {
            cantanteDAO.createDisco(codigoCantante, codigoDisco, nombreDisco, anioDeLanzamiento);
            cantanteDAO.update(c);
            return true;
        }
        return false;
    }

    //posible modificacion
    public Disco verDisco(int codigoCa, int codigoD) {
        Cantante c = this.buscar(codigoCa);
        Disco d = cantanteDAO.readDisco(c, codigoD);
        return d;
    }

    public boolean actualizarDisco(int codigoCa, int codigoD, String nombre, int anioDeLanzamiento) {
        Cantante c = this.buscar(codigoCa);
        if (c != null){
            cantanteDAO.updateDisco(c, codigoD, nombre, anioDeLanzamiento);
            cantanteDAO.update(cantante);
            return true;
        }
        return false;
    }

    public boolean eliminarDisco(int codigoCa, int codigoD) {
        Cantante c = this.buscar(codigoCa);
        if (c != null){
            cantanteDAO.deleteDisco(c, codigoD);
            cantanteDAO.update(cantante);
            return true;
        }
        return false;
    }

    public List<Disco> verDiscos(int codigoCa) {
        Cantante c = this.buscar(codigoCa);
        return cantanteDAO.findAllDisco(c);
    }

    public Cantante buscarPorNombreDeDisco(String nombreD) {
        this.cantante = cantanteDAO.buscarPorNombreDeDisco(nombreD);
        return this.cantante;
    }

}
