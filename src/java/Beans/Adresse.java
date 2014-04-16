/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author mehdi
 */
public class Adresse implements Serializable{
    private String adresseFormate;
    private LatLng latlng;

    public String getAdresseFormate() {
        return adresseFormate;
    }

    public void setAdresseFormate(String adresseFormate) {
        this.adresseFormate = adresseFormate;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public Adresse() {
    }

    @Override
    public String toString() {
        return  adresseFormate ;
    }

    
    
    
}
