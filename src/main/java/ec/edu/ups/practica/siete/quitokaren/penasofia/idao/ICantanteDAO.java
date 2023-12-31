
package ec.edu.ups.practica.siete.quitokaren.penasofia.idao;

import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Disco;
import java.util.List;


public interface ICantanteDAO {
    public abstract void create(Cantante cantante);
    public abstract Cantante read(int codigo);
    public abstract void update(Cantante cantante);
    public abstract void delete(Cantante cantante);
    public List<Cantante> findALL();
    public abstract Cantante buscarPorNombreDeDisco(String valor);
    
    public void createDisco (int codigoC, int codigo, String nombre, int anioDeLanzamiento);
    public Disco readDisco(int codigoC, int codigo);
    public void updateDisco(int codigoC,int codigo, String nombre, int anioDeLanzamiento);
    public void deleteDisco(int codigoC, int codigo);
    public List<Disco> findAllDisco(int codigoC);

}
