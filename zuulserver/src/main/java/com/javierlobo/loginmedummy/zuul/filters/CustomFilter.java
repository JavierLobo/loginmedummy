package com.javierlobo.loginmedummy.zuul.filters;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class CustomFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(CustomFilter.class);
  
  private String filterType;
  
  private Boolean shouldFilter;
  
  public CustomFilter(String filterType) {
	  this.filterType = filterType;
	  this.shouldFilter = false;
  }
  
  public CustomFilter(String filterType, Boolean shouldFilter) {
	  this.filterType = filterType;
	  this.shouldFilter = shouldFilter;
  }
  
  @Override
  public String filterType() {
    return filterType;
  }

  @Override
  public int filterOrder() {
	  return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
  }

  @Override
  public boolean shouldFilter() {
    return shouldFilter;
  }

  @Override
  public Object run() {
	RequestContext ctx = RequestContext.getCurrentContext();
	HttpServletRequest request = ctx.getRequest();
	
	if (FilterConstants.PRE_TYPE.equals(filterType())) {
		log.info("Entrando a PRE filter");
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		
		request.setAttribute("tiempoInicio", System.currentTimeMillis());
		
	} else if (FilterConstants.POST_TYPE.equals(filterType())) {
		log.info("Entrando a POST filter");
		
		Long tiempoInicial = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicial; // Todo: Aquí se puede usar un Duration o Period para calcular el tiempo
		
		log.info(String.format("Tiempo transcurrido en segundos %s", tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s", tiempoTranscurrido.doubleValue()));
	}  
	// newEndPoind(); // ToDo: Este método no se si es util  
    return null;
  }

  private void newEndPoind() {
	RequestContext ctx = RequestContext.getCurrentContext();
    StringBuffer strLog = new StringBuffer();
    
    strLog.append("\n------ NUEVA PETICION ------\n");                     
    strLog.append(String.format("Server: %s Metodo: %s Path: %s \n",
    		ctx.getRequest().getServerName() ,ctx.getRequest().getMethod(), ctx.getRequest().getRequestURI()));
    
    Enumeration<String> enume= ctx.getRequest().getHeaderNames();
    
    String header;    
    while (enume.hasMoreElements()) {
        header = enume.nextElement();
        strLog.append(String.format("Headers: %s = %s \n", header, ctx.getRequest().getHeader(header)));                 
    };
    
    log.info(strLog.toString());
  }

}