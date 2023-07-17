
package ec.edu.ups.practica.siete.quitokaren.penasofia.dao;

import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICompositorDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Compositor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositorDAO implements ICompositorDAO {
//atributos
    private List<Compositor> listaCompositor;
//constructor
    public CompositorDAO() {
        this.listaCompositor = new ArrayList<>();
    }
//sobreescritura metodos CRUD y buscar por Cancion
    @Override
    public void create(Compositor compositor) {
        listaCompositor.add(compositor);
    }
    @Override
    public Compositor read(int codigo) {
        for (Compositor compositor : listaCompositor) {
            if (compositor.getCodigo() == codigo) {
                return compositor;
            }
        }
        return null;
    }
    @Override
    public void update(Compositor compositor) {
        for (int i = 0; i < listaCompositor.size(); i++) {
            Compositor com = listaCompositor.get(i);
            if (com.getCodigo() == compositor.getCodigo()) {
                listaCompositor.set(i, compositor);
                break;
            }
        }
    }
    @Override
    public void delete(Compositor compositor) {
        Iterator<Compositor> it = listaCompositor.iterator();
        while (it.hasNext()) {
            Compositor co = it.next();
            if (co.getCodigo() == compositor.getCodigo()) {
                it.remove();
                break;
            }
        }
    }

    @Override
    public List<Compositor> findALL() {
        return listaCompositor;
    }

    @Override
    public Compositor buscarPorTituloDeCancion(String valor) {
        for (Compositor compositor : listaCompositor) {
            for (Cancion cancion : compositor.listarCanciones()) {
                if (cancion.getTitulo().equals(valor)) {
                    return compositor;
                }
            }
        }
        return null;
    }
    
}
