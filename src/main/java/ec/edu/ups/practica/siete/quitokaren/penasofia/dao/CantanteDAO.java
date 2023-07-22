package ec.edu.ups.practica.siete.quitokaren.penasofia.dao;

import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICantanteDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Disco;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CantanteDAO implements ICantanteDAO {

//atributo
    private int contadorRegistro;
    private String ruta;
    private List<Cantante> listaCantante;
//constructor

    public CantanteDAO() {
        this.listaCantante = new ArrayList<>();
        this.contadorRegistro = 0;
        this.ruta = "src/main/resources/archivo/cantante.dat";
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
            RandomAccessFile archivoCrearCantante;
            archivoCrearCantante = new RandomAccessFile(ruta, "rw");
            archivoCrearCantante.seek(0);
            //4 b
            if (archivoCrearCantante.length() == 0 || archivoCrearCantante.length() < 375) {
                archivoCrearCantante.writeInt(1);
            } else {
                archivoCrearCantante.seek(0);
                int contReg = archivoCrearCantante.readInt();
                archivoCrearCantante.seek(0);
                archivoCrearCantante.writeInt(contReg + 1);
            }

            long pos = archivoCrearCantante.length();
            archivoCrearCantante.seek(pos);

            // 4 bytes
            archivoCrearCantante.writeInt(cantante.getCodigo());
            // 15 bytes + 2
            String nombre = rellenarEspacios(cantante.getNombre(), 15);
            archivoCrearCantante.writeUTF(nombre);
            // longitud de 15 bytes + 2
            String apellido = rellenarEspacios(cantante.getApellido(), 15);
            archivoCrearCantante.writeUTF(apellido);
            // 4 bytes
            archivoCrearCantante.writeInt(cantante.getEdad());
            // longitud de 25 bytes + 2
            String nacionalidad = rellenarEspacios(cantante.getNacionalidad(), 25);
            archivoCrearCantante.writeUTF(nacionalidad);
            // 8 bytes
            archivoCrearCantante.writeDouble(cantante.getSalario());
            // longitud de 20 bytes + 2
            String nombreArtistico = rellenarEspacios(cantante.getNombreArtistico(), 20);
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
            // segun calculos 123 bytes cada registro sin canciones y con el contador 
            // total de la primera 127 por el contador
            //disco espacio pa 10 discos  cada disco pesaria 25 b y por el utf + 2b + 4b de un contDisco
            //entonces cada registro es de 383 -4 contCan
            /*
            codigo 4b
            nombre 15 + 2 b
            anio lanza 4b 
             */
            System.out.println(archivoCrearCantante.length());
            archivoCrearCantante.writeInt(0);
            System.out.println(archivoCrearCantante.length());
            archivoCrearCantante.writeUTF(rellenarEspacios("", 250));
            System.out.println(archivoCrearCantante.length());
            archivoCrearCantante.seek(0);
            int contReg = archivoCrearCantante.readInt();

            System.out.println("NUMERO DE REGISTROS " + contReg);
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
    @Override
    public Cantante read(int codigo) {
        try {
            RandomAccessFile archivoLecturaCantante;
            archivoLecturaCantante = new RandomAccessFile(ruta, "r");
            archivoLecturaCantante.seek(0);
            int contReg = archivoLecturaCantante.readInt();
            System.out.println("Cont Can Buscar"+contReg);
            long pos = 4;
            // archivoLectura.seek(n);

            for (int i = 0; i < contReg; i++) {
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
                pos += 379;
                System.out.println("pos nuevo " + pos);

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
            RandomAccessFile archivoCantante;
            archivoCantante = new RandomAccessFile(ruta, "rw");
            archivoCantante.seek(0);
            int contReg = archivoCantante.readInt();
            long pos = 4;
            for (int i = 0; i < contReg; i++) {
                archivoCantante.seek(pos);
                int codigoL = archivoCantante.readInt();
                System.out.println(codigoL);
                if (codigoL == cantanteActualizado.getCodigo()) {
                    // Encontramos el registro que coincide con el código, ahora actualizamos los datos
                    System.out.println(pos);
                    // archivoCantante.writeInt(cantanteActualizado.getCodigo());
                    System.out.println(pos);
                    //15 + 2 by
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getNombre(), 15));
                    //15 + 2 b
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getApellido(), 15));
                    archivoCantante.writeInt(cantanteActualizado.getEdad());
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getNacionalidad(), 25));
                    archivoCantante.writeDouble(cantanteActualizado.getSalario());
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getNombreArtistico(), 20));
                    archivoCantante.writeUTF(rellenarEspacios(cantanteActualizado.getGeneroMusical(), 10));
                    archivoCantante.writeInt(cantanteActualizado.getNumeroDeSencillos());
                    archivoCantante.writeInt(cantanteActualizado.getNumeroDeConciertos());
                    archivoCantante.writeInt(cantanteActualizado.getNumeroDeGiras());
                    archivoCantante.close();
                    //return true; // Actualización exitosa
                }
                pos += 379; // La longitud de cada registro es de 375 bytes
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
        try {
            RandomAccessFile archivoCantante;
            archivoCantante = new RandomAccessFile(ruta, "rw");
            archivoCantante.seek(0);
            int contReg = archivoCantante.readInt();
            long pos = 4;
            for (int i = 0; i < contReg; i++) {
                archivoCantante.seek(pos);
                int codigoL = archivoCantante.readInt();
                System.out.println(codigoL);

                if (codigoL == cantante.getCodigo()) {
                    // Encontramos el registro que coincide con el código, ahora actualizamos los datos
                    System.out.println(pos);
                    for (int j = i; j < contReg - 1; j++) {
                        long origen = pos + 379;
                        long destino = pos;
                        byte[] buffer = new byte[375];
                        archivoCantante.seek(origen);
                        archivoCantante.read(buffer);
                        archivoCantante.seek(destino);
                        archivoCantante.write(buffer);
                        pos += 379;
                    }
                    // Ajustamos la cantidad de registros en el archivo
                    archivoCantante.setLength(archivoCantante.length() - 375);
                    contReg -= 1;
                    archivoCantante.seek(0);
                    archivoCantante.writeInt(contReg);
                    archivoCantante.close();
                }
                pos += 379; // La longitud de cada registro es de 375 bytes
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
    public List<Cantante> findALL() {
        try {
            List<Cantante> listaCantante = new ArrayList<>();
            RandomAccessFile archivoLecturaCantante;
            archivoLecturaCantante = new RandomAccessFile(ruta, "r");
            archivoLecturaCantante.seek(0);
            int contReg = archivoLecturaCantante.readInt();
            System.out.println(contReg);
            long pos = 4;

            for (int i = 0; i < contReg; i++) {
                System.out.println("entro?");
                archivoLecturaCantante.seek(pos);
                int codigoL = archivoLecturaCantante.readInt();
                System.out.println("codigo " + codigoL);
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

                Cantante c = new Cantante(nombreArtistico, generoMusical, nSencillos, nConciertos, nGiras, codigoL, nombre, apellido, edad, nacionalidad, salario);

                listaCantante.add(c);
                //archivoLecturaCantante.close();

                pos += 379;
                System.out.println("pos nuevo " + pos);

            }
            archivoLecturaCantante.close();
            System.out.println(Arrays.toString(listaCantante.toArray()));
            return listaCantante;
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrado " + e1);
        } catch (IOException e2) {
            System.out.println("Error de lectura " + e2 + e2.getMessage());
        } catch (Exception e3) {
            System.out.println("Eror General " + e3);
        }
        return null;
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

    @Override
    public void createDisco(int codigoCantante, int codigoDisco, String nombre, int anioDeLanzamiento) {
        try {
            System.out.println(codigoCantante);
            RandomAccessFile archivoCantante;
            archivoCantante = new RandomAccessFile(ruta, "rw");

            archivoCantante.seek(0);
            int contReg = archivoCantante.readInt();
            System.out.println("contadorCantante: "+contReg);
            long pos = 4;
            
            for (int i = 0; i < contReg; i++) {
                
                archivoCantante.seek(pos);
                int codigoL = archivoCantante.readInt();
                System.out.println(pos + "leida del can");
                //4 b   383
                System.out.println("codigo " + codigoL);
                if (codigoL == codigoCantante) {
                    long posD = pos;
                    //127  506
                    posD += 123;
                    archivoCantante.seek(posD);
                    System.out.println("pa ingresar disco "+posD);
                    //131
                    int contDisco = archivoCantante.readInt();
                    System.out.println("contDisco "+posD + " xd "+contDisco);
                    if (contDisco <= 10) {
                        //127
                        
                        //131
                        archivoCantante.seek(posD);
                        archivoCantante.writeInt(contDisco + 1);
                        System.out.println(posD);
                        
                        if (contDisco+1 > 1){
                            posD = posD + (contDisco*25);
                            archivoCantante.seek(posD);
                        }
                        //codigo Disco 4 b   135
                        archivoCantante.writeInt(codigoDisco);
                        System.out.println(posD);
                        // nombre 15 b + 2     152
                        archivoCantante.writeUTF(rellenarEspacios(nombre, 15));
                        //System.out.println(pos);
                        // anio de lanzamiento 4 b    156
                        archivoCantante.writeInt(anioDeLanzamiento);
                       // System.out.println(pos);
                        //registro de disco 25 
                        
                        archivoCantante.close();
                        break;
                    }
                }
                pos += 379;
                System.out.println("pos nuevo " + pos);

            }

            archivoCantante.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura");
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
    }

    @Override
    public Disco readDisco(Cantante cantante, int codigoDisco) {
        return cantante.buscarDisco(codigoDisco);
    }

    @Override
    public void updateDisco(Cantante cantante, int codigo, String nombre, int anioDeLanzamiento) {
        cantante.actualizarDisco(codigo, nombre, anioDeLanzamiento);
    }

    @Override
    public void deleteDisco(Cantante cantante, int codigo) {
        Disco d = this.readDisco(cantante, codigo);
        cantante.eliminarDisco(d);
    }

    @Override
    public List<Disco> findAllDisco(Cantante cantante) {
        return cantante.listaDisco();
    }

}
