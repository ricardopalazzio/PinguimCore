/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pinguim.producer;

import com.mypinguim.pinguimservices.security.ApplicationBean;
import com.mypinguim.pinguimservices.security.AuthenticatedUser;
import com.mypinguim.pinguimservices.security.User;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;


/**
 *
 * @author ricar
 */
@RequestScoped
public class UserProducer {
    
    @Produces
    @RequestScoped
    @AuthenticatedUser
    private User authenticatedUser;
    
     public void handleAuthenticationEvent(@Observes @AuthenticatedUser String token) {
        this.authenticatedUser = findUser(token);
    }

    private User findUser(String token) {
        // Hit the the database or a service to find a user by its username and return it
        // Return the User instance
        return ApplicationBean.getMap().get(token);
    }
}
