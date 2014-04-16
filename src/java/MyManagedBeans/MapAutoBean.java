package MyManagedBeans;

import Beans.Adresse;
import Beans.DirectionInnondation;
import Beans.MarkerAngles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;
import service.GeocodeParser;


@ManagedBean(name = "mapAutoBean")
@SessionScoped
public class MapAutoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private MapModel emptyModel;
    private LatLng latlng;
    private int zoom;
    private boolean afficheStart;
    private double compteurHeurs;
    private List<MarkerAngles> listMarker;
    private LatLng latlngInitial;
    private List<String> MarkerImage;
    private String imageCourante;
    private Marker markerSelectionne;

    
    //***Dessin**//
    private boolean dessin;
    private Polygon polygon;
    private List<Polygon> polygonList;
    private List<Marker> markerDessin;
    
    
    
    
      
    
    
    
    
    
    
    
    
    
    
    
    
    public Marker getMarkerSelectionne() {
        return markerSelectionne;
    }

    public void setMarkerSelectionne(Marker markerSelectionne) {
        this.markerSelectionne = markerSelectionne;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public List<String> getMarkerImage() {
        return MarkerImage;
    }

    public void setMarkerImage(List<String> MarkerImage) {
        this.MarkerImage = MarkerImage;
    }

    public String getImageCourante() {
        return imageCourante;
    }

    public void setImageCourante(String imageCourante) {
        this.imageCourante = imageCourante;
    }

    public LatLng getLatlngInitial() {
        return latlngInitial;
    }

    public void setLatlngInitial(LatLng latlngInitial) {
        this.latlngInitial = latlngInitial;
    }

    public boolean isAfficheStart() {
        return afficheStart;
    }

    public void setAfficheStart(boolean afficheStart) {
        this.afficheStart = afficheStart;
    }

    public double getCompteurHeurs() {
        return compteurHeurs;
    }

    public void setCompteurHeurs(double compteurHeurs) {
        this.compteurHeurs = compteurHeurs;
    }

    public List<MarkerAngles> getListMarker() {
        return listMarker;
    }

    public void setListMarker(List<MarkerAngles> listMarker) {
        this.listMarker = listMarker;
    }

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();

        latlng = new LatLng(43.59633699595024, 1.4536070823669434);
        listMarker = new ArrayList<>();
        zoom = 13;
        latlngInitial = new LatLng(43.59633699595024, 1.4536070823669434);

        afficheStart = false;



    }

    public MapAutoBean() {
       polygonList=new ArrayList<>();
       markerDessin=new ArrayList<>();
       polygon=new Polygon();
        MarkerImage = new ArrayList<>();
        imageCourante = "0.png";
        for (int i = 0; i < 14; i++) {
            MarkerImage.add(i + ".png");
        }
//imageCourante="0.png";

    }

    public void onStateChange(StateChangeEvent event) {
        LatLngBounds bounds = event.getBounds();
        int zoomLevel = event.getZoomLevel();
        latlngInitial = event.getCenter();

        zoom = zoomLevel;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //important
    public void onPointSelect(PointSelectEvent event) {
        if(!dessin)
        {
        latlng = event.getLatLng();
        DirectionInnondation direction1 = new DirectionInnondation();
        DirectionInnondation direction2 = new DirectionInnondation();
        DirectionInnondation direction3 = new DirectionInnondation();
        DirectionInnondation direction4 = new DirectionInnondation();
        direction1.setHeurspropagation(0);
        direction2.setHeurspropagation(0);
        direction3.setHeurspropagation(0);
        direction4.setHeurspropagation(0);
        direction1.setAngle(135);
        direction2.setAngle(90);
        direction3.setAngle(20);
        direction4.setAngle(250);
        direction1.setCouleur("#34f6f8");
        direction2.setCouleur("#345df8");
        direction3.setCouleur("#34f890");
        direction4.setCouleur("#67b0f4");
        direction1.setSpeed(Math.random() * 2500);
        direction2.setSpeed(Math.random() * 2500);
        direction3.setSpeed(Math.random() * 2500);
        direction4.setSpeed(Math.random() * 2500);
        Marker marker = new Marker(latlng, null, null, "images/marker/" + imageCourante);
        MarkerAngles markerAngle = new MarkerAngles();
        markerAngle.getListeDirection().add(direction1);
        markerAngle.getListeDirection().add(direction2);
        markerAngle.getListeDirection().add(direction3);
        markerAngle.getListeDirection().add(direction4);
        markerAngle.setMarker(marker);
        afficheStart = true;
        listMarker.add(markerAngle);
        emptyModel.addOverlay(marker);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marqueur établie ! ", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
        }
        
        else{
            
            
        
        Marker markerss = new Marker(event.getLatLng(), null, null, "images/marker/dessin.png");
         emptyModel.addOverlay(markerss);
         polygon.getPaths().add(markerss.getLatlng()); 
        markerDessin.add(markerss);
         
         
         
        }
        
        
        }

    //important
    public void delete(MarkerAngles markeur) {
        listMarker.remove(markeur);
        emptyModel.getMarkers().remove(markeur.getMarker());
        emptyModel.getPolylines().clear();
        emptyModel.getPolygons().clear();
    }

    public void showInnondationDemo(MarkerAngles markeur) {

        //   latlngInitial=markeur.getMarker().getLatlng();
        for (DirectionInnondation d : markeur.getListeDirection()) {
            d.setHeurspropagation(d.getHeurspropagation() + 0.2);
        }

        markeur.addListePolyToMdoel(emptyModel);
        compteurHeurs = markeur.getListeDirection().get(0).getHeurspropagation() * 60 * 60;
        afficheStart = true;
        Integer seconde;
        seconde = (int) Math.floor(markeur.getListeDirection().get(0).getHeurspropagation() * 60 * 60);
        Date T = new Date();
        T.setHours(0);
        T.setMinutes(0);
        T.setSeconds(seconde);
        markeur.setAvancementTemporelle(T.getHours() + " h " + T.getMinutes() + " min " + T.getSeconds() + " rs ");
    }

    public void showSart() {

        afficheStart = true;
    }
/*
    public void onMarkerSelect(OverlaySelectEvent event) {
           
        markerSelectionne = (Marker) event.getOverlay();


    }

    public void supprimerMarker() {
        MarkerAngles mar = new MarkerAngles();
        for (MarkerAngles m : listMarker) {
            if (m.getMarker().getLatlng().getLat() == markerSelectionne.getLatlng().getLat()) {
                if (m.getMarker().getLatlng().getLng() == markerSelectionne.getLatlng().getLng()) {
                    mar = m;
                }
            }
        }

        listMarker.remove(mar);
        emptyModel.getMarkers().remove(mar.getMarker());
        emptyModel.getPolylines().clear();
        emptyModel.getPolygons().clear();

    }*/
    //***Dessin**//
   

    public List<Marker> getMarkerDessin() {
        return markerDessin;
    }

    public void setMarkerDessin(List<Marker> markerDessin) {
        this.markerDessin = markerDessin;
    }
    
    public boolean isDessin() {
        return dessin;
    }

    public void setDessin(boolean dessin) {
        this.dessin = dessin;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public List<Polygon> getPolygonList() {
        return polygonList;
    }

    public void setPolygonList(List<Polygon> polygonList) {
        this.polygonList = polygonList;
    }
    
    public void dessiner() {
         polygon=new Polygon();
        dessin = true;
    } 
    
     public void finDessiner() {
         for(Marker m:markerDessin)
         {
         emptyModel.getMarkers().remove(m);
         }
         markerDessin.clear();
        dessin = false;
    } 
     
     public void validerPoly()
     {if (!polygon.getPaths().isEmpty())
     {
     emptyModel.addOverlay(polygon);
     
     }
     
     }
     
     private boolean stop;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    
    public void start()
    {
    stop=false;
    }
     public void stoper()
    {
    stop=true;
    }
     
     
     
     
     
     
     
     
     //******list
       private String txt1; 
    private Adresse current;
    
    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public Adresse getCurrent() {
        return current;
    }

    public void setCurrent(Adresse current) {
        this.current = current;
    }
    
    
    
    public List<Adresse> complete(String query) {  
        List<Adresse> results = new ArrayList<>();  
          
        
            results= GeocodeParser.getListAdresses(query);
       
          
        return results;  
    }  
    public void handleSelect(SelectEvent event) {  
  
          current=  GeocodeParser.getListAdresses(event.getObject().toString()).get(0);
          System.out.println(current);
          latlngInitial=current.getLatlng();

    
    
     latlng = current.getLatlng();
        DirectionInnondation direction1 = new DirectionInnondation();
        DirectionInnondation direction2 = new DirectionInnondation();
        DirectionInnondation direction3 = new DirectionInnondation();
        DirectionInnondation direction4 = new DirectionInnondation();
        direction1.setHeurspropagation(0);
        direction2.setHeurspropagation(0);
        direction3.setHeurspropagation(0);
        direction4.setHeurspropagation(0);
        direction1.setAngle(135);
        direction2.setAngle(90);
        direction3.setAngle(20);
        direction4.setAngle(250);
        direction1.setCouleur("#34f6f8");
        direction2.setCouleur("#345df8");
        direction3.setCouleur("#34f890");
        direction4.setCouleur("#67b0f4");
        direction1.setSpeed(Math.random() * 2500);
        direction2.setSpeed(Math.random() * 2500);
        direction3.setSpeed(Math.random() * 2500);
        direction4.setSpeed(Math.random() * 2500);
        Marker marker = new Marker(latlng, null, null, "images/marker/" + imageCourante);
        MarkerAngles markerAngle = new MarkerAngles();
        markerAngle.getListeDirection().add(direction1);
        markerAngle.getListeDirection().add(direction2);
        markerAngle.getListeDirection().add(direction3);
        markerAngle.getListeDirection().add(direction4);
        markerAngle.setMarker(marker);
        afficheStart = true;
        listMarker.add(markerAngle);
        emptyModel.addOverlay(marker);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marqueur établie ! ", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
}  
     
     
     
     
     
}

     
     
     
     

