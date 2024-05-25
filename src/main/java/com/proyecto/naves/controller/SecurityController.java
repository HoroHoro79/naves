package com.proyecto.naves.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.naves.security.SecurityConfig;
import com.proyecto.naves.utils.Constantes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(Constantes.REQUEST_MAPPING_SECURITY)
@Log4j2
public class SecurityController {

	
	
    @Autowired
    private SecurityConfig securityConfig;
    
    private static final String version = "/v1";
    
    
    
    
    @GetMapping(version+Constantes.CREDENTIALS_ENDPOINT)
    public ResponseEntity<Map<String, String>> getCredentials() {
    	 Map<String, String> credentials = new HashMap<>();
         credentials.put("username", securityConfig.getUsuario());
         credentials.put("password", securityConfig.getGeneratedPassword());
        return ResponseEntity.ok(credentials);
    }
    
    @GetMapping(version+Constantes.TOKEN_ENDPOINT)
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        return csrfToken;
    }
}
