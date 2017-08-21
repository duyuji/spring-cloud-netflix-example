package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ClientService {
	
	@Autowired
	private EurekaClient discoveryClient;

	@HystrixCommand(fallbackMethod = "defaultServiceUrl")
	public String serviceUrl() {
	    InstanceInfo instance = discoveryClient.getNextServerFromEureka("SETOR", false);
	    return instance.getHomePageUrl();
	}
	
	public String defaultServiceUrl() {
		return "Circuit breaker is open";
	}
	
}
