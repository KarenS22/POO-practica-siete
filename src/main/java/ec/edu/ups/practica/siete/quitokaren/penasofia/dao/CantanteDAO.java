package ec.edu.ups.practica.siete.quitokaren.penasofia.dao;

import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICantanteDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Disco;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CantanteDAO implements ICantanteDAO {

//atributo
    private List<Cantante> listaCantante;
//constructor

    public CantanteDAO() {
        this.listaCantante = new ArrayList<>();
    }

    @Override
    public void create(Cantante cantante) {
        try {
            String ruta = "C:/carpeta1/archivoCantantePrueba.dat";
            RandomAccessFile archivoCrearCantante;
            archivoCrearCantante = new RandomAccessFile(ruta, "rw");

            long pos = archivoCrearCantante.length();
            archivoCrearCantante.seek(pos);

            archivoCrearCantante.writeInt(cantante.getCodigo());
            StringBuilder nombre = new StringBuilder(cantante.getNombre());
            nombre.setLength(10);
            archivoCrearCantante.writeUTF(nombre.toString());
            System.out.println(archivoCrearCantante.length());
            archivoCrearCantante.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura");
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
    }

//sobreescriturametodos CRUD
//    @Override
//    public void create(Cantante cantante) {
//        listaCantante.add(cantante);
//    }
    @Override
    public Cantante read(int codigo) {
        for (Cantante cantante : listaCantante) {
            if (cantante.getCodigo() == codigo) {
                return cantante;
            }
        }
        return null;
    }

    @Override
    public void update(Cantante cantante) {
        for (int i = 0; i < listaCantante.size(); i++) {
            Cantante cantan = listaCantante.get(i);
            if (cantan.getCodigo() == cantante.getCodigo()) {
                listaCantante.set(i, cantante);
                break;
            }
        }
    }

    @Override
    public void delete(Cantante cantante) {
        Iterator<Cantante> it = listaCantante.iterator();
        while (it.hasNext()) {
            Cantante c = it.next();
            if (c.getCodigo() == cantante.getCodigo()) {
                it.remove();
                break;
            }
        }
    }

    @Override
    public List<Cantante> findALL() {
        return listaCantante;
    }
//sobreescritura metodo buscar por disco y devuelve un cantante

    @Override
    public Cantante buscarPorNombreDeDisco(String valor) {
        for (Cantante cantante : listaCantante) {
            for (Disco disco : cantante.listaDisco()) {
                if (disco.getNombre().equals(valor)) {
                    return cantante;
                }
            }
        }
        return null;
    }

}
