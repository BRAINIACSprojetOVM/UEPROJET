/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mehdi
 */
@ManagedBean(name = "beanTime")
@SessionScoped
public class BeanTime {
    
   public String temps(double seconde)
     {
    int m,h,rs;
h=  (int) Math.floor(seconde/3600);
m=(int)Math.floor(seconde)%60;
rs=(int)Math.floor(seconde)%60;
    return m+" h "+m+" min "+rs+" rs ";
    }
    
}
