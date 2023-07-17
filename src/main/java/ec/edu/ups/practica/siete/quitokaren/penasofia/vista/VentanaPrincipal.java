/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package ec.edu.ups.practica.siete.quitokaren.penasofia.vista;

import ec.edu.ups.practica.siete.quitokaren.penasofia.controlador.ControladorCantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.controlador.ControladorCompositor;
import ec.edu.ups.practica.siete.quitokaren.penasofia.dao.CantanteDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.dao.CompositorDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICantanteDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.idao.ICompositorDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cancion.VentanaActualizarCancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cancion.VentanaAgregarCancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cancion.VentanaBuscarCancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cancion.VentanaEliminarCancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cancion.VentanaListarCancion;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cantante.VentanaActualizarCantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cantante.VentanaBuscarCantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cantante.VentanaCrearCantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cantante.VentanaEliminarCantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cantante.VentanaListarCantante;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cliente.VentanaActualizarCliente;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cliente.VentanaAgregarCliente;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cliente.VentanaBuscarCliente;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cliente.VentanaEliminarCliente;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.cliente.VentanaListarClientes;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.compositor.VentanaActualizarCompositor;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.compositor.VentanaBuscarCompositor;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.compositor.VentanaCrearCompositor;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.compositor.VentanaEliminarCompositor;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.compositor.VentanaListarCompositor;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.disco.VentanaActualizarDisco;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.disco.VentanaAgregarDisco;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.disco.VentanaBuscarDisco;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.disco.VentanaEliminarDisco;
import ec.edu.ups.practica.siete.quitokaren.penasofia.vista.disco.VentanaListarDisco;
import java.util.Locale;
import java.util.ResourceBundle;


public class VentanaPrincipal extends javax.swing.JFrame {
    //cantante ventanas
    private VentanaCrearCantante ventanaCrearCantante;
    private VentanaBuscarCantante ventanaBuscarCantante;
    private VentanaActualizarCantante ventanaActualizarCantante;
    private VentanaEliminarCantante  ventanaEliminarCantante;
    private VentanaListarCantante ventanaListarCantante;
    
    //ventana disco
    private VentanaAgregarDisco ventanaAgregarDisco;
    private VentanaBuscarDisco ventanaBuscarDisco;
    private VentanaActualizarDisco ventanaActualizarDisco;
    private VentanaEliminarDisco ventanaEliminarDisco;
    private VentanaListarDisco ventanaListarDisco;
    
    //ventana compositor
    private VentanaCrearCompositor ventanaCrearCompositor;
    private VentanaBuscarCompositor ventanaBuscarCompositor;
    private VentanaActualizarCompositor ventanaActualizarCompositor;
    private VentanaEliminarCompositor ventanaEliminarCompositor;
    private VentanaListarCompositor ventanaListarCompositor;
    
    private VentanaAgregarCancion ventanaAgregarCancion;
    private VentanaBuscarCancion ventanaBuscarCancion;
    private VentanaActualizarCancion ventanaActualizarCancion;
    private VentanaEliminarCancion ventanaEliminarCancion;
    private VentanaListarCancion ventanaListarCancion;
    
    private VentanaAgregarCliente ventanaAgregarCliente;
    private VentanaBuscarCliente ventanaBuscarCliente;
    private VentanaActualizarCliente ventanaActualizarCliente;
    private VentanaEliminarCliente ventanaEliminarCliente;
    private VentanaListarClientes ventanaListarClientes;
    
    private ControladorCantante controladorCantante;
    private ControladorCompositor controladorCompositor;
    
    private ICompositorDAO compositorDAO;
    private ICantanteDAO cantanteDAO;
    
     //Internacionalizacion
    private Locale localizacion;
    private ResourceBundle mensajes;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        cantanteDAO = new CantanteDAO();
        controladorCantante = new ControladorCantante(cantanteDAO);
        compositorDAO = new CompositorDAO();
        controladorCompositor = new ControladorCompositor(compositorDAO);
        localizacion = Locale.getDefault();
        cambiarIdioma();
    }
    
    private void cambiarIdioma(){
        
        mensajes = ResourceBundle.getBundle("mensajes.mensaje", localizacion);
        menuCantante.setText(mensajes.getString("menu.cantante"));
        menuOperadora.setText(mensajes.getString("menu.compositor"));
        menuDisco.setText(mensajes.getString("menu.disco"));
        menuOpciones.setText(mensajes.getString("menu.opciones"));
        menuItemCrearCantante.setText(mensajes.getString("menu.item.crear"));
        menuItemBuscarCantante.setText(mensajes.getString("menu.item.buscar"));
        menuItemActualizarCantante.setText(mensajes.getString("menu.item.actualizar"));
        menuItemEliminarCantante.setText(mensajes.getString("menu.item.eliminar"));
        menuItemListarCantante.setText(mensajes.getString("menu.item.listar"));
        menuItemCrearDisco.setText(mensajes.getString("menu.item.crear"));
        menuItemBuscarDisco.setText(mensajes.getString("menu.item.buscar"));
        menuItemActualizarDisco.setText(mensajes.getString("menu.item.actualizar"));
        menuItemEliminarDisco.setText(mensajes.getString("menu.item.eliminar"));
        menuItemListarDisco.setText(mensajes.getString("menu.item.listar"));
        menuItemCrearCompositor.setText(mensajes.getString("menu.item.crear"));
        menuItemBuscarCompositor.setText(mensajes.getString("menu.item.buscar"));
        menuItemActualizarCompositor.setText(mensajes.getString("menu.item.actualizar"));
        menuItemEliminarCompositor.setText(mensajes.getString("menu.item.eliminar"));
        menuItemListarCompositor.setText(mensajes.getString("menu.item.listar"));
        menuItemCrearCancion.setText(mensajes.getString("menu.item.crear"));
        menuItemBuscarCancion.setText(mensajes.getString("menu.item.buscar"));
        menuItemActualizarCancion.setText(mensajes.getString("menu.item.actualizar"));
        menuItemEliminarCancion.setText(mensajes.getString("menu.item.eliminar"));
        menuItemListarCancion.setText(mensajes.getString("menu.item.listar"));
        menuItemCrearCliente.setText(mensajes.getString("menu.item.crear"));
        menuItemBuscarCliente.setText(mensajes.getString("menu.item.buscar"));
        menuItemActualizarCliente.setText(mensajes.getString("menu.item.actualizar"));
        menuItemEliminarCliente.setText(mensajes.getString("menu.item.eliminar"));
        menuItemListarCliente.setText(mensajes.getString("menu.item.listar"));
        lblCompositor.setText(mensajes.getString("menu.compositor"));
        jlblCantante.setText(mensajes.getString("menu.cantante"));
        menuCancion.setText(mensajes.getString("menu.cancion"));
        menuCliente.setText(mensajes.getString("menu.cliente"));
        menuIdiomas.setText(mensajes.getString("menu.idioma"));
        menuItemSalirOpciones.setText(mensajes.getString("menu.item.salir"));
        menuItemEspanol.setText(mensajes.getString("menu.item.espanol"));
        menuItemIngles.setText(mensajes.getString("menu.item.ingles"));
        menuItemFrances.setText(mensajes.getString("menu.item.frances"));
        if(ventanaCrearCantante != null){
            ventanaCrearCantante.cambiarIdioma(localizacion);   
        }
        if(ventanaBuscarCantante != null){
            ventanaBuscarCantante.cambiarIdioma(localizacion);   
        }
        if(ventanaActualizarCantante != null){
            ventanaActualizarCantante.cambiarIdioma(localizacion);   
        }
        if(ventanaEliminarCantante != null){
            ventanaEliminarCantante.cambiarIdioma(localizacion);   
        }
        if(ventanaListarCantante != null){
            ventanaListarCantante.cambiarIdioma(localizacion);   
        }
        if(ventanaAgregarDisco != null){
            ventanaAgregarDisco.cambiarIdioma(localizacion);   
        }
        if(ventanaActualizarDisco != null){
            ventanaActualizarDisco.cambiarIdioma(localizacion);   
        }
        if(ventanaBuscarDisco != null){
            ventanaBuscarDisco.cambiarIdioma(localizacion);   
        }
        if(ventanaEliminarDisco != null){
            ventanaEliminarDisco.cambiarIdioma(localizacion);   
        }
        if (ventanaListarDisco != null){
            ventanaListarDisco.cambiarIdioma(localizacion);
        }
        if (ventanaCrearCompositor != null){
            ventanaCrearCompositor.cambiarIdioma(localizacion);
        }
        if(ventanaBuscarCompositor != null){
            ventanaBuscarCompositor.cambiarIdioma(localizacion);   
        }
        if(ventanaActualizarCompositor != null){
            ventanaActualizarCompositor.cambiarIdioma(localizacion);   
        }
        if(ventanaEliminarCompositor != null){
            ventanaEliminarCompositor.cambiarIdioma(localizacion);   
        }
        if(ventanaListarCompositor != null){
            ventanaListarCompositor.cambiarIdioma(localizacion);
        }
        if(ventanaAgregarCancion != null){
            ventanaAgregarCancion.cambiarIdioma(localizacion);
        }
        if(ventanaBuscarCancion != null){
            ventanaBuscarCancion.cambiarIdioma(localizacion);
        }
        if(ventanaActualizarCancion != null){
            ventanaActualizarCancion.cambiarIdioma(localizacion);
        }
        if(ventanaEliminarCancion != null){
            ventanaEliminarCancion.cambiarIdioma(localizacion);
        }
        if(ventanaListarCancion != null){
            ventanaListarCancion.cambiarIdioma(localizacion);
        }
        if(ventanaAgregarCliente != null){
            ventanaAgregarCliente.cambiarIdioma(localizacion);
        }
        if(ventanaBuscarCliente != null){
            ventanaBuscarCliente.cambiarIdioma(localizacion);
        }
        if(ventanaActualizarCliente != null){
            ventanaActualizarCliente.cambiarIdioma(localizacion);
        }
        if(ventanaEliminarCliente != null){
            ventanaEliminarCliente.cambiarIdioma(localizacion);
        }
        if(ventanaListarClientes != null){
            ventanaListarClientes.cambiarIdioma(localizacion);
        }
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        jlblLogo = new javax.swing.JLabel();
        jlblCantante = new javax.swing.JLabel();
        lblCompositor = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuCantante = new javax.swing.JMenu();
        menuItemCrearCantante = new javax.swing.JMenuItem();
        menuItemBuscarCantante = new javax.swing.JMenuItem();
        menuItemActualizarCantante = new javax.swing.JMenuItem();
        menuItemEliminarCantante = new javax.swing.JMenuItem();
        menuItemListarCantante = new javax.swing.JMenuItem();
        menuDisco = new javax.swing.JMenu();
        menuItemCrearDisco = new javax.swing.JMenuItem();
        menuItemBuscarDisco = new javax.swing.JMenuItem();
        menuItemActualizarDisco = new javax.swing.JMenuItem();
        menuItemEliminarDisco = new javax.swing.JMenuItem();
        menuItemListarDisco = new javax.swing.JMenuItem();
        menuOperadora = new javax.swing.JMenu();
        menuItemCrearCompositor = new javax.swing.JMenuItem();
        menuItemBuscarCompositor = new javax.swing.JMenuItem();
        menuItemActualizarCompositor = new javax.swing.JMenuItem();
        menuItemEliminarCompositor = new javax.swing.JMenuItem();
        menuItemListarCompositor = new javax.swing.JMenuItem();
        menuCancion = new javax.swing.JMenu();
        menuItemCrearCancion = new javax.swing.JMenuItem();
        menuItemBuscarCancion = new javax.swing.JMenuItem();
        menuItemActualizarCancion = new javax.swing.JMenuItem();
        menuItemEliminarCancion = new javax.swing.JMenuItem();
        menuItemListarCancion = new javax.swing.JMenuItem();
        menuCliente = new javax.swing.JMenu();
        menuItemCrearCliente = new javax.swing.JMenuItem();
        menuItemBuscarCliente = new javax.swing.JMenuItem();
        menuItemActualizarCliente = new javax.swing.JMenuItem();
        menuItemEliminarCliente = new javax.swing.JMenuItem();
        menuItemListarCliente = new javax.swing.JMenuItem();
        menuOpciones = new javax.swing.JMenu();
        menuItemSalirOpciones = new javax.swing.JMenuItem();
        menuIdiomas = new javax.swing.JMenu();
        menuItemEspanol = new javax.swing.JMenuItem();
        menuItemIngles = new javax.swing.JMenuItem();
        menuItemFrances = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo v.png"))); // NOI18N
        desktopPane.add(jlblLogo);
        jlblLogo.setBounds(1070, 0, 210, 180);

        jlblCantante.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlblCantante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Music-User.256.png"))); // NOI18N
        jlblCantante.setText("Cantante");
        desktopPane.add(jlblCantante);
        jlblCantante.setBounds(90, 140, 350, 250);

        lblCompositor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCompositor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Music-User.256.png"))); // NOI18N
        lblCompositor.setText("Compositor");
        desktopPane.add(lblCompositor);
        lblCompositor.setBounds(480, 140, 380, 240);

        menuCantante.setMnemonic('f');
        menuCantante.setText("Cantante");

        menuItemCrearCantante.setMnemonic('o');
        menuItemCrearCantante.setText("Crear");
        menuItemCrearCantante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCrearCantanteActionPerformed(evt);
            }
        });
        menuCantante.add(menuItemCrearCantante);

        menuItemBuscarCantante.setMnemonic('a');
        menuItemBuscarCantante.setText("Buscar");
        menuItemBuscarCantante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBuscarCantanteActionPerformed(evt);
            }
        });
        menuCantante.add(menuItemBuscarCantante);

        menuItemActualizarCantante.setMnemonic('s');
        menuItemActualizarCantante.setText("Actualizar");
        menuItemActualizarCantante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemActualizarCantanteActionPerformed(evt);
            }
        });
        menuCantante.add(menuItemActualizarCantante);

        menuItemEliminarCantante.setMnemonic('x');
        menuItemEliminarCantante.setText("Eliminar");
        menuItemEliminarCantante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarCantanteActionPerformed(evt);
            }
        });
        menuCantante.add(menuItemEliminarCantante);

        menuItemListarCantante.setText("Listar");
        menuItemListarCantante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListarCantanteActionPerformed(evt);
            }
        });
        menuCantante.add(menuItemListarCantante);

        menuDisco.setMnemonic('h');
        menuDisco.setText("Disco");

        menuItemCrearDisco.setMnemonic('c');
        menuItemCrearDisco.setText("Crear");
        menuItemCrearDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCrearDiscoActionPerformed(evt);
            }
        });
        menuDisco.add(menuItemCrearDisco);

        menuItemBuscarDisco.setText("Buscar");
        menuItemBuscarDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBuscarDiscoActionPerformed(evt);
            }
        });
        menuDisco.add(menuItemBuscarDisco);

        menuItemActualizarDisco.setMnemonic('a');
        menuItemActualizarDisco.setText("Actualizar");
        menuItemActualizarDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemActualizarDiscoActionPerformed(evt);
            }
        });
        menuDisco.add(menuItemActualizarDisco);

        menuItemEliminarDisco.setText("Eliminar");
        menuItemEliminarDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarDiscoActionPerformed(evt);
            }
        });
        menuDisco.add(menuItemEliminarDisco);

        menuItemListarDisco.setText("Listar");
        menuItemListarDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListarDiscoActionPerformed(evt);
            }
        });
        menuDisco.add(menuItemListarDisco);

        menuCantante.add(menuDisco);

        menuBar.add(menuCantante);

        menuOperadora.setMnemonic('e');
        menuOperadora.setText("Compositor");

        menuItemCrearCompositor.setMnemonic('t');
        menuItemCrearCompositor.setText("Crear");
        menuItemCrearCompositor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCrearCompositorActionPerformed(evt);
            }
        });
        menuOperadora.add(menuItemCrearCompositor);

        menuItemBuscarCompositor.setMnemonic('p');
        menuItemBuscarCompositor.setText("Buscar");
        menuItemBuscarCompositor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBuscarCompositorActionPerformed(evt);
            }
        });
        menuOperadora.add(menuItemBuscarCompositor);

        menuItemActualizarCompositor.setMnemonic('y');
        menuItemActualizarCompositor.setText("Actualizar");
        menuItemActualizarCompositor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemActualizarCompositorActionPerformed(evt);
            }
        });
        menuOperadora.add(menuItemActualizarCompositor);

        menuItemEliminarCompositor.setMnemonic('d');
        menuItemEliminarCompositor.setText("Eliminar");
        menuItemEliminarCompositor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarCompositorActionPerformed(evt);
            }
        });
        menuOperadora.add(menuItemEliminarCompositor);

        menuItemListarCompositor.setText("Listar");
        menuItemListarCompositor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListarCompositorActionPerformed(evt);
            }
        });
        menuOperadora.add(menuItemListarCompositor);

        menuCancion.setText("Cancion");

        menuItemCrearCancion.setText("Crear");
        menuItemCrearCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCrearCancionActionPerformed(evt);
            }
        });
        menuCancion.add(menuItemCrearCancion);

        menuItemBuscarCancion.setText("Buscar");
        menuItemBuscarCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBuscarCancionActionPerformed(evt);
            }
        });
        menuCancion.add(menuItemBuscarCancion);

        menuItemActualizarCancion.setText("Actualizar");
        menuItemActualizarCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemActualizarCancionActionPerformed(evt);
            }
        });
        menuCancion.add(menuItemActualizarCancion);

        menuItemEliminarCancion.setText("Eliminar");
        menuItemEliminarCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarCancionActionPerformed(evt);
            }
        });
        menuCancion.add(menuItemEliminarCancion);

        menuItemListarCancion.setText("Listar");
        menuItemListarCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListarCancionActionPerformed(evt);
            }
        });
        menuCancion.add(menuItemListarCancion);

        menuOperadora.add(menuCancion);

        menuCliente.setText("Cliente");

        menuItemCrearCliente.setText("Crear");
        menuItemCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCrearClienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuItemCrearCliente);

        menuItemBuscarCliente.setText("Buscar");
        menuItemBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBuscarClienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuItemBuscarCliente);

        menuItemActualizarCliente.setText("Actualizar");
        menuItemActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemActualizarClienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuItemActualizarCliente);

        menuItemEliminarCliente.setText("Eliminar");
        menuItemEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarClienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuItemEliminarCliente);

        menuItemListarCliente.setText("Listar");
        menuItemListarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemListarClienteActionPerformed(evt);
            }
        });
        menuCliente.add(menuItemListarCliente);

        menuOperadora.add(menuCliente);

        menuBar.add(menuOperadora);

        menuOpciones.setText("Opciones");

        menuItemSalirOpciones.setText("Salir");
        menuItemSalirOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirOpcionesActionPerformed(evt);
            }
        });
        menuOpciones.add(menuItemSalirOpciones);

        menuBar.add(menuOpciones);

        menuIdiomas.setText("Idiomas");

        menuItemEspanol.setText("Español");
        menuItemEspanol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEspanolActionPerformed(evt);
            }
        });
        menuIdiomas.add(menuItemEspanol);

        menuItemIngles.setText("Ingles");
        menuItemIngles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemInglesActionPerformed(evt);
            }
        });
        menuIdiomas.add(menuItemIngles);

        menuItemFrances.setText("Frances");
        menuItemFrances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemFrancesActionPerformed(evt);
            }
        });
        menuIdiomas.add(menuItemFrances);

        menuBar.add(menuIdiomas);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemSalirOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirOpcionesActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemSalirOpcionesActionPerformed

    private void menuItemCrearCantanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCrearCantanteActionPerformed
       if (ventanaCrearCantante == null){
           ventanaCrearCantante = new VentanaCrearCantante(controladorCantante);
           desktopPane.add(ventanaCrearCantante);
           ventanaCrearCantante.cambiarIdioma(localizacion);
       }
       ventanaCrearCantante.setVisible(true);
    }//GEN-LAST:event_menuItemCrearCantanteActionPerformed

    private void menuItemBuscarCantanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBuscarCantanteActionPerformed
        if (ventanaBuscarCantante ==null){
            ventanaBuscarCantante = new VentanaBuscarCantante(controladorCantante);
            desktopPane.add(ventanaBuscarCantante);
            ventanaBuscarCantante.cambiarIdioma(localizacion);
        }
        ventanaBuscarCantante.setVisible(true);
    }//GEN-LAST:event_menuItemBuscarCantanteActionPerformed

    private void menuItemActualizarCantanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemActualizarCantanteActionPerformed
        if (ventanaActualizarCantante == null){
            ventanaActualizarCantante = new VentanaActualizarCantante(controladorCantante);
            desktopPane.add(ventanaActualizarCantante);
            ventanaActualizarCantante.cambiarIdioma(localizacion); 
        }
        ventanaActualizarCantante.setVisible(true);
    }//GEN-LAST:event_menuItemActualizarCantanteActionPerformed

    private void menuItemEliminarCantanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarCantanteActionPerformed
        if (ventanaEliminarCantante == null){
            ventanaEliminarCantante = new VentanaEliminarCantante(controladorCantante);
            desktopPane.add(ventanaEliminarCantante);
            ventanaEliminarCantante.cambiarIdioma(localizacion);  
        }
        ventanaEliminarCantante.setVisible(true);
    }//GEN-LAST:event_menuItemEliminarCantanteActionPerformed

    private void menuItemListarCantanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListarCantanteActionPerformed
        if (ventanaListarCantante == null){
            ventanaListarCantante = new VentanaListarCantante(controladorCantante);
            desktopPane.add(ventanaListarCantante);
            ventanaListarCantante.cambiarIdioma(localizacion);  
        }
        ventanaListarCantante.setVisible(true);
    }//GEN-LAST:event_menuItemListarCantanteActionPerformed

    private void menuItemCrearCompositorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCrearCompositorActionPerformed
        if (ventanaCrearCompositor == null) {
            ventanaCrearCompositor = new VentanaCrearCompositor(controladorCompositor);
            desktopPane.add(ventanaCrearCompositor);
            ventanaCrearCompositor.cambiarIdioma(localizacion);
        }
        ventanaCrearCompositor.setVisible(true);
    }//GEN-LAST:event_menuItemCrearCompositorActionPerformed

    private void menuItemBuscarCompositorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBuscarCompositorActionPerformed
         if (ventanaBuscarCompositor == null) {
            ventanaBuscarCompositor = new VentanaBuscarCompositor(controladorCompositor);
            desktopPane.add(ventanaBuscarCompositor);
            ventanaBuscarCompositor.cambiarIdioma(localizacion);
        }
        ventanaBuscarCompositor.setVisible(true);
    }//GEN-LAST:event_menuItemBuscarCompositorActionPerformed

    private void menuItemActualizarCompositorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemActualizarCompositorActionPerformed
         if (ventanaActualizarCompositor == null) {
            ventanaActualizarCompositor = new VentanaActualizarCompositor(controladorCompositor);
            desktopPane.add(ventanaActualizarCompositor);
            ventanaActualizarCompositor.cambiarIdioma(localizacion);  
        }
        ventanaActualizarCompositor.setVisible(true);
    }//GEN-LAST:event_menuItemActualizarCompositorActionPerformed

    private void menuItemEliminarCompositorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarCompositorActionPerformed
        if (ventanaEliminarCompositor == null) {
            ventanaEliminarCompositor = new VentanaEliminarCompositor(controladorCompositor);
            desktopPane.add(ventanaEliminarCompositor);
            ventanaEliminarCompositor.cambiarIdioma(localizacion);  
        }
        ventanaEliminarCompositor.setVisible(true);
    }//GEN-LAST:event_menuItemEliminarCompositorActionPerformed

    private void menuItemListarCompositorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListarCompositorActionPerformed
        if (ventanaListarCompositor == null) {
            ventanaListarCompositor = new VentanaListarCompositor(controladorCompositor);
            desktopPane.add(ventanaListarCompositor);
            ventanaListarCompositor.cambiarIdioma(localizacion);
        }
        ventanaListarCompositor.setVisible(true);
    }//GEN-LAST:event_menuItemListarCompositorActionPerformed

    private void menuItemCrearDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCrearDiscoActionPerformed
        if (ventanaAgregarDisco == null){
            ventanaAgregarDisco = new VentanaAgregarDisco(controladorCantante);
            desktopPane.add(ventanaAgregarDisco);
            ventanaAgregarDisco.cambiarIdioma(localizacion);   
        }
        ventanaAgregarDisco.setVisible(true);
    }//GEN-LAST:event_menuItemCrearDiscoActionPerformed

    private void menuItemBuscarDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBuscarDiscoActionPerformed
        if (ventanaBuscarDisco == null) {
            ventanaBuscarDisco = new VentanaBuscarDisco(controladorCantante);
            desktopPane.add(ventanaBuscarDisco);
            ventanaBuscarDisco.cambiarIdioma(localizacion);
        }
        ventanaBuscarDisco.setVisible(true);
    }//GEN-LAST:event_menuItemBuscarDiscoActionPerformed

    private void menuItemEspanolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEspanolActionPerformed
        // TODO add your handling code here:
        localizacion = new Locale("es","EC");
        cambiarIdioma();
    }//GEN-LAST:event_menuItemEspanolActionPerformed

    private void menuItemInglesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemInglesActionPerformed
        // TODO add your handling code here:
        localizacion = new Locale("en","US");
        cambiarIdioma();
    }//GEN-LAST:event_menuItemInglesActionPerformed

    private void menuItemFrancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemFrancesActionPerformed
        // TODO add your handling code here:
        localizacion = new Locale("fr","FR");
        cambiarIdioma();
    }//GEN-LAST:event_menuItemFrancesActionPerformed

    private void menuItemActualizarDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemActualizarDiscoActionPerformed
        // TODO add your handling code here:
        if (ventanaActualizarDisco == null) {
            ventanaActualizarDisco = new VentanaActualizarDisco(controladorCantante);
            desktopPane.add(ventanaActualizarDisco);
            ventanaActualizarDisco.cambiarIdioma(localizacion); 
        }
        ventanaActualizarDisco.setVisible(true);
    }//GEN-LAST:event_menuItemActualizarDiscoActionPerformed

    private void menuItemEliminarDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarDiscoActionPerformed
        // TODO add your handling code here:
        if (ventanaEliminarDisco == null) {
            ventanaEliminarDisco = new VentanaEliminarDisco(controladorCantante);
            desktopPane.add(ventanaEliminarDisco);
            ventanaEliminarDisco.cambiarIdioma(localizacion);   
        }
        ventanaEliminarDisco.setVisible(true);
    }//GEN-LAST:event_menuItemEliminarDiscoActionPerformed

    private void menuItemListarDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListarDiscoActionPerformed
        // TODO add your handling code here:
        if (ventanaListarDisco == null) {
            ventanaListarDisco = new VentanaListarDisco(controladorCantante);
            desktopPane.add(ventanaListarDisco);
            ventanaListarDisco.cambiarIdioma(localizacion);
        }
        ventanaListarDisco.setVisible(true);
    }//GEN-LAST:event_menuItemListarDiscoActionPerformed

    private void menuItemCrearCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCrearCancionActionPerformed
        // TODO add your handling code here:
        if (ventanaAgregarCancion == null) {
            ventanaAgregarCancion = new VentanaAgregarCancion(controladorCompositor);
            desktopPane.add(ventanaAgregarCancion);
            ventanaAgregarCancion.cambiarIdioma(localizacion);
        }
        ventanaAgregarCancion.setVisible(true);
    }//GEN-LAST:event_menuItemCrearCancionActionPerformed

    private void menuItemBuscarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBuscarCancionActionPerformed
        // TODO add your handling code here:
        if (ventanaBuscarCancion == null) {
            ventanaBuscarCancion = new VentanaBuscarCancion(controladorCompositor);
            desktopPane.add(ventanaBuscarCancion);
            ventanaBuscarCancion.cambiarIdioma(localizacion);
        }
        ventanaBuscarCancion.setVisible(true);
    }//GEN-LAST:event_menuItemBuscarCancionActionPerformed

    private void menuItemActualizarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemActualizarCancionActionPerformed
        // TODO add your handling code here:
        if (ventanaActualizarCancion == null) {
            ventanaActualizarCancion = new VentanaActualizarCancion(controladorCompositor);
            desktopPane.add(ventanaActualizarCancion);
            ventanaActualizarCancion.cambiarIdioma(localizacion);
        }
        ventanaActualizarCancion.setVisible(true);
    }//GEN-LAST:event_menuItemActualizarCancionActionPerformed

    private void menuItemEliminarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarCancionActionPerformed
        // TODO add your handling code here:
        if (ventanaEliminarCancion == null) {
            ventanaEliminarCancion = new VentanaEliminarCancion(controladorCompositor);
            desktopPane.add(ventanaEliminarCancion);
            ventanaEliminarCancion.cambiarIdioma(localizacion);
        }
        ventanaEliminarCancion.setVisible(true);
    }//GEN-LAST:event_menuItemEliminarCancionActionPerformed

    private void menuItemListarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListarCancionActionPerformed
        // TODO add your handling code here:
        if (ventanaListarCancion == null) {
            ventanaListarCancion = new VentanaListarCancion(controladorCompositor);
            desktopPane.add(ventanaListarCancion);
            ventanaListarCancion.cambiarIdioma(localizacion);
        }
        ventanaListarCancion.setVisible(true);
    }//GEN-LAST:event_menuItemListarCancionActionPerformed

    private void menuItemCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCrearClienteActionPerformed
        // TODO add your handling code here:
        if (ventanaAgregarCliente == null) {
            ventanaAgregarCliente = new VentanaAgregarCliente(controladorCompositor, controladorCantante);
            desktopPane.add(ventanaAgregarCliente);
            ventanaAgregarCliente.cambiarIdioma(localizacion);
        }
        ventanaAgregarCliente.setVisible(true);
    }//GEN-LAST:event_menuItemCrearClienteActionPerformed

    private void menuItemBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBuscarClienteActionPerformed
        // TODO add your handling code here:
          if (ventanaBuscarCliente == null) {
            ventanaBuscarCliente = new VentanaBuscarCliente(controladorCompositor, controladorCantante);
            desktopPane.add(ventanaBuscarCliente);
            ventanaBuscarCliente.cambiarIdioma(localizacion);
        }
        ventanaBuscarCliente.setVisible(true);
    }//GEN-LAST:event_menuItemBuscarClienteActionPerformed

    private void menuItemActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemActualizarClienteActionPerformed
        // TODO add your handling code here:
          if (ventanaActualizarCliente == null) {
            ventanaActualizarCliente = new VentanaActualizarCliente(controladorCompositor, controladorCantante);
            desktopPane.add(ventanaActualizarCliente);
            ventanaActualizarCliente.cambiarIdioma(localizacion);
        }
        ventanaActualizarCliente.setVisible(true);
    }//GEN-LAST:event_menuItemActualizarClienteActionPerformed

    private void menuItemEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarClienteActionPerformed
        // TODO add your handling code here:
          if (ventanaEliminarCliente == null) {
            ventanaEliminarCliente = new VentanaEliminarCliente(controladorCompositor, controladorCantante);
            desktopPane.add(ventanaEliminarCliente);
            ventanaEliminarCliente.cambiarIdioma(localizacion);
        }
        ventanaEliminarCliente.setVisible(true);
    }//GEN-LAST:event_menuItemEliminarClienteActionPerformed

    private void menuItemListarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemListarClienteActionPerformed
        // TODO add your handling code here:
          if (ventanaListarClientes == null) {
            ventanaListarClientes = new VentanaListarClientes(controladorCompositor, controladorCantante);
            desktopPane.add(ventanaListarClientes);
            ventanaListarClientes.cambiarIdioma(localizacion);
        }
        ventanaListarClientes.setVisible(true);
    }//GEN-LAST:event_menuItemListarClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel jlblCantante;
    private javax.swing.JLabel jlblLogo;
    private javax.swing.JLabel lblCompositor;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCancion;
    private javax.swing.JMenu menuCantante;
    private javax.swing.JMenu menuCliente;
    private javax.swing.JMenu menuDisco;
    private javax.swing.JMenu menuIdiomas;
    private javax.swing.JMenuItem menuItemActualizarCancion;
    private javax.swing.JMenuItem menuItemActualizarCantante;
    private javax.swing.JMenuItem menuItemActualizarCliente;
    private javax.swing.JMenuItem menuItemActualizarCompositor;
    private javax.swing.JMenuItem menuItemActualizarDisco;
    private javax.swing.JMenuItem menuItemBuscarCancion;
    private javax.swing.JMenuItem menuItemBuscarCantante;
    private javax.swing.JMenuItem menuItemBuscarCliente;
    private javax.swing.JMenuItem menuItemBuscarCompositor;
    private javax.swing.JMenuItem menuItemBuscarDisco;
    private javax.swing.JMenuItem menuItemCrearCancion;
    private javax.swing.JMenuItem menuItemCrearCantante;
    private javax.swing.JMenuItem menuItemCrearCliente;
    private javax.swing.JMenuItem menuItemCrearCompositor;
    private javax.swing.JMenuItem menuItemCrearDisco;
    private javax.swing.JMenuItem menuItemEliminarCancion;
    private javax.swing.JMenuItem menuItemEliminarCantante;
    private javax.swing.JMenuItem menuItemEliminarCliente;
    private javax.swing.JMenuItem menuItemEliminarCompositor;
    private javax.swing.JMenuItem menuItemEliminarDisco;
    private javax.swing.JMenuItem menuItemEspanol;
    private javax.swing.JMenuItem menuItemFrances;
    private javax.swing.JMenuItem menuItemIngles;
    private javax.swing.JMenuItem menuItemListarCancion;
    private javax.swing.JMenuItem menuItemListarCantante;
    private javax.swing.JMenuItem menuItemListarCliente;
    private javax.swing.JMenuItem menuItemListarCompositor;
    private javax.swing.JMenuItem menuItemListarDisco;
    private javax.swing.JMenuItem menuItemSalirOpciones;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenu menuOperadora;
    // End of variables declaration//GEN-END:variables

}
