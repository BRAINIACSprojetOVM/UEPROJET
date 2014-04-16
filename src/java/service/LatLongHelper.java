/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import org.primefaces.model.map.LatLng;

/**
 *
 * @author mehdi
 */
public class LatLongHelper {
    public static LatLng latlngBySpeedAngleSeconde(double speed,double angle,double heurs,LatLng latLangInitial)
    {
        //vitesse en m/h
        //R la distance entre les 2 point qu'on calcule avec la vitesse
        //v=R/T ( R=T*V avec V : speed et T : heurs)
        //R=speed*heurs
        
        double R=speed*heurs;
        LatLng latlngFinal;
        
        double dx,dy,deltaLat,deltaLong;
        
        dx=R*Math.cos(angle);
        dy=R*Math.sin(angle);
        
        deltaLong = dx/(111320*Math.cos(latLangInitial.getLat())) ;
        deltaLat = dy/110540;
        
        latlngFinal=new LatLng(deltaLat+latLangInitial.getLat(), deltaLong+latLangInitial.getLng());
        
    return latlngFinal;
    }
    
}
