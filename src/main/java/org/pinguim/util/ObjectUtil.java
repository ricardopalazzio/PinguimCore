
package org.pinguim.util;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author r.palazzio
 */
public class ObjectUtil {
 
    /**
     * Clear all object attributes for search,
     * noted with @Column @ManyToOne @OneToOne.
     * Attention: the  object can`t to have a pritive attributes .
     * @param <T>
     * @param obj
     * @return the same object
     */
    public static <T> T prepareObject(T obj){
        if(obj==null){
            try {
                obj =(T) obj.getClass().newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
  
                try {
                    if( !f.getType().isPrimitive() && (f.getType() instanceof Serializable || f.getType().isEnum()) )
                        f.set(obj, null);   
                } catch (IllegalAccessException ex) {
                     
                }
        }
        return obj;
        }

    }
