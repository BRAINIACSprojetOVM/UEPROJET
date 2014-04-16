/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;



/**
 *
 * @author mehdi
 */
public class DirectionInnondation implements Serializable{
      private static final long serialVersionUID = 1L;
   private double speed;
   private double angle;
   private double heurspropagation;
   private String couleur;

    public DirectionInnondation() {
        couleur="#3f7cec";
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getHeurspropagation() {
        return heurspropagation;
    }

    public void setHeurspropagation(double heurspropagation) {
        this.heurspropagation = heurspropagation;
    }

    @Override
    public String toString() {
        return "DirectionInnondation{" + "speed=" + speed + ", angle=" + angle + ", heurspropagation=" + heurspropagation + '}';
    }
  
    
}
