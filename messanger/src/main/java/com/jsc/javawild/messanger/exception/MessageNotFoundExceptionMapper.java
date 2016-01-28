package com.jsc.javawild.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jsc.javawild.messanger.model.ErrorMessage;

@Provider
public class MessageNotFoundExceptionMapper implements ExceptionMapper<MessageNotFound>{

	@Override
	public Response toResponse(MessageNotFound ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "google it");
		return Response.status(404).entity(errorMessage).build();
	}
	
}
