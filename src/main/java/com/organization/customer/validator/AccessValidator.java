package com.organization.customer.validator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class AccessValidator implements org.springframework.validation.Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String firstName = (String) target;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().toString();

        if(!loggedInUser.equals(firstName) && !role.equals("ROLE_ADMIN")){
            errors.reject("100", "Requested information unavailable");
        }

    }
}
