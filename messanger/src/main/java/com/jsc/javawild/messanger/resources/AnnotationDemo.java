package com.jsc.javawild.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/annotations")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class AnnotationDemo {

	@GET
	@Path("/annotation")
	public String annotations(@MatrixParam("matrix") String matrixValue,
			@HeaderParam("customHeader") String headerValue,
			@CookieParam("name") String value) {
		return "matrixValue is  : " + matrixValue + " header value :"
				+ headerValue + " Cookie param : " + value;
	}
	
	@GET
	@Path("/context")
	public String context(@Context UriInfo info, @Context HttpHeaders headers){
		return "Path : " + info.getAbsolutePath() + "\nrequest headers : " + headers.getRequestHeaders().toString();
	}
}
