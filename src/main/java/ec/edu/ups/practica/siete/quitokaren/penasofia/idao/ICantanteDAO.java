
package ec.edu.ups.practica.siete.quitokaren.penasofia.idao;

import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import java.util.List;


public interface ICantanteDAO {
    public abstract void create(Cantante cantante);
    public abstract Cantante read(int codigo);
    public abstract void update(Cantante cantante);
    public abstract void delete(Cantante cantante);
    public List<Cantante> findALL();
    public abstract Cantante buscarPorNombreDeDisco(String valor);

}
