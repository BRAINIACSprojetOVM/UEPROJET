package MyManagedBeans;

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
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;
import service.LatLongHelper;

@ManagedBean(name = "myBean")
@SessionScoped
public class MyBean implements Serializable {
private static final long serialVersionUID = 1L;
    private MapModel emptyModel;
    private LatLng latlng;
    private DirectionInnondation direction; 
    private boolean afficheStart;
    private double compteurHeurs;
    private List<MarkerAngles> listMarker;
    private String zoom;
    private LatLng latlngInitial;
    private double latinitial;
    private double lnginitial;
    private String tempo;
    private String geo;

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }
    

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
    
    public double getLatinitial() {
        return latinitial;
    }

    public void setLatinitial(double latinitial) {
        this.latinitial = latinitial;
    }

    public double getLnginitial() {
        return lnginitial;
    }

    public void setLnginitial(double lnginitial) {
        this.lnginitial = lnginitial;
    }

   
    
    public LatLng getLatlngInitial() {
        return latlngInitial;
    }

    public void setLatlngInitial(LatLng latlngInitial) {
        this.latlngInitial = latlngInitial;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
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

    public DirectionInnondation getDirection() {
        return direction;
    }

    public void setDirection(DirectionInnondation direction) {
        this.direction = direction;
    }
 @PostConstruct
   public void init() {
     emptyModel = new DefaultMapModel();
        direction=new DirectionInnondation();
        direction.setHeurspropagation(0);
        latlng = new LatLng(43.59633699595024, 1.4536070823669434);
        listMarker = new ArrayList<>();
        zoom = "13";
        latlngInitial = new LatLng(43.59633699595024, 1.4536070823669434);
        latinitial=43.59633699595024;
        lnginitial=1.4536070823669434;
        geo="43.59633699595024,1.4536070823669434";
        afficheStart = false;
        tempo="0 h 0 min 0 rs ";
     
     }
    public MyBean() {
        

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
        latlng = event.getLatLng();
       direction=new DirectionInnondation();
       
        Marker marker = new Marker(latlng,null,null,"images/vague.png");
        MarkerAngles markerAngle = new MarkerAngles();
        markerAngle.setMarker(marker);

        listMarker.add(markerAngle);
        emptyModel.addOverlay(marker);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marqueur établie ! ", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
    }

   
    //important

    public void showInnondationDemo() {
        double aux=direction.getHeurspropagation() + 0.2;
        //System.out.println(direction);
         direction.setHeurspropagation(aux);
         
        Polyline polyline = new Polyline();  
        polyline.getPaths().add(listMarker.get(listMarker.size() - 1).getMarker().getLatlng());   
        polyline.getPaths().add(LatLongHelper.latlngBySpeedAngleSeconde(direction.getSpeed(),direction.getAngle(), direction.getHeurspropagation(), listMarker.get(listMarker.size() - 1).getMarker().getLatlng()));  
        polyline.setStrokeWeight(10);  
        polyline.setStrokeColor("#0099ff");  
        polyline.setStrokeOpacity(0.7);  
          
       emptyModel.addOverlay(polyline);
       /*
        listMarker.get(listMarker.size() - 1).getListeDirection().add(direction);
        listMarker.get(listMarker.size() - 1).addListePolyToMdoel(emptyModel);*/
        compteurHeurs = direction.getHeurspropagation() * 60*60;
        afficheStart = true;
        Integer seconde;
        seconde =(int)Math.floor(direction.getHeurspropagation() * 60*60);         
         Date T=new Date();
         T.setSeconds(seconde);
         tempo=T.getHours()+" h "+T.getMinutes()+" min "+T.getSeconds()+" rs ";
    }

    public void showSart() {
        afficheStart = true;
    }

      public String temps()
     {
        double seconde;
        seconde =compteurHeurs;
    int m,h,rs;
h=  (int) Math.floor(seconde/3600);
m=(int)Math.floor(seconde)%60;
rs=(int)Math.floor(seconde)%60;
    return m+" h "+m+" min "+rs+" rs ";
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
}
