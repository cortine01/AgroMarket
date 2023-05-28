/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;
/**
 *
 * @author LAB205BPC02
 */
public class AuditoriaDAO {

    public AuditoriaDAO() {
        BasedeDatos.conectar();
    }
    
    public boolean Incrementador(String lugar) {
         String sql = "update b_cortine.auditoria set contador = contador + 1 where pagina = '"+lugar+"';";
         boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
         
         return registro;
    }
    
    public List<String[][]> getMostrar() {
        
        String sql = "select * from b_cortine.auditoria";
        List<String[][]> selecciones = new ArrayList<String[][]>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    String[][] seleccion = new String[1][2];
                    seleccion[0][0] = result.getString("pagina");
                    seleccion[0][1] = result.getString("contador");
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Mostrar Auditoria");
        }
        
        return selecciones;
    }
    
    public String[][] Mostrar() {
        
        String[][] matrizSelecciones = null;
        List<String[][]> selecciones = getMostrar();
        
        
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
