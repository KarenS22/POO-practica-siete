/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import ec.edu.ups.practica.siete.quitokaren.penasofia.dao.CantanteDAO;
import ec.edu.ups.practica.siete.quitokaren.penasofia.modelo.Cantante;

/**
 *
 * @author ACER
 */
public class Prueba {
        public static void main(String[] args) {
        //432 123
            CantanteDAO cantanteDAO = new CantanteDAO();
//       cantanteDAO.create(new Cantante("Karen1", "pop", 5, 3, 1, 1, "Karen", "Quito", 18, "Ecuatoriana", 500));
//        cantanteDAO.create(new Cantante("Karen2", "pop", 5, 3, 1, 2, "Karenn", "Quitoo", 18, "Ecuatoriana", 500));
////        cantanteDAO.create(new Cantante("Karen3", "pop", 5, 3, 1, 3, "Karennn", "Quitooo", 18, "Ecuatoriana", 500));
//        
        cantanteDAO.createDisco(1, 12, "Disco02", 2001);
        //cantanteDAO.createDisco(2, 13, "Disc03", 2003);
        
        
    }
}
