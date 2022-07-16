package com.javierlobo.loginmedummy.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Bean;

import com.javierlobo.loginmedummy.zuul.filters.CustomFilter;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}

	@Bean
	public CustomFilter preFilter() {
		return new CustomFilter(FilterConstants.PRE_TYPE, true);
	}
	
	@Bean
	public CustomFilter postFilter() {
		return new CustomFilter(FilterConstants.POST_TYPE, true);
	}
	
	@Bean
	public CustomFilter errorFilter() {
		return new CustomFilter(FilterConstants.ERROR_TYPE, true);
	}
	
	@Bean
	public CustomFilter routeFilter() {
		return new CustomFilter(FilterConstants.ROUTE_TYPE, true);
	}
	
}

/*
 * URL's:
 *   - http://localhost:8005/api/whois
 *   - http://localhost:8090/gateway/api/whois
 *   
 * 
 * Notas: 
 * https://www.google.com/search?q=spring+zuul+server&sxsrf=APq-WBsZzbJ5-C3LeV-iNcId-bLsYQfrKQ%3A1646773851572&ei=W8YnYum_Iv-W9u8P49-FiA0&oq=spring+zuul&gs_lcp=Cgdnd3Mtd2l6EAMYADIECCMQJzIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLAToHCCMQsAMQJzoHCAAQRxCwAzoHCAAQsAMQQzoKCAAQ5AIQsAMYADoSCC4QxwEQowIQyAMQsAMQQxgBOhUILhDHARDRAxDUAhDIAxCwAxBDGAE6DwguENQCEMgDELADEEMYAToSCC4QxwEQ0QMQyAMQsAMQQxgBOgoIABCxAxCDARBDOgcIABCxAxBDOgQIABBDOggIABCABBCxAzoLCAAQgAQQsQMQgwE6BQgAEIAEOgQIABAKSgQIQRgASgQIRhgBUIgFWM0TYP0eaAFwAXgAgAF0iAHRA5IBAzQuMZgBAKABAcgBE8ABAdoBBggAEAEYCdoBBggBEAEYCA&sclient=gws-wiz
 * http://www.profesor-p.com/2019/03/16/zuul-para-redirigir-peticiones-rest-en-spring-boot/
 */