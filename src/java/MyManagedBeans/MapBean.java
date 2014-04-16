package MyManagedBeans;


      

import java.io.Serializable;  
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.Circle;
  
import org.primefaces.model.map.DefaultMapModel;  
import org.primefaces.model.map.LatLng;  
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;  
import org.primefaces.model.map.Marker;  
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;
  @ManagedBean(name = "mapBean")
@SessionScoped
public class MapBean implements Serializable {  
  
    private MapModel simpleModel; 
     private Marker marker;  
  
    
    public MapBean() {  
        simpleModel = new DefaultMapModel();  
          
        //Shared coordinates  
        LatLng coord1 = new LatLng(36.879466, 30.667648);  
        LatLng coord2 = new LatLng(36.883707, 30.689216);  
        LatLng coord3 = new LatLng(36.879703, 30.706707);  
        LatLng coord4 = new LatLng(36.885233, 30.702323);  
          
        //Basic marker  
        simpleModel.addOverlay(new Marker(coord1, "Konyaalti", "konyaalti.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));  
     //   simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));  
        simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));  
        simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png")); 
        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));  
        for(Marker marker : simpleModel.getMarkers()) {  
            marker.setDraggable(true);  
        }  
        Polyline polyline = new Polyline();  
        polyline.getPaths().add(coord1);  
        polyline.getPaths().add(coord2);  
        polyline.getPaths().add(coord3);  
        polyline.getPaths().add(coord4);  
          
        polyline.setStrokeWeight(10);  
        polyline.setStrokeColor("#FF9900");  
        polyline.setStrokeOpacity(0.7);  
          
        simpleModel.addOverlay(polyline); 
                
         LatLng coord5 = new LatLng(36.90392089423511, 30.722064971923828);  
        LatLng coord6 = new LatLng(36.896233503726414, 30.74197769165039);  
        LatLng coord7 = new LatLng(36.89760630882799, 30.71451187133789);  
  //Lat:36.90392089423511, Lng:30.722064971923828
//Lat:36.896233503726414, Lng:30.74197769165039
//Lat:36.89760630882799, Lng:30.71451187133789

        //Polygon  
        Polygon polygon = new Polygon();  
        polygon.getPaths().add(coord5);  
        polygon.getPaths().add(coord6);  
        polygon.getPaths().add(coord7);  
  
        polygon.setStrokeColor("#FF9900");  
        polygon.setFillColor("#FF9900");  
        polygon.setStrokeOpacity(0.7);  
        polygon.setFillOpacity(0.7);  
          simpleModel.addOverlay(polygon); 
          //Lat:36.89740038963697, Lng:30.66661834716797
 LatLng coord8 = new LatLng(36.89740038963697, 30.66661834716797);  
        Circle circle1 = new Circle(coord8, 500);  
            circle1.setStrokeColor("#d93c3c");  
            circle1.setFillColor("#d93c3c");  
            circle1.setFillOpacity(0.5); 
            simpleModel.addOverlay(circle1);
        
    }  
  
    public MapModel getSimpleModel() {  
        return simpleModel;  
    }  
    public void onMarkerDrag(MarkerDragEvent event) {  
        marker = event.getMarker();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));  
    }  
    public void onMarkerSelect(OverlaySelectEvent event) {  
        marker = (Marker) event.getOverlay();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));  
    }  
    public void onStateChange(StateChangeEvent event) {  
        LatLngBounds bounds = event.getBounds();  
        int zoomLevel = event.getZoomLevel();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));  
    }  
      
    public void onPointSelect(PointSelectEvent event) {  
        LatLng latlng = event.getLatLng();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));  
    }  
      
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}  
    
