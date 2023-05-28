/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }
    
    public boolean registrarSeleccion(Seleccion seleccion) {
        
        String sql = "INSERT INTO b_cortine.seleccion (nombre, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', " 
                + "'" + seleccion.getContinente() + "', " 
                + "'" + seleccion.getDt() + "', " 
                + "'" + seleccion.getNacionalidad() + "')";
        
        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }
    
    public List<Seleccion> getSelecciones() {
        
        String sql = "SELECT nombre, continente, dt, nacionalidad FROM b_cortine.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }
        
        return selecciones;
    }
    
    public List<Seleccion> getSeleccionesBuscado(String buscado) {
        
        String sql = "SELECT * FROM b_cortine.seleccion WHERE nombre LIKE '%"+buscado+"%' OR continente LIKE '%"+buscado+"%' OR dt LIKE '%"+buscado+"%' OR nacionalidad LIKE '%"+buscado+"%';";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }
        
        return selecciones;
    }
    
    
    public String[][] getSeleccionesMatriz() {
        
        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();
        
        
        if(selecciones != null && selecciones.size() > 0) {
            
        
            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }
        
        return matrizSelecciones;
    }
    
    public String[][] getSeleccionesMatrizBuscado(String buscado) {
        
        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSeleccionesBuscado(buscado);
        
        
        if(selecciones != null && selecciones.size() > 0) {
            
        
            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }
        
        return matrizSelecciones;
    }
    
    public int getCantidadSelecciones() {
        String sql = "SELECT COUNT(DISTINCT nombre) FROM b_cortine.seleccion";
        int resultado = -1;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado = result.getInt("COUNT");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando Selecciones");
        }
        
        return resultado;
    }
    
    public int getCantidadNacionalidades() {
        String sql = "SELECT COUNT(DISTINCT nacionalidad) FROM b_cortine.seleccion";
        int resultado = -1;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado = result.getInt("COUNT");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando Nacionalidades");
        }
        
        return resultado;
    }
    
    public List<String[][]> getMayorCantidadNacionalidad() {
        
        String sql = "SELECT nacionalidad, COUNT(nacionalidad) AS cantidad FROM b_cortine.seleccion GROUP BY nacionalidad ORDER BY cantidad DESC LIMIT 3";
        List<String[][]> selecciones = new ArrayList<String[][]>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    String[][] seleccion = new String[1][2];
                    seleccion[0][0] = result.getString("nacionalidad");
                    seleccion[0][1] = result.getString("cantidad");
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Mayor Cantidad Nacionalidad");
        }
        
        return selecciones;
    }
    
    public String[][] mayorCantidadNacionalidad() {
        
        String[][] matrizSelecciones = null;
        List<String[][]> selecciones = getMayorCantidadNacionalidad();
        
        
        if(selecciones != null && selecciones.size() > 0) {
            
        
            matrizSelecciones = new String[selecciones.size()][2];

            int x = 0;
            for (String[][] seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion[0][0];
                matrizSelecciones[x][1] = seleccion[0][1];

                x++;
            }
        }
        
        return matrizSelecciones;
    }
    
    public List<String[][]> getCantidadSeleccionesContinente() {
        
        String sql = "SELECT continente, COUNT(nombre) AS cantidad FROM b_cortine.seleccion GROUP BY continente";
        List<String[][]> selecciones = new ArrayList<String[][]>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    String[][] seleccion = new String[1][2];
                    seleccion[0][0] = result.getString("continente");
                    seleccion[0][1] = result.getString("cantidad");
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando Mayor Cantidad Nacionalidad");
        }
        
        return selecciones;
    }
    
    public String[][] cantidadSeleccionesContinente() {
        
        String[][] matrizSelecciones = null;
        List<String[][]> selecciones = getCantidadSeleccionesContinente();
        
        
        if(selecciones != null && selecciones.size() > 0) {
            
        
            matrizSelecciones = new String[selecciones.size()][2];

            int x = 0;
            for (String[][] seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion[0][0];
                matrizSelecciones[x][1] = seleccion[0][1];

                x++;
            }
        }
        
        return matrizSelecciones;
    }
}
