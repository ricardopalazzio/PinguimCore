/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pinguim.security;


import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Priority;
import javax.inject.Inject;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
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
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    @AuthenticatedUser User user;
        
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

     
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<RolesEnum> classRoles = extractRoles(resourceClass);

     
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<RolesEnum> methodRoles = extractRoles(resourceMethod);

        try {

            if (methodRoles.isEmpty()) {
                checkPermissions(classRoles);
            } else {
                checkPermissions(methodRoles);
            }

        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN).entity("voce nao tem acesso amiguinho").build());
        }
    }

    // Extract the roles from the annotated element
    private List<RolesEnum> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<RolesEnum>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<RolesEnum>();
            } else {
                RolesEnum[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }


    private void checkPermissions(List<RolesEnum> allowedRoles ) throws Exception {
        if(!allowedRoles.isEmpty()){
            
            if(user.getRole() == null)
                throw new Exception("Voce nao tem permissao");
            
            if(!allowedRoles.contains(user.getRole()))
                 throw new Exception("Voce nao tem permissao");
        }
    }
}