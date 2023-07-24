package ec.edu.ups.practica.siete.quitokaren.penasofia.dao;

import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICompositorDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Compositor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositorDAO implements ICompositorDAO {
//atributos

    private List<Compositor> listaCompositor;
    private String ruta;
//constructor

    public CompositorDAO() {
        this.listaCompositor = new ArrayList<>();
        this.ruta = "src/main/resources/archivo/compositor.dat";
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
//sobreescritura metodos CRUD y buscar por Cancion

    @Override
    public void create(Compositor compositor) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "rw");
            archivoCompositor.seek(0);
            //4 b
            if (archivoCompositor.length() == 0 || archivoCompositor.length() < 375) {
                archivoCompositor.writeInt(1);
            } else {
                archivoCompositor.seek(0);
                int contReg = archivoCompositor.readInt();
                archivoCompositor.seek(0);
                archivoCompositor.writeInt(contReg + 1);
            }

            long pos = archivoCompositor.length();
            archivoCompositor.seek(pos);

            // 4 bytes
            archivoCompositor.writeInt(compositor.getCodigo());
            // 15 bytes + 2
            String nombre = rellenarEspacios(compositor.getNombre(), 15);
            archivoCompositor.writeUTF(nombre);
            // longitud de 15 bytes + 2
            String apellido = rellenarEspacios(compositor.getApellido(), 15);
            archivoCompositor.writeUTF(apellido);
            // 4 bytes
            archivoCompositor.writeInt(compositor.getEdad());
            // longitud de 25 bytes + 2
            String nacionalidad = rellenarEspacios(compositor.getNacionalidad(), 25);
            archivoCompositor.writeUTF(nacionalidad);
            // 8 bytes
            archivoCompositor.writeDouble(compositor.getSalario());
            // 4 bytes
            archivoCompositor.writeInt(compositor.getNumeroDeComposiciones());
            // segun calculos 81 bytes cada registro sin canciones y con el contador 
            // total de la primera 85 por el contador
            //canciones espacio pa 10 canciones  cada cancion pesaria 96 b y por el utf + 2b + 4b de un contDisco
            //cliente 10 cliente cada cliente pesaria 38 b + 2b UTF y 4 b de contador
            //entonces cada registro es de 1437 -4 contCan
            /*
            codigo 4b
            titulo 15 + 2 b
            letra  65 + 2 b
            tiempo 8 
             */
 /*
            cliente 
            codigo 4b
            nombre 15 + 2 b
            apellido 15 + 2b 
             */
            System.out.println(archivoCompositor.length());
            archivoCompositor.writeInt(0);
            System.out.println(archivoCompositor.length());
            archivoCompositor.writeUTF(rellenarEspacios("", 960));
            System.out.println(archivoCompositor.length());

            archivoCompositor.writeInt(0);
            System.out.println(archivoCompositor.length());
            archivoCompositor.writeUTF(rellenarEspacios("", 380));
            System.out.println(archivoCompositor.length());
            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();

            System.out.println("NUMERO DE REGISTROS " + contReg);
            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura");
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
        listaCompositor.add(compositor);
    }

    @Override
    public Compositor read(int codigoCompositor) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "r");
            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            System.out.println("Cont Can Buscar" + contReg);
            long pos = 4;
            // archivoLectura.seek(n);
            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println("codigo " + codigoL);
                if (codigoL == codigoCompositor) {
                    int newCodigo = codigoL;
                    String nombre = archivoCompositor.readUTF().trim();
                    String apellido = archivoCompositor.readUTF().trim();
                    int edad = archivoCompositor.readInt();
                    String nacionalidad = archivoCompositor.readUTF().trim();
                    double salario = archivoCompositor.readDouble();
                    int nComposiciones = archivoCompositor.readInt();
                    Compositor compositor = new Compositor(nComposiciones, newCodigo, nombre, apellido, edad, nacionalidad, salario);
                    archivoCompositor.close();
                    return compositor;
                }
                pos += 1433;
                System.out.println("pos nuevo " + pos);
            }

            archivoCompositor.close();
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
    public void update(Compositor compositor) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "rw");
            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            long pos = 4;
            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println(codigoL);
                if (codigoL == compositor.getCodigo()) {
                    // Encontramos el registro que coincide con el código, ahora actualizamos los datos
                    System.out.println(pos);
                    // archivoCantante.writeInt(cantanteActualizado.getCodigo());
                    System.out.println(pos);
                    //15 + 2 by
                    archivoCompositor.writeUTF(rellenarEspacios(compositor.getNombre(), 15));
                    //15 + 2 b
                    archivoCompositor.writeUTF(rellenarEspacios(compositor.getApellido(), 15));
                    archivoCompositor.writeInt(compositor.getEdad());
                    archivoCompositor.writeUTF(rellenarEspacios(compositor.getNacionalidad(), 25));
                    archivoCompositor.writeDouble(compositor.getSalario());
                    archivoCompositor.writeInt(compositor.getNumeroDeComposiciones());
                    archivoCompositor.close();
                    break;
                    //return true; // Actualización exitosa
                }
                pos += 1433; // La longitud de cada registro es de 375 bytes
            }

            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura: " + e2.getMessage());
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }

    }

    @Override
    public void delete(Compositor compositor) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "rw");
            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            long pos = 4;
            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println(codigoL);

                if (codigoL == compositor.getCodigo()) {
                    // Encontramos el registro que coincide con el código, ahora actualizamos los datos
                    System.out.println(pos);
                    for (int j = i; j < contReg - 1; j++) {
                        long origen = pos + 1433;
                        long destino = pos;
                        byte[] buffer = new byte[1429];
                        archivoCompositor.seek(origen);
                        archivoCompositor.read(buffer);
                        archivoCompositor.seek(destino);
                        archivoCompositor.write(buffer);
                        pos += 1433;
                    }
                    // Ajustamos la cantidad de registros en el archivo
                    archivoCompositor.setLength(archivoCompositor.length() - 1433);
                    contReg -= 1;
                    archivoCompositor.seek(0);
                    archivoCompositor.writeInt(contReg);
                    archivoCompositor.close();
                }
                pos += 1433; // La longitud de cada registro es de 375 bytes
            }

            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura: " + e2.getMessage());
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }

    }

    @Override
    public List<Compositor> findALL() {
        try {
            List<Compositor> listaCompositor = new ArrayList<>();
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "r");
            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            System.out.println("contadorCompositor: " + contReg);
            long pos = 4;

            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println("codigo " + codigoL);
                String nombre = archivoCompositor.readUTF().trim();
                String apellido = archivoCompositor.readUTF().trim();
                int edad = archivoCompositor.readInt();
                String nacionalidad = archivoCompositor.readUTF().trim();
                double salario = archivoCompositor.readDouble();
                int nComposiciones = archivoCompositor.readInt();
                System.out.println("composiciones " + nComposiciones);
                Compositor c = new Compositor(nComposiciones, codigoL, nombre, apellido, edad, nacionalidad, salario);

                listaCompositor.add(c);
                pos += 1433; // La longitud de cada registro es de 1433 bytes
                System.out.println("pos nuevo " + pos);
            }
            archivoCompositor.close();
            System.out.println(Arrays.toString(listaCompositor.toArray()));
            return listaCompositor;
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("Error de lectura: " + e2.getMessage());
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
        return null;
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

    @Override
    public void createCancion(int codigoCompositor, int codigoCancion, String titulo, String letra, double tiempoEnMinutos) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "rw");

            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            System.out.println("contadorCompositor: " + contReg);
            long pos = 4;

            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println(pos + "leida del compositor");
                System.out.println("codigo " + codigoL);
                if (codigoL == codigoCompositor) {
                    long posC = pos;
                    // 85
                    posC += 81;
                    archivoCompositor.seek(posC);
                    System.out.println("pa ingresar cancion " + posC);
                    // 89
                    int contCancion = archivoCompositor.readInt();
                    System.out.println("contCancion " + posC + " xd " + contCancion);
                    if (contCancion <= 10) {

                        archivoCompositor.seek(posC);
                        archivoCompositor.writeInt(contCancion + 1);
                        System.out.println(posC);

                        if (contCancion + 1 > 1) {
                            posC = (posC + 4) + (contCancion * 96);
                            archivoCompositor.seek(posC);
                        }
                        // Código de la canción (4 bytes)
                        archivoCompositor.writeInt(codigoCancion);
                        System.out.println(posC);

                        // Título de la canción (15 bytes + 2 bytes de longitud)
                        archivoCompositor.writeUTF(rellenarEspacios(titulo, 15));

                        // Letra de la canción (65 bytes + 2 bytes de longitud)
                        archivoCompositor.writeUTF(rellenarEspacios(letra, 65));

                        // Tiempo de la canción (8 bytes)
                        archivoCompositor.writeDouble(tiempoEnMinutos);

                        archivoCompositor.close();
                        break;
                    }
                }
                pos += 1433;
                System.out.println("pos nuevo " + pos);
            }

            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura");
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
    }

    @Override
    public Cancion readCancion(int codigoCompositor, int codigoCancion) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "r");

            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            System.out.println("contadorCompositor: " + contReg);
            long pos = 4;

            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println(pos + "leida del compositor");
                System.out.println("codigo " + codigoL);
                if (codigoL == codigoCompositor) {
                    long posC = pos;
                    // 127
                    posC += 81;
                    archivoCompositor.seek(posC);
                    System.out.println("pa ver cont cancion " + posC);
                    // 131
                    int contCancion = archivoCompositor.readInt();
                    System.out.println("contCancion " + posC + " xd " + contCancion);
                    long posBC = posC + 4;
                    for (int j = 0; j < contCancion; j++) {
                        archivoCompositor.seek(posBC);
                        // 4 bytes para el código de la canción
                        int codigoC = archivoCompositor.readInt();
                        if (codigoC == codigoCancion) {
                            // 15 bytes + 2 bytes para el título de la canción
                            String titulo = archivoCompositor.readUTF().trim();
                            // 65 bytes + 2 bytes para la letra de la canción
                            String letra = archivoCompositor.readUTF().trim();
                            // 8 bytes para el tiempo de la canción
                            double tiempo = archivoCompositor.readDouble();
                            archivoCompositor.close();
                            return new Cancion(codigoC, titulo, letra, tiempo);
                        }
                        posBC += 96;
                    }
                }
                pos += 1437;
                System.out.println("pos nuevo " + pos);
            }
            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura");
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
        return null;
    }

    @Override
    public void updateCancion(int codigoCompositor, int codigoCancion, String titulo, String letra, double tiempo) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "rw");

            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            System.out.println("contadorCompositor: " + contReg);
            long pos = 4;

            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println(pos + "leida del compositor");
                System.out.println("codigo " + codigoL);
                if (codigoL == codigoCompositor) {
                    long posC = pos;
                    // 127
                    posC += 81;
                    archivoCompositor.seek(posC);
                    System.out.println("pa ver cont cancion " + posC);
                    // 131
                    int contCancion = archivoCompositor.readInt();
                    System.out.println("contCancion " + posC + " xd " + contCancion);
                    long posBC = posC + 4;
                    for (int j = 0; j < contCancion; j++) {
                        archivoCompositor.seek(posBC);
                        // 4 bytes para el código de la canción
                        int codigoC = archivoCompositor.readInt();
                        System.out.println(codigoC);
                        if (codigoC == codigoCancion) {
                            // Actualizamos los datos de la canción
                            // 15 bytes + 2 bytes para el título de la canción
                            archivoCompositor.writeUTF(rellenarEspacios(titulo, 15));
                            // 65 bytes + 2 bytes para la letra de la canción
                            archivoCompositor.writeUTF(rellenarEspacios(letra, 65));
                            // 8 bytes para el tiempo de la canción
                            archivoCompositor.writeDouble(tiempo);
                            archivoCompositor.close();
                            return;
                        }
                        posBC += 96;
                    }
                }
                pos += 1433;
                System.out.println("pos nuevo " + pos);
            }
            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura");
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
    }

    @Override
    public void deleteCancion(int codigoCompositor, int codigoCancion) {
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "rw");

            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            System.out.println("contadorCompositor: " + contReg);
            long pos = 4;

            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println(pos + "leida del compositor");
                System.out.println("codigo " + codigoL);
                if (codigoL == codigoCompositor) {
                    long posC = pos;
                    // 127
                    posC += 81;
                    archivoCompositor.seek(posC);
                    System.out.println("pa ver cont cancion " + posC);
                    // 131
                    int contCancion = archivoCompositor.readInt();
                    System.out.println("contCancion " + posC + " xd " + contCancion);
                    long posBC = posC + 4;
                    for (int j = 0; j < contCancion; j++) {
                        archivoCompositor.seek(posBC);
                        // 4 bytes para el código de la canción
                        int codigoC = archivoCompositor.readInt();
                        System.out.println(codigoC);
                        if (codigoC == codigoCancion) {
                            // Marcamos el código de la canción como negativo para indicar que está eliminada
                            archivoCompositor.seek(posBC);
                            archivoCompositor.writeInt(-codigoCancion);
                            // 15 bytes + 2 bytes para el título de la canción, marcado como vacío
                            archivoCompositor.writeUTF(rellenarEspacios("Eliminado", 15));
                            // 65 bytes + 2 bytes para la letra de la canción, marcado como vacío
                            archivoCompositor.writeUTF(rellenarEspacios("", 65));
                            // 8 bytes para el tiempo de la canción, marcado como -1
                            archivoCompositor.writeDouble(-1);
                            archivoCompositor.close();
                            return;
                        }
                        posBC += 96;
                    }
                }
                pos += 1433;
                System.out.println("pos nuevo " + pos);
            }
            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura");
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());

        }
    }

    @Override
    public List<Cancion> findAllCancion(int codigoCompositor) {
        List<Cancion> listaCancion = new ArrayList<>();
        try {
            RandomAccessFile archivoCompositor;
            archivoCompositor = new RandomAccessFile(ruta, "rw");

            archivoCompositor.seek(0);
            int contReg = archivoCompositor.readInt();
            System.out.println("contadorCompositor: " + contReg);
            long pos = 4;

            for (int i = 0; i < contReg; i++) {
                archivoCompositor.seek(pos);
                int codigoL = archivoCompositor.readInt();
                System.out.println(pos + "leida del compositor");
                System.out.println("codigo " + codigoL);
                if (codigoL == codigoCompositor) {
                    long posC = pos;
                    // 127
                    posC += 81;
                    archivoCompositor.seek(posC);
                    System.out.println("pa ver cont cancion " + posC);
                    // 131
                    int contCancion = archivoCompositor.readInt();
                    System.out.println("contCancion " + posC + " xd " + contCancion);
                    long posBC = posC + 4;
                    for (int j = 0; j < contCancion; j++) {
                        archivoCompositor.seek(posBC);
                        // 4 bytes para el código de la canción
                        int codigoC = archivoCompositor.readInt();
                        System.out.println(codigoC);
                        if (codigoC > 0) { // Canciones con código positivo, es decir, que no han sido eliminadas
                            // 15 bytes + 2 bytes para el título de la canción
                            String titulo = archivoCompositor.readUTF().trim();
                            // 65 bytes + 2 bytes para la letra de la canción
                            String letra = archivoCompositor.readUTF().trim();
                            // 8 bytes para el tiempo de la canción
                            double tiempo = archivoCompositor.readDouble();
                            listaCancion.add(new Cancion(codigoC, titulo, letra, tiempo));
                            System.out.println(listaCancion);
                        }
                        posBC += 96;
                    }
                }
                pos += 1433;
                System.out.println("pos nuevo " + pos);
            }
            archivoCompositor.close();
        } catch (FileNotFoundException e1) {
            System.out.println("Ruta no encontrada");
        } catch (IOException e2) {
            System.out.println("Error de lectura/escritura: " + e2.getMessage());
        } catch (Exception e3) {
            System.out.println("Error general: " + e3.getMessage());
        }
        return listaCancion;
    }

    @Override
    public void agregarCliente(int codigoCompositor, Cantante cliente) {
    try {
        RandomAccessFile archivoCompositor;
        archivoCompositor = new RandomAccessFile(ruta, "rw");

        archivoCompositor.seek(0);
        int contReg = archivoCompositor.readInt();
        System.out.println("contadorCompositor: " + contReg);

        long pos = 4;

        for (int i = 0; i < contReg; i++) {
            archivoCompositor.seek(pos);
            int codigoL = archivoCompositor.readInt();
            System.out.println(pos + "leida del compositor");
            System.out.println("codigo " + codigoL);
            if (codigoL == codigoCompositor) {
                long posC = pos;
                // 85
                posC += 1051;
                archivoCompositor.seek(posC);
                System.out.println("pa ingresar cliente " + posC);
                // 89
                int contCliente = archivoCompositor.readInt();
                System.out.println("contCliente " + posC + " xd " + contCliente);
                if (contCliente <= 10) {

                    archivoCompositor.seek(posC);
                    archivoCompositor.writeInt(contCliente + 1);
                    System.out.println(posC);

                    if (contCliente + 1 > 1) {
                        posC = (posC + 4) + (contCliente * 38);
                        archivoCompositor.seek(posC);
                    }
                    // Código del cliente (4 bytes)
                    archivoCompositor.writeInt(cliente.getCodigo());
                    System.out.println(posC);

                    // Nombre del cliente (15 bytes + 2 bytes de longitud)
                    archivoCompositor.writeUTF(rellenarEspacios(cliente.getNombre(), 15));

                    // Apellido del cliente (15 bytes + 2 bytes de longitud)
                    archivoCompositor.writeUTF(rellenarEspacios(cliente.getApellido(), 15));

                    archivoCompositor.close();
                    break;
                }
            }
            pos += 1433;
            System.out.println("pos nuevo " + pos);
        }

        archivoCompositor.close();
    } catch (FileNotFoundException e1) {
        System.out.println("Ruta no encontrada");
    } catch (IOException e2) {
        System.out.println("Error de lectura/escritura");
    } catch (Exception e3) {
        System.out.println("Error general: " + e3.getMessage());
    }
}


    @Override
    public void actualizarCliente(int codigoCompositor, int codigoCantante, String nombre, String apellido) {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarCliente(int codigoCompositor, int codigoCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cantante> listarClientes(int codigoCompositor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cantante buscarCli(int codigoCompositor, int idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
