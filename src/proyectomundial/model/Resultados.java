/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.model;

/**
 *
 * @author LAB205BPC02
 */
public class Resultados {
    
    String Grupo;
    String Local;
    String Visitante;
    String continente_local;
    String continente_visitante;
    String Goles_local;
    String Goles_visitante;

    public Resultados() {
    }

    public Resultados(String Grupo, String Local, String Visitante, String continente_local, String continente_visitante, String Goles_local, String Goles_visitante) {
        this.Grupo = Grupo;
        this.Local = Local;
        this.Visitante = Visitante;
        this.continente_local = continente_local;
        this.continente_visitante = continente_visitante;
        this.Goles_local = Goles_local;
        this.Goles_visitante = Goles_visitante;
    }
    
    

    public String getGrupo() {
        return Grupo;
    }

    public String getLocal() {
        return Local;
    }

    public String getVisitante() {
        return Visitante;
    }

    public String getContinente_local() {
        return continente_local;
    }

    public String getContinente_visitante() {
        return continente_visitante;
    }

    public String getGoles_local() {
        return Goles_local;
    }

    public String getGoles_visitante() {
        return Goles_visitante;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public void setLocal(String Local) {
        this.Local = Local;
    }

    public void setVisitante(String Visitante) {
        this.Visitante = Visitante;
    }

    public void setContinente_local(String continente_local) {
        this.continente_local = continente_local;
    }

    public void setContinente_visitante(String continente_visitante) {
        this.continente_visitante = continente_visitante;
    }

    public void setGoles_local(String Goles_local) {
        this.Goles_local = Goles_local;
    }

    public void setGoles_visitante(String Goles_visitante) {
        this.Goles_visitante = Goles_visitante;
    }
    
    
    
    
}
