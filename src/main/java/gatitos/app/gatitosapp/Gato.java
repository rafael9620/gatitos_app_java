/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatitos.app.gatitosapp;

/**
 *
 * @author FranciscoR
 */
public class Gato {
    private int idGato;
    private String url;
    private final String KEY ="979887f9-7d8c-40a5-98a7-7a85d19eb2ed";
    private String image;

    public int getIdGato() {
        return idGato;
    }

    public void setIdGato(int idGato) {
        this.idGato = idGato;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
