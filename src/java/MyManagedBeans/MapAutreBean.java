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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
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
import service.GeocodeParser;
import service.LatLongHelper;

@ManagedBean(name = "mapAutreBean")
@SessionScoped
public class MapAutreBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private MapModel emptyModel;
    private LatLng latlng;
    private boolean afficheStart;
    private double compteurHeurs;
    private List<MarkerAngles> listMarker;
    private int zoom;
    private LatLng latlngInitial;
    private double v1;
    private double v2;
    private double v3;
    private double v4;
    private List<String> MarkerImage;
    private String imageCourante;
    private Marker markerSelectionne;
   
    

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

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public double getV3() {
        return v3;
    }

    public void setV3(double v3) {
        this.v3 = v3;
    }

    public double getV4() {
        return v4;
    }

    public void setV4(double v4) {
        this.v4 = v4;
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
        imageCourante = "0.png";


    }

    public MapAutreBean() {
        polygonList = new ArrayList<>();
        markerDessin = new ArrayList<>();
        polygon = new Polygon();
        MarkerImage = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            MarkerImage.add(i + ".png");
        }
        imageCourante = "0.png";
        markerSelectionne = new Marker(latlngInitial);
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

    public void onStateChange(StateChangeEvent event) {
        LatLngBounds bounds = event.getBounds();
        int zoomLevel = event.getZoomLevel();
        latlngInitial = event.getCenter();
        zoom = zoomLevel;
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
            Marker marker = new Marker(latlng, null, null, "images/marker/" + imageCourante);
            MarkerAngles markerAngle = new MarkerAngles();
            markerAngle.getListeDirection().add(direction1);
            markerAngle.getListeDirection().add(direction2);
            markerAngle.getListeDirection().add(direction3);
            markerAngle.getListeDirection().add(direction4);
            markerAngle.setMarker(marker);

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
//        double aux=direction.getHeurspropagation() + 0.2;
//        //System.out.println(direction);
//         direction.setHeurspropagation(aux);
        latlngInitial = markeur.getMarker().getLatlng();
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
        if(v1!=0||v2!=0||v3!=0||v4!=0)
        {
      listMarker.get(listMarker.size() - 1).getListeDirection().get(0).setSpeed(v1);
        listMarker.get(listMarker.size() - 1).getListeDirection().get(1).setSpeed(v2);
        listMarker.get(listMarker.size() - 1).getListeDirection().get(2).setSpeed(v3);
        listMarker.get(listMarker.size() - 1).getListeDirection().get(3).setSpeed(v4);
        afficheStart = true;}
        else
        {
        addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur","Vous avez entrer 4 vitesse null !"));
        MarkerAngles m=new MarkerAngles();
        m=listMarker.get(listMarker.size() - 1);
        listMarker.remove(m);
        emptyModel.getMarkers().remove(m.getMarker());
        
        
        }
        
    }

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

    }

    public String temps() {
        double seconde;
        seconde = compteurHeurs;
        int m, h, rs;
        h = (int) Math.floor(seconde / 3600);
        m = (int) Math.floor(seconde) % 60;
        rs = (int) Math.floor(seconde) % 60;
        return m + " h " + m + " min " + rs + " rs ";
    }
    //µµµµµµµµµµµµµµµµ**********************************************************************µµµµµµµµµµµµµµµµµµµµµµµµ

    public void aplySpeedCircle() {
        Circle circle1 = new Circle(latlng, 500);
        circle1.setStrokeColor("#d93c3c");
        circle1.setFillColor("#d93c3c");
        circle1.setFillOpacity(0.5);
        emptyModel.addOverlay(circle1);

    }

    public void test() {
        Polyline polyline = new Polyline();

        polyline.getPaths().add(listMarker.get(listMarker.size() - 1).getMarker().getLatlng());
        polyline.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(1000, 90, 1, listMarker.get(listMarker.size() - 1).getMarker().getLatlng()));

        polyline.setStrokeWeight(10);
        polyline.setStrokeColor("#59c3e2");
        polyline.setStrokeOpacity(0.7);

        emptyModel.addOverlay(polyline);

        Polyline polyline1 = new Polyline();

        polyline1.getPaths().add(listMarker.get(listMarker.size() - 1).getMarker().getLatlng());
        polyline1.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(1000, 180, 1, listMarker.get(listMarker.size() - 1).getMarker().getLatlng()));

        polyline1.setStrokeWeight(10);
        polyline1.setStrokeColor("#a81800");
        polyline1.setStrokeOpacity(0.7);

        emptyModel.addOverlay(polyline1);

        Polyline polyline2 = new Polyline();

        polyline2.getPaths().add(listMarker.get(listMarker.size() - 1).getMarker().getLatlng());
        polyline2.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(1000, 270, 1, listMarker.get(listMarker.size() - 1).getMarker().getLatlng()));

        polyline2.setStrokeWeight(10);
        polyline2.setStrokeColor("#7cf500");
        polyline2.setStrokeOpacity(0.7);

        emptyModel.addOverlay(polyline2);

        Polyline polyline3 = new Polyline();

        polyline3.getPaths().add(listMarker.get(listMarker.size() - 1).getMarker().getLatlng());
        polyline3.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(1000, 360, 1, listMarker.get(listMarker.size() - 1).getMarker().getLatlng()));

        polyline3.setStrokeWeight(10);
        polyline3.setStrokeColor("#ffff9c");
        polyline3.setStrokeOpacity(0.7);

        emptyModel.addOverlay(polyline3);


    }
    //***Dessin**//
    //***Dessin**//
    private boolean dessin;
    private Polygon polygon;
    private List<Polygon> polygonList;
    private List<Marker> markerDessin;

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
        polygon = new Polygon();
        
        dessin = true;
    }

    public void finDessiner() {
        for (Marker m : markerDessin) {
            emptyModel.getMarkers().remove(m);
        }
        markerDessin.clear();
        dessin = false;
    }

    public void validerPoly() {
        if (!polygon.getPaths().isEmpty()) {
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
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     //*****************************************
     
  
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
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Adresse choisi :" + event.getObject().toString(), null);  
          current=  GeocodeParser.getListAdresses(event.getObject().toString()).get(0);
          System.out.println(current);
          latlngInitial=current.getLatlng();
    FacesContext.getCurrentInstance().addMessage(null, message);  
}  
     
     
     
     
     
}
