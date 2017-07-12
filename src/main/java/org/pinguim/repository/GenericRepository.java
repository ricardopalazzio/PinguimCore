/*
 * The MIT License
 *
 * Copyright 2017 Ricardo Palazzio.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.pinguim.repository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author tux
 * @param <T>
 */
@Dependent
public abstract class GenericRepository<T> {

    @Inject
    protected EntityManager em;

    private Class<T> classe;

    @PostConstruct
    private void init() {

        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        classe = (Class) parameterizedType.getActualTypeArguments()[0];

    }

    public List<T> findAll(SingularAttribute attr, boolean asc) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(classe);
        Root<T> root = query.from(classe);
        CriteriaQuery<T> select = query.select(root);

        if (attr != null) {
            if (asc) {
                select.orderBy(builder.asc(root.get(attr)));
            } else {
                select.orderBy(builder.desc(root.get(attr)));
            }
        }
        
        return em.createQuery(select).getResultList();
    }
   
    public T findById(Long id){
        return em.find(classe, id);
    }
    
    public List<T> findPaging(Integer startPosition, Integer maxResult, SingularAttribute attr, boolean asc) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(classe);
        Root<T> root = query.from(classe);

        CriteriaQuery<T> select = query.select(root);

        if (attr != null) {
            if (asc) {
                select.orderBy(builder.asc(root.get(attr)));
            } else {
                select.orderBy(builder.desc(root.get(attr)));
            }
        }

        TypedQuery<T> typedQuery = em.createQuery(select);
        if(startPosition!=null) typedQuery.setFirstResult(startPosition);
        if(maxResult!=null) typedQuery.setFirstResult(maxResult);

        return typedQuery.getResultList();
    }
            
    /**
     * Use before ObjectUtil.prepareObject 
     * @param obj
     * @param attr
     * @param asc
     * @return List<T> 
     */ 
    public List<T> findSelf(T obj, SingularAttribute attr, boolean asc) {
       
       CriteriaBuilder builder = em.getCriteriaBuilder();
       CriteriaQuery<T> query  = builder.createQuery(classe);
       Root<T> root = query.from(classe);
       CriteriaQuery<T> select    =  query.select(root);
        
       
       Field[] fields = classe.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (!f.isAnnotationPresent(Transient.class) &&
                   f.isAnnotationPresent(OneToMany.class) &&
                    !f.getType().isPrimitive()) {
                try {
                    
                    Object value = f.get(obj);
                    
                    if (value != null) 
                        select.where(builder.equal(root.get(f.getName()), value));
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        select.distinct(true);
        
        if (attr != null) {
            if(asc) 
                select.orderBy(builder.asc(root.get(attr)));
            else
                select.orderBy(builder.desc(root.get(attr)));
                       
        }
        return em.createQuery(select).getResultList();
    }

}
