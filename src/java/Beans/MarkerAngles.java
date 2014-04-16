/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;
import service.LatLongHelper;

/**
 *
 * @author mehdi
 */
public class MarkerAngles implements Serializable{
      private static final long serialVersionUID = 1L;
    private  Marker marker ;
    private  List<DirectionInnondation> listeDirection;
    private String avancementTemporelle;
    private String image;
    

    // private HashMap<Double,DirectionInnondation> map;
    
    public MarkerAngles() {
       listeDirection=new ArrayList<>();
       avancementTemporelle="0 h 0 min 0 rs ";
    }

    public String getAvancementTemporelle() {
        return avancementTemporelle;
    }

    public void setAvancementTemporelle(String avancementTemporelle) {
        this.avancementTemporelle = avancementTemporelle;
    }
     

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public List<DirectionInnondation> getListeDirection() {
        return listeDirection;
    }

    public void setListeDirection(List<DirectionInnondation> listeDirection) {
        this.listeDirection = listeDirection;
    }
    
    public void addPolylineToModel(DirectionInnondation direction,MapModel emptyModel)
    {
        Polyline polyline = new Polyline();  
        polyline.getPaths().add(marker.getLatlng());   
        polyline.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(direction.getSpeed(),direction.getAngle(), direction.getHeurspropagation(), marker.getLatlng()));  
        polyline.setStrokeWeight(10);  
        polyline.setStrokeColor(direction.getCouleur());  
        polyline.setStrokeOpacity(0.7);  
          
        emptyModel.addOverlay(polyline); 
       
    }
    
    public void addListePolyToMdoel(MapModel emptyModel)
    {
    for(DirectionInnondation direction:listeDirection)
    {
    addPolylineToModel(direction, emptyModel);
    }
    
     Polygon polygon = new Polygon(); 
        polygon.getPaths().add(marker.getLatlng());  
        polygon.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(0).getSpeed(),listeDirection.get(0).getAngle(), listeDirection.get(0).getHeurspropagation(), marker.getLatlng()));  
        polygon.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(1).getSpeed(),listeDirection.get(1).getAngle(), listeDirection.get(1).getHeurspropagation(), marker.getLatlng()));  
  
        polygon.setStrokeColor(listeDirection.get(0).getCouleur());  
        polygon.setFillColor(listeDirection.get(0).getCouleur());  
        polygon.setStrokeOpacity(0.2);  
        polygon.setFillOpacity(0.2);  
          
        emptyModel.addOverlay(polygon); 
        
        Polygon polygon1 = new Polygon(); 
        polygon1.getPaths().add(marker.getLatlng());  
        polygon1.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(3).getSpeed(),listeDirection.get(3).getAngle(), listeDirection.get(3).getHeurspropagation(), marker.getLatlng()));  
        polygon1.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(0).getSpeed(),listeDirection.get(0).getAngle(), listeDirection.get(0).getHeurspropagation(), marker.getLatlng()));  
  
        polygon1.setStrokeColor(listeDirection.get(3).getCouleur());  
        polygon1.setFillColor(listeDirection.get(3).getCouleur());  
        polygon1.setStrokeOpacity(0.2);  
        polygon1.setFillOpacity(0.2);  
          
        emptyModel.addOverlay(polygon1); 
        
        Polygon polygon2 = new Polygon(); 
        polygon2.getPaths().add(marker.getLatlng());  
        polygon2.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(2).getSpeed(),listeDirection.get(2).getAngle(), listeDirection.get(2).getHeurspropagation(), marker.getLatlng()));  
        polygon2.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(3).getSpeed(),listeDirection.get(3).getAngle(), listeDirection.get(3).getHeurspropagation(), marker.getLatlng()));  
  
        polygon2.setStrokeColor(listeDirection.get(2).getCouleur());  
        polygon2.setFillColor(listeDirection.get(2).getCouleur());  
        polygon2.setStrokeOpacity(0.2);  
        polygon2.setFillOpacity(0.2);  
          
        emptyModel.addOverlay(polygon2); 
        
        Polygon polygon3 = new Polygon(); 
        polygon3.getPaths().add(marker.getLatlng());  
        polygon3.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(1).getSpeed(),listeDirection.get(1).getAngle(), listeDirection.get(1).getHeurspropagation(), marker.getLatlng()));  
        polygon3.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(listeDirection.get(2).getSpeed(),listeDirection.get(2).getAngle(), listeDirection.get(2).getHeurspropagation(), marker.getLatlng()));  
  
        polygon3.setStrokeColor(listeDirection.get(1).getCouleur());  
        polygon3.setFillColor(listeDirection.get(1).getCouleur());  
        polygon3.setStrokeOpacity(0.2);  
        polygon3.setFillOpacity(0.2);  
          
        emptyModel.addOverlay(polygon3); 
    }
    
    
   
    
    
    
}
