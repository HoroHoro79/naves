package com.proyecto.naves.cache;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheClearService {
	
	  private final CacheManager cacheManager;

	    public CacheClearService(CacheManager cacheManager) {
	        this.cacheManager = cacheManager;
	    }

	    @Scheduled(fixedRate = 300000)
	    public void clearNavesByFiltrosCache() {
	        cacheManager.getCache("navesByFiltrosCache").clear();
	    }

	    @Scheduled(fixedRate = 300000)
	    public void clearNavesCache() {
	        cacheManager.getCache("navesCache").clear();
	    }
	    
	    @Scheduled(fixedRate = 300000)
	    public void clearAllNavesCache() {
	        cacheManager.getCache("allNavesCache").clear();
	    }
}
