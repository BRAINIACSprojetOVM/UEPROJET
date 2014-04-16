/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Beans.Adresse;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author mehdi
 */
public class GeocodeParser {
    
    private InputStream makeCallService(URL url) throws IOException {
        //open the connection
	HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
        //connect to the url
        urlConnect.connect();
        if (urlConnect.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return urlConnect.getInputStream();
        }
        return null;
    }
    
        public String getAdresses(String adr) {
        String geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=Toulouse&components=country:FR&sensor=true&key=AIzaSyD9oox4BUyXmIq4Rp_-tftsUJWgC4T29qk";
        try {
            geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(adr, "UTF-8")+"&components=country:FR&sensor=true&key=AIzaSyD9oox4BUyXmIq4Rp_-tftsUJWgC4T29qk";
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GeocodeParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream inputStream = null;
        try {
            //send the request
            inputStream = makeCallService(new URL(geocodeUrl));
        } catch (MalformedURLException ex) {
            Logger.getLogger(GeocodeParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeocodeParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        //we have a result
        if (inputStream != null) {
            Scanner s = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return null;
    }
     
      
      private Adresse pGetAdresse(JsonElement elt) {
          Adresse adresse = new Adresse();
        JsonObject obj = elt.getAsJsonObject();
        
        String adr = obj.get("formatted_address").toString().replace("\"", "");
         System.out.println(adr);
      JsonObject geo = obj.getAsJsonObject("geometry");
        JsonObject latlng = geo.getAsJsonObject("location");
        String lat = latlng.get("lat").toString().replace("\"", "");
        String lng= latlng.get("lng").toString().replace("\"", "");
        System.out.println(lng+" "+lat+"  "+ adr);
        
       
        adresse.setAdresseFormate(adr);
       adresse.setLatlng(new LatLng(Double.parseDouble(lat),Double.parseDouble(lng)));
      
       return adresse;
        
    }
       public static List<Adresse> getListAdresses(String adr)
       {
           
       List<Adresse> list=new ArrayList<>();
       GeocodeParser g = new GeocodeParser();
         
         JsonElement elt = new JsonParser().parse(g.getAdresses(adr));
        JsonObject obj = elt.getAsJsonObject();
        JsonArray array =obj.getAsJsonArray("results");
        for (JsonElement jsonElement : array) {
            System.out.println(jsonElement);
           list.add(g.pGetAdresse(jsonElement));
        }
       
       
       return list;
       }
 public static void main(String[] args) {
        // TODO code application logic here
          /* VelibParser v = new VelibParser();
         
         JsonElement elt = new JsonParser().parse(v.getStations());
        JsonArray array = elt.getAsJsonArray();
        */
        /*    for (JsonElement jsonElement : array) {
           v.pGetStation(jsonElement);
        }
        */
    for(Adresse s:GeocodeParser.getListAdresses("Camille Sou")) 
      {
         System.out.println(s);
        
      }
           
    }
}
