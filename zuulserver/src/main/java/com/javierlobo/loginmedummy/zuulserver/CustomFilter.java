package com.javierlobo.loginmedummy.zuulserver;

import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class CustomFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(CustomFilter.class);
  
  private String filterType; 
  
  public CustomFilter(String filterType) {
	  this.filterType = filterType;
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
    return true;
  }

  @Override
  public Object run() {
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
    return null;
  }

}