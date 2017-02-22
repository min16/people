package com.handstudio.annotation;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessRole {
	
	public enum AccessRoles {
		All, ADMIN
	}
	
	public AccessRoles role() default AccessRoles.All;
}

