package com.proyecto.naves.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

	
    private static final Logger logger = LoggerFactory.getLogger(Aspect.class);

    @Before("execution(* com.proyecto.naves.controller.*.*(..)) && args(id,..)")
    public void logIfNegativeId(JoinPoint joinPoint, Long id) {
        if (id < 0) {
            logger.warn("El ID pasado como parámetro es negativo: " + id);
        }
    }
	
}
