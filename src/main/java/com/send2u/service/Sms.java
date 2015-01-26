package com.send2u.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

@Path("/sms")
public class Sms {
	
	static Logger logger = Logger.getLogger(Sms.class);
	
	@GET
	@Path("/enviar")
	@Produces(MediaType.TEXT_PLAIN)
	public Response enviar(@QueryParam("account") String account,
			@QueryParam("user") String user, @QueryParam("pass") String pass, 
			@QueryParam("to") String to, @QueryParam("msg") String msg,
			@QueryParam("idSms") String idSms,
			@Context HttpServletRequest request, @Context HttpServletResponse response){
		
		ResponseBuilder builder = null;
		String key = null;
		try{
			key=RandomStringUtils.randomAlphanumeric(10).toUpperCase();
			GenericEntity<String> entity = new GenericEntity<String>(key) {	};
			builder = Response.ok(entity);
			logger.info(to+"-"+key);
		}catch(Exception e){
			builder = Response.serverError().entity(e);
		}finally{
			key = null;
		}
		return builder.build();
	}

}
