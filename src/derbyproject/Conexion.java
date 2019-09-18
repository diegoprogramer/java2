/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derbyproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alexander
 */
public class Conexion {
    private Connection conn = null;
    public Connection CrearBD() throws SQLException{
    try{
     

    // se obtiene el driver para el mysql
    
   Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      
            // obtenenmos la conexion
            // derby en memoria
           // String sURL = "jdbc:derby:memory:myDB.bd;create=true";
           // PARA QUE NOS CREE UNA CARPETA
                 
           // conn = DriverManager.getConnection(sURL);
            conn = DriverManager.getConnection("jdbc:derby:.\\MBD\\MYBD.DB;create=true");
       
       
    if(conn != null){
    JOptionPane.showMessageDialog(null,"Ok base de datos listo");
    String creartabla="create table Clientes(nombre varchar(50), ape1 varchar(50), ape2 varchar(50), direccion varchar(50))";
    String desc = "disconnect";
    try{
    PreparedStatement pstm = conn.prepareStatement(creartabla);
    pstm.execute();
    pstm.close();
//    PreparedStatement pstm2 = conn.prepareStatement(desc);
//    pstm2.execute();
//    pstm2.close();
    JOptionPane.showMessageDialog(null,"Base de datos creada correctamente");
    
    }
  
    catch(SQLException ex){
     JOptionPane.showMessageDialog(null,"ERROR EN LA CREACION DE LA BD"+ex);
    }
    }// fin del si
    
    }// fin del try
     catch (ClassNotFoundException ex) {
          JOptionPane.showMessageDialog(null,"error 2"+ex);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    return conn;
   } 
    
    public Connection conectarBD() throws SQLException{
          try{
    // se obtiene el driver para el mysql
    
   Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      
            // obtenenmos la conexion
            // derby en memoria
            String sURL = "jdbc:derby:.\\MBD\\MYBD.DB";
       
            conn = DriverManager.getConnection(sURL);
       
        
        
    }
           catch (ClassNotFoundException ex) {
          JOptionPane.showMessageDialog(null,"error 2"+ex);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } return conn;
   }
    // AQUI PARA CARGAR LA BASE DE DATOS
    
    public void insertar() throws SQLException{
    Statement carga = conn.createStatement();
    carga.addBatch("INSERT INTO Clientes VALUES('DIEGO','PRADO','ACOSTA','CRA 23E')");
    carga.addBatch("INSERT INTO Clientes VALUES('Dario','PRADO','Arias','CRA 24E')");
    carga.addBatch("INSERT INTO Clientes VALUES('Duran','PRADO','ACOSTA','CRA 25E')");
    carga.addBatch("INSERT INTO Clientes VALUES('DIEGO','PRADO','ACOSTA','CRA 26E')");
    carga.executeBatch();
    }
    // AQUI PARA CARGAR LA BASE DE DATOS CON PARAMETROS
    
    public void insertar(String name, String ape1, String ape2, String dir) throws SQLException{
    Statement carga1 = conn.createStatement();
    carga1.addBatch("INSERT INTO Clientes  VALUES('"+name+"','"+ape1+"','"+ape2+"','"+dir+"')");
    carga1.executeBatch();
    }
    // AQUI PARA CONSULTAR LA BD
    public void consultarClientes() throws SQLException{
       String datos="";
   try{
    PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT * FROM Clientes ORDER BY nombre");
    ResultSet rs = stmt.executeQuery();
    while (rs.next()){ System.out.println("nombre"+rs.getString("nombre"));
        System.out.println("apellido 1: "+rs.getString("ape1"));
        System.out.println("apellido 2: "+rs.getString("ape2"));
        System.out.println("Direccion: "+rs.getString("Direccion"));
        datos = datos+rs.getString("nombre")+","+rs.getString("ape1")+","+rs.getString("ape2")+","+rs.getString("direccion")+"\n";
        }
    //llamado a la ventana Vlistarclientes
    Vlistarclientes v1 = new Vlistarclientes(datos);
    v1.setVisible(true);
   }
   catch (SQLException ex){
       System.out.println("Error en la ejecucion"+ex.getErrorCode()+" "+ex.getMessage());
   }
    }
    
 }
    
    
