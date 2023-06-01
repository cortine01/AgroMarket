/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyectomundial.model.Resultados;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;
/**
 *
 * @author LAB205BPC02
 */
public class ResultadoDAO {
    
    public ResultadoDAO() {
        BasedeDatos.conectar();
    }
    
    public boolean registrarResultado(Resultados resultados) {
        
        String sql = "INSERT INTO b_cortine.resultado (grupo, \"local\", visitante, continente_local, continente_visitante, goles_local, goles_visitante) values("
                + "'" + resultados.getGrupo() + "', " 
                + "'" + resultados.getLocal() + "', " 
                + "'" + resultados.getVisitante() + "', " 
                + "'" + resultados.getContinente_local() + "', " 
                + "'" + resultados.getContinente_visitante() + "', " 
                + "'" + resultados.getGoles_local() + "', " 
                + "'" + resultados.getGoles_visitante() + "')";
        
        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }
    
    public List<Resultados> getResultados() {
        
        String sql = "SELECT grupo, \"local\", visitante, continente_local, continente_visitante, goles_local, goles_visitante FROM b_cortine.resultado";
        List<Resultados> resultados = new ArrayList<Resultados>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados");
        }
        
        return resultados;
    }
    
    public List<Resultados> getResultadosBuscado(String buscado) {
        
        //int numBuscado = Integer.(buscado);
        
        //String sql = "SELECT * FROM b_cortine.resultado WHERE grupo LIKE '%"+buscado+"%' OR \"local\" LIKE '%"+buscado+"%' OR visitante LIKE '%"+buscado+"%' OR continente_local LIKE '%"+buscado+"%' OR continente_visitante LIKE '%"+buscado+"%' OR goles_local LIKE '%"+numBuscado+"%' OR goles_visitante LIKE '%"+numBuscado+"%'";
        String sql = "SELECT * FROM b_cortine.resultado WHERE grupo LIKE '%"+buscado+"%' OR \"local\" LIKE '%"+buscado+"%' OR visitante LIKE '%"+buscado+"%' OR continente_local LIKE '%"+buscado+"%' OR continente_visitante LIKE '%"+buscado+"%'";
        List<Resultados> resultados = new ArrayList<Resultados>();
        
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result != null) {
            
                while (result.next()) { 
                    Resultados resultado = new Resultados(result.getString("grupo"), result.getString("local"), result.getString("visitante"), result.getString("continente_local"), result.getString("continente_visitante"), result.getString("goles_local"), result.getString("goles_visitante"));
                    resultados.add(resultado);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando resultados buscados");
        }
        
        return resultados;
    }
    
    public String[][] getResultadosMatriz() {
        
        String[][] matrizResultados = null;
        List<Resultados> resultados = getResultados();
        
        if(resultados != null && resultados.size() > 0) {
            
        
            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_local();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }
        
        return matrizResultados;
    }
    
    public String[][] getResultadosMatrizBuscado(String buscado) {
        
        String[][] matrizResultados = null;
        List<Resultados> resultados = getResultadosBuscado(buscado);
        
        if(resultados != null && resultados.size() > 0) {
            
        
            matrizResultados = new String[resultados.size()][7];

            int x = 0;
            for (Resultados resultado : resultados) {

                matrizResultados[x][0] = resultado.getGrupo();
                matrizResultados[x][1] = resultado.getLocal();
                matrizResultados[x][2] = resultado.getVisitante();
                matrizResultados[x][3] = resultado.getContinente_local();
                matrizResultados[x][4] = resultado.getContinente_visitante();
                matrizResultados[x][5] = resultado.getGoles_local();
                matrizResultados[x][6] = resultado.getGoles_visitante();
                x++;
            }
        }
        
        return matrizResultados;
    }
    
    public int getCantidadResultados() {
        String sql = "select count(grupo) as cantidad from b_cortine.resultado";
        int resultado = -1;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado = result.getInt("cantidad");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando Resultado");
        }
        
        return resultado;
    }
    
    public double getPromedioGoles() {
        String sql = "SELECT avg(goles_local + goles_local) AS promedioGoles FROM b_cortine.resultado";
        double resultado = -1;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado = result.getDouble("promedioGoles");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando PromedioGoles");
        }
        
        return resultado;
    }
    
    public String[] getpartidoMayorGoles() {
        String sql = "select grupo, \"local\", visitante, sum(goles_local + goles_visitante) as cantidad from b_cortine.resultado group by grupo, \"local\", visitante order by cantidad desc limit 1 ";
        String[] resultado = new String[4];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("grupo");
                resultado[1] = result.getString("local");
                resultado[2] = result.getString("visitante");
                resultado[3] = result.getString("cantidad");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando PromedioGoles");
        }
        
        return resultado;
    }
    
    public String[] getpartidoMenorGoles() {
        String sql = "select grupo, \"local\", visitante, sum(goles_local + goles_visitante) as cantidad from b_cortine.resultado group by grupo, \"local\", visitante order by cantidad asc limit 1 ";
        String[] resultado = new String[4];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("grupo");
                resultado[1] = result.getString("local");
                resultado[2] = result.getString("visitante");
                resultado[3] = result.getString("cantidad");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando PromedioGoles");
        }
        
        return resultado;
    }
    
    public int[] getCantidadEmpateGanador() {
        String sql = "SELECT \n" +
                "  COUNT(CASE WHEN goles_local = goles_visitante THEN 1 END) AS empate,\n" +
                "  COUNT(CASE WHEN goles_local <> goles_visitante THEN 1 END) AS ganador\n" +
                "FROM b_cortine.resultado";
        int resultado[] = new int[2];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getInt("empate");
                resultado[1] = result.getInt("ganador");
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando Empate Ganador");
        }
        
        return resultado;
    }
    
    public String getSeleccionMayorGoles(){
        String sql = "select locales.equipo, locales.goles + visitante.goles as goles from (\n" +
                    "	select \n" +
                    "		\"local\" as equipo, sum(goles_local) as goles\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo ) as locales, \n" +
                    "	(\n" +
                    "	select\n" +
                    "		visitante as equipo, sum(goles_visitante) as goles \n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo) as visitante \n" +
                    "where locales.equipo = visitante.equipo order by goles desc limit 1 ";
        String[] resultado = new String[2];
        String retorno = "";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("equipo");
                resultado[1] = result.getString("goles");
                retorno = "<html>"+resultado[0] +"<br/>"+ resultado[1] + "</html>";
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando PromedioGoles");
        }
        
        return retorno;
    }
    
    public String getSeleccionMenorGoles(){
        String sql = "select locales.equipo, locales.goles + visitante.goles as goles from (\n" +
                    "	select \n" +
                    "		\"local\" as equipo, sum(goles_local) as goles\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo ) as locales, \n" +
                    "	(\n" +
                    "	select\n" +
                    "		visitante as equipo, sum(goles_visitante) as goles \n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo) as visitante \n" +
                    "where locales.equipo = visitante.equipo order by goles asc limit 1 ";
        String[] resultado = new String[2];
        String retorno = "";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("equipo");
                resultado[1] = result.getString("goles");
                retorno = "<html>"+resultado[0] +"<br/>"+ resultado[1] + "</html>";
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando PromedioGoles");
        }
        
        return retorno;
    }
    
    public String getContinenteMayorGoles(){
        String sql = "select locales.continente, locales.goles + visitante.goles as goles from (\n" +
                    "	select \n" +
                    "		continente_local as continente, sum(goles_local) as goles\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	continente ) as locales, \n" +
                    "	(\n" +
                    "	select\n" +
                    "		continente_visitante as continente, sum(goles_visitante) as goles \n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	continente) as visitante \n" +
                    "where locales.continente = visitante.continente order by goles desc limit 1;";
        String[] resultado = new String[2];
        String retorno = "";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("continente");
                resultado[1] = result.getString("goles");
                retorno = "<html>"+resultado[0] +"<br/>"+ resultado[1] + "</html>";
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando PromedioGoles");
        }
        
        return retorno;
    }
    
    public String getContinenteMenorGoles(){
        String sql = "select locales.continente, locales.goles + visitante.goles as goles from (\n" +
                    "	select \n" +
                    "		continente_local as continente, sum(goles_local) as goles\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	continente ) as locales, \n" +
                    "	(\n" +
                    "	select\n" +
                    "		continente_visitante as continente, sum(goles_visitante) as goles \n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	continente) as visitante \n" +
                    "where locales.continente = visitante.continente order by goles asc limit 1;";
        String[] resultado = new String[2];
        String retorno = "";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("continente");
                resultado[1] = result.getString("goles");
                retorno = "<html>"+resultado[0] +"<br/>"+ resultado[1] + "</html>";
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando PromedioGoles");
        }
        
        return retorno;
    }
    
    public String getSeleccionMayorPuntos(){
        String sql = "select locales.equipo, locales.puntos + visitante.puntos as puntos from (\n" +
                    "	select \n" +
                    "		\"local\" as equipo, SUM(CASE WHEN goles_local > goles_visitante THEN 3 \n" +
                    "WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) as puntos\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo ) as locales, \n" +
                    "	(\n" +
                    "	select\n" +
                    "		visitante as equipo, SUM(CASE WHEN goles_visitante > goles_local THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) as puntos\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo) as visitante \n" +
                    "where locales.equipo = visitante.equipo order by puntos desc limit 1";
        String[] resultado = new String[2];
        String retorno = "";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("equipo");
                resultado[1] = result.getString("puntos");
                retorno = "<html>"+resultado[0] +"<br/>"+ resultado[1] + "</html>";
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error mayor puntos");
        }
        
        return retorno;
    }
    
    public String getSeleccionMenorPuntos(){
        String sql = "select locales.equipo, locales.puntos + visitante.puntos as puntos from (\n" +
                    "	select \n" +
                    "		\"local\" as equipo, SUM(CASE WHEN goles_local > goles_visitante THEN 3 \n" +
                    "WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) as puntos\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo ) as locales, \n" +
                    "	(\n" +
                    "	select\n" +
                    "		visitante as equipo, SUM(CASE WHEN goles_visitante > goles_local THEN 3 WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) as puntos\n" +
                    "	from b_cortine.resultado\n" +
                    "	group by\n" +
                    "	equipo) as visitante \n" +
                    "where locales.equipo = visitante.equipo order by puntos asc limit 1";
        String[] resultado = new String[2];
        String retorno = "";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            
            if(result.next()) {
                resultado[0] = result.getString("equipo");
                resultado[1] = result.getString("puntos");
                retorno = "<html>"+resultado[0] +"<br/>"+ resultado[1] + "</html>";
            }
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando menor puntos");
        }
        
        return retorno;
    }
    
    
}
