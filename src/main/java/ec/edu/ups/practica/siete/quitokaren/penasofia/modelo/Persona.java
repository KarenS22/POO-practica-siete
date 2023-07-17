
package ec.edu.ups.practica.siete.quitokaren.penasofia.modelo;


public abstract class Persona {
    //atributos
    private int codigo;
    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;
    double salario;

    //constructor
    
    public Persona(int codigo, String nombre, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(int codigo, String nombre, String apellido, int edad, String nacionalidad, 
            double salario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.salario = salario;
    }
    
    //getters and setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    //sobreescritura hashCode
    @Override    
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.codigo;
        return hash;
    }
    //sobreescritura equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return this.codigo == other.codigo;
    }
   
    //sobreescritura toString
    @Override
    public String toString() {
        return "Codigo: " + codigo + " Nombre: " + nombre + " Apellido: " + apellido;
    }
    
    //metodo abstracto que se modificará en las subclase
    public abstract double calcularSalario(); 
 
}
