/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.util;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author bryam
 */
public class ConexionOracle {
    private Connection conn = null;
    private String url, user, pass;
    private Statement statement;
    private ResultSet Result;

    public ConexionOracle() {
        conectar();
    }
    
    private void conectar() {
        try {
            Class.forName("oracle.jdbc.OracleDriver"); // Driver BD
            url = "jdbc:oracle:thin:@localhost:1521:XE";
            user = "AgroMarket";
            pass = "unisimon2023";
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Error, No se pudo conectar");
        }
    }
    
    public void desconectar() {
        try {
            conn.close();
            System.out.println("Desconectado");
        } catch (Exception e) {
            System.out.println("Error, No se pudo Desconectar");
        }
    }
    
    public JTable consulta(String sentencia) {
        DefaultTableModel modelo = new DefaultTableModel();
        JTable table = new JTable();
        table.setModel(modelo);
        
        try {
            String sql = sentencia;
            conectar();
            
            statement = conn.createStatement();
            Result = statement.executeQuery(sql);
            
            ResultSetMetaData resultado = Result.getMetaData();
            
            int cantidadColumnas = resultado.getColumnCount();
            
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(resultado.getColumnLabel(i));
            }
            
            while(Result.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = Result.getObject(i+1);
                }
                modelo.addRow(fila);
            }
            Result.close();
            desconectar();
            
        } catch (Exception e) {
            System.out.println("No funciono la consulta" + e);
        }
        
        return table;
        
    }
    
    public String[][] Sesion() {
         String[][] usuarios = null;
        try {
            String sql = "SELECT \"usuario\", \"contrasena\" FROM usuarios_uss";
            conectar();
            
            statement = conn.createStatement();
            Result = statement.executeQuery(sql);
            
            ResultSetMetaData resultado = Result.getMetaData();
            int cantidadColumnas = resultado.getColumnCount();
    
            
            // Crear una lista para almacenar las filas
        List<String[]> filas = new ArrayList<>();

        while (Result.next()) {
            String[] fila = new String[cantidadColumnas];
            for (int i = 0; i < cantidadColumnas; i++) {
                fila[i] = Result.getString(i + 1);
            }
            filas.add(fila);
        }

        // Convertir la lista a una matriz
        String[][] matriz = new String[filas.size()][cantidadColumnas];
        for (int i = 0; i < filas.size(); i++) {
            matriz[i] = (String[]) filas.get(i);
        }

        usuarios = matriz;
        
        Result.close();
        desconectar();

        // Ahora, la matriz contiene la información de la base de datos
 
            
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        
        return usuarios;
    }
    
    
}
