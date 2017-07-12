/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pinguim.repository;


import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import org.hibernate.jpa.QueryHints;
import org.pinguim.enumerated.CacheRegionEnum;
import org.pinguim.model.common.Country;

/**
 *
 * @author tux
 */
@ApplicationScoped
public class CountryRepository extends GenericRepository<Country>{
    
    
    
     @Override
     public List<Country> findAll(SingularAttribute attr, boolean asc) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Country> query = builder.createQuery(Country.class);
        Root<Country> root = query.from(Country.class);
        CriteriaQuery<Country> select = query.select(root);

        if (attr != null) {
            if (asc) {
                select.orderBy(builder.asc(root.get(attr)));
            } else {
                select.orderBy(builder.desc(root.get(attr)));
            }
        }
       
        return em.createQuery(select)
                .setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE)
                .setHint(QueryHints.HINT_CACHE_REGION, CacheRegionEnum.GLOBAL.name())
                .getResultList();
    }

}
