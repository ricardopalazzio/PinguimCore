/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pinguim.security;

import com.mypinguim.pinguimservices.security.AuthenticatedUser;
import com.mypinguim.pinguimservices.security.Secured;
import java.io.IOException;
import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.pinguim.model.auth.RolesEnum;
import org.pinguim.model.auth.User;

/**
 *
 * @author ricar
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    @AuthenticatedUser
    Event<String> userAuthenticatedEvent;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = 
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || (!authorizationHeader.startsWith("palazzio") && !authorizationHeader.startsWith("denis"))) {
            ApplicationBean.getMap().clear();
            throw new NotAuthorizedException("Authorization header must be provided");
           
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.toUpperCase();

        try {

            // Validate the token
            validateToken(token);

        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean validateToken(String token) throws Exception {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        User user = new User();
        user.setUsername("adminpinguim");
        user.setToken(token);
        if(token.equals("PALAZZIO"))
           user.setRole(RolesEnum.ADMINISTRATOR);
        else
            user.setRole(RolesEnum.MASTER_VENDOR);
        
        ApplicationBean.getMap().put(token, user);
        userAuthenticatedEvent.fire(token);
        
        return true;
    }
}