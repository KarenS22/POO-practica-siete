
package ec.edu.ups.practica.siete.quitokaren.penasofia.modelo;

import java.util.ArrayList;
import java.util.List;

public class Compositor extends Persona {
     //atributos
    private int numeroDeComposiciones;
    private List<Cancion> cancionesTop100Billboard;
    private List<Cantante> clientes;

    //constructores
    public Compositor(int codigo, String nombre, String apellido) {
        super(codigo, nombre, apellido);
    }
    
    public Compositor(int numeroDeComposiciones, int codigo, String nombre, String apellido, int edad,
            String nacionalidad, double salario) {
        super(codigo, nombre, apellido, edad, nacionalidad, salario);
        this.numeroDeComposiciones = numeroDeComposiciones;
        this.cancionesTop100Billboard =new ArrayList();
        this.clientes = new ArrayList();
    }

    //sobreescritura metodo calcularSalario, dependiendo las condiciones el salario aumenta
    @Override
    public double calcularSalario() {
        double salarioFinal = getSalario();
        if (numeroDeComposiciones >= 5){
            double comisicion = 300;
            salarioFinal += comisicion;
        } else if (cancionesTop100Billboard.size()>=1 && cancionesTop100Billboard.size()<=3){
            double aumento = salarioFinal * 0.1;
            salarioFinal += aumento;
        } else if(cancionesTop100Billboard.size()>=4 && cancionesTop100Billboard.size()<=6){
            double comision2 = (salarioFinal * 0.2);
            salarioFinal += comision2;
        } else if (cancionesTop100Billboard.size()> 6){
            double aumento2 = (salarioFinal * 0.2);
            salarioFinal += aumento2;
        }
        this.salario = salarioFinal;
        return this.salario;
    }

    /*
    metodo agregarCancion donde se tiene que poner 4 parametros de entrada que crearan 
    un objeto cancion que luego sera agregado a la lista cancionesTop100Billboard
    */

    public void agregarCancion(int codigoCancion, String titulo, String letra, double tiempoEnMinutos){
        cancionesTop100Billboard.add(new Cancion(codigoCancion, titulo, letra, tiempoEnMinutos));
    }
    //metodo si se quiere actualizar los datos de de las canciones
    public void actualizarCancion(int codigoCancion, String titulo, String letra, double tiempoEnMinutos){
        for (int i = 0; i < cancionesTop100Billboard.size(); i++) {
            if (cancionesTop100Billboard.get(i).getCodigo() == codigoCancion){
                Cancion cancion = cancionesTop100Billboard.get(i);
                cancion.setTitulo(titulo);
                cancion.setLetra(letra);
                cancion.setTiempoEnMinutos(tiempoEnMinutos);
            }
        }
    }
    // metodo para eliminar la cancion
    public void eliminarCancion(Cancion cancion){
        if (cancionesTop100Billboard.contains(cancion)){
            int index = cancionesTop100Billboard.indexOf(cancion);
            cancionesTop100Billboard.remove(index);
        }
    }
    // metod que devuelve una lista de canciones
    public List<Cancion> listarCanciones(){
        return cancionesTop100Billboard;
    }
    
    public Cancion buscarCancion(int id){
        for (int i = 0; i < cancionesTop100Billboard.size(); i++) {
            if (cancionesTop100Billboard.get(i).getCodigo() == id){
                return cancionesTop100Billboard.get(i);
            }   
        }
        return null;
    }
    /*
    metodo agregarCliente se pone un objeto tipo Cantante que luego sera 
    agregado a la lista clientes
    */
    public void agregarCliente(Cantante cliente){
        clientes.add(cliente);
    }
    //metodo actualiza datos de cliente
    public void actualizarCliente(int codigoCantante, String nombre, String apellido){
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCodigo() == codigoCantante){
                Cantante c = clientes.get(i);
                c.setNombre(nombre);
                c.setApellido(apellido);      
            }
        }
    }
    //metodo elimina cliente
    public void eliminarCliente(Cantante cantante){
         if (clientes.contains(cantante)){
            int index = clientes.indexOf(cantante);
            clientes.remove(index);
        }
    }
    //lista de clientes
    public List<Cantante> listarClientes(){
        return clientes;
    }
    public Cantante buscarCli(int id){
       for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCodigo() == id){
                return clientes.get(i);
            }   
        }
        return null;
    }
       //getters and setters
    public int getNumeroDeComposiciones() {
        return numeroDeComposiciones;
    }
    public void setNumeroDeComposiciones(int numeroDeComposiciones) {
        this.numeroDeComposiciones = numeroDeComposiciones;
    }

    //sobreescritura toString
    @Override
    public String toString() {
        return "Compositor{" + super.toString() + "numeroDeComposiciones=" + numeroDeComposiciones + ","
                + " cancionesTop100Billboard=" + cancionesTop100Billboard + ", clientes=" + clientes + '}';
    } 
}
