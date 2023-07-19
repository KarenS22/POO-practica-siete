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
    private int contadorRegistro;
    private List<Cantante> listaCantante;
//constructor

    public CantanteDAO() {
        this.listaCantante = new ArrayList<>();
        this.contadorRegistro = 0;
    }

    public String rellenarEspacios(String cadena, int longitudDeseada) {
        StringBuilder nombre = new StringBuilder(cadena);
        int sizeFirst = nombre.length();
        int espaciosFaltantes = longitudDeseada - sizeFirst;
        if (nombre.length() != longitudDeseada) {
            for (int i = 0; i < espaciosFaltantes; i++) {
                nombre.append(" ");
            }
        }
        nombre.setLength(longitudDeseada);
        return nombre.toString();
    }

    @Override
    public void create(Cantante cantante) {
        try {
            String ruta = "C:/carpeta1/archivoCantantePrueba.dat";
            RandomAccessFile archivoCrearCantante;
            archivoCrearCantante = new RandomAccessFile(ruta, "rw");
            archivoCrearCantante.seek(0);
            //4 b
            archivoCrearCantante.writeInt(contadorRegistro +=1);
            long pos = archivoCrearCantante.length();
            archivoCrearCantante.seek(pos);
            
            
            // 4 bytes
            archivoCrearCantante.writeInt(cantante.getCodigo());
            // 10 bytes + 2
            String nombre = rellenarEspacios(cantante.getNombre(), 10);
            archivoCrearCantante.writeUTF(nombre);
            // longitud de 10 bytes + 2
            String apellido = rellenarEspacios(cantante.getApellido(), 10);
            archivoCrearCantante.writeUTF(apellido);
            // 4 bytes
            archivoCrearCantante.writeInt(cantante.getEdad());
            // longitud de 10 bytes + 2
            String nacionalidad = rellenarEspacios(cantante.getNacionalidad(), 10);
            archivoCrearCantante.writeUTF(nacionalidad);
            // 8 bytes
            archivoCrearCantante.writeDouble(cantante.getSalario());
            // longitud de 10 bytes + 2
            String nombreArtistico = rellenarEspacios(cantante.getNombreArtistico(), 10);
            archivoCrearCantante.writeUTF(nombreArtistico);
            // longitud de 10 bytes + 2
            String generoMusical = rellenarEspacios(cantante.getGeneroMusical(), 10);
            archivoCrearCantante.writeUTF(generoMusical);
            // 4 bytes
            archivoCrearCantante.writeInt(cantante.getNumeroDeSencillos());
            // 4 bytes
            archivoCrearCantante.writeInt(cantante.getNumeroDeConciertos());
            // 4 bytes
            archivoCrearCantante.writeInt(cantante.getNumeroDeGiras());
            // segun calculos 92 bytes cada registro sin canciones 
            System.out.println(archivoCrearCantante.length());
            //contadorRegistro += 1;
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
        try {
            String ruta = "C:/carpeta1/archivoCantantePrueba.dat";
            RandomAccessFile archivoLecturaCantante;
            archivoLecturaCantante = new RandomAccessFile(ruta, "r");
            archivoLecturaCantante.seek(0);
            int contReg = archivoLecturaCantante.readInt();
            System.out.println(contReg);
            long pos = 4;
            // archivoLectura.seek(n);
            
            for (int i = 0; i < contadorRegistro; i++) {
                archivoLecturaCantante.seek(pos);
                int codigoL = archivoLecturaCantante.readInt();
                System.out.println("codigo " + codigoL);
                if (codigoL == codigo) {
                    int newCodigo = codigoL;
                    String nombre = archivoLecturaCantante.readUTF().trim();
                    String apellido = archivoLecturaCantante.readUTF().trim();
                    int edad = archivoLecturaCantante.readInt();
                    String nacionalidad = archivoLecturaCantante.readUTF().trim();
                    double salario = archivoLecturaCantante.readDouble();
                    String nombreArtistico = archivoLecturaCantante.readUTF().trim();
                    String generoMusical = archivoLecturaCantante.readUTF().trim();
                    int nSencillos = archivoLecturaCantante.readInt();
                    int nConciertos = archivoLecturaCantante.readInt();
                    int nGiras = archivoLecturaCantante.readInt();

                    Cantante c = new Cantante(nombreArtistico, generoMusical, nSencillos, nConciertos, nGiras, newCodigo, nombre, apellido, edad, nacionalidad, salario);
                    archivoLecturaCantante.close();
                    return c;
                }
                System.out.println("pos anterior " + pos);
                pos += 88;
                System.out.println("pos nuevo " + pos);
                //System.out.println(x);
                //System.out.println(n);
            }

            archivoLecturaCantante.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrado " + e1);
        } catch (IOException e2) {
            System.out.println("Error de lectura " + e2 + e2.getMessage());
        } catch (Exception e3) {
            System.out.println("Eror General " + e3);
        }
        return null;

    }

    @Override
    public void update(Cantante cantanteActualizado) {
        
        try {
            String ruta = "C:/carpeta1/archivoCantantePrueba.dat";
            RandomAccessFile archivoCantante;
            archivoCantante = new RandomAccessFile(ruta, "rw");

            long pos = 0;
            for (int i = 0; i < contadorRegistro; i++) {
                archivoCantante.seek(pos);
                int codigoL = archivoCantante.readInt();
                System.out.println(codigoL);
                if (codigoL == cantanteActualizado.getCodigo()) {
                    // Encontramos el registro que coincide con el código, ahora actualizamos los datos
                    System.out.println(pos);
                   // archivoCantante.writeInt(cantanteActualizado.getCodigo());
                    System.out.println(pos);
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getNombre(), 10));
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getApellido(), 10));
                    archivoCantante.writeInt(cantanteActualizado.getEdad());
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getNacionalidad(), 10));
                    archivoCantante.writeDouble(cantanteActualizado.getSalario());
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getNombreArtistico(), 10));
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getGeneroMusical(), 10));
                    archivoCantante.writeInt(cantanteActualizado.getNumeroDeSencillos());
                    archivoCantante.writeInt(cantanteActualizado.getNumeroDeConciertos());
                    archivoCantante.writeInt(cantanteActualizado.getNumeroDeGiras());
                    archivoCantante.close();
                    //return true; // Actualización exitosa
                }
                pos += 88; // La longitud de cada registro es de 88 bytes
            }

            archivoCantante.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura: " + e2.getMessage());
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
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
