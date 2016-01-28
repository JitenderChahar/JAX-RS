package com.jsc.javawild.messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.jsc.javawild.messanger.beans.MessageFilterBean;
import com.jsc.javawild.messanger.exception.MessageNotFound;
import com.jsc.javawild.messanger.model.Message;
import com.jsc.javawild.messanger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML }) //content negotiation at class level
public class MessageResource {
	private MessageService messageService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessagesInJSON(@BeanParam MessageFilterBean filter) {
		System.out.println("Inside getMessagesInJSON");
		if (filter.getYear() > 0) {
			return messageService.getAllMessagesForYear(filter.getYear());
		}
		/*
		 * if (filter.getStart() >= 0 && filter.getSize() >= 0) { return
		 * messageService.getAllMessageForPagination(filter.getStart(),
		 * filter.getSize()); }
		 */

		return messageService.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessagesInXML(@BeanParam MessageFilterBean filter) {
		System.out.println("Inside getMessagesInXML");
		if (filter.getYear() > 0) {
			return messageService.getAllMessagesForYear(filter.getYear());
		}
		/*
		 * if (filter.getStart() >= 0 && filter.getSize() >= 0) { return
		 * messageService.getAllMessageForPagination(filter.getStart(),
		 * filter.getSize()); }
		 */

		return messageService.getAllMessages();
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo info) {
		Message m = messageService.addMessage(message);
		String id = String.valueOf(m.getId());

		return Response.created(info.getAbsolutePathBuilder().path(id).build())
				.entity(message).build();

	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id,
			@Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		if (message == null) {
			throw new MessageNotFound("Message with id " + id + " not found");
		}
		message.addUri(getUriForSelf(id, uriInfo), "self");
		message.addUri(getUriForComment(id, uriInfo), "comments");
		return message;
	}

	private String getUriForComment(long id, UriInfo uriInfo) {
		URI uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.resolveTemplate("messageId", id).build();
		return uri.toString();
	}

	private String getUriForSelf(long id, UriInfo uriInfo) {
		URI uri = uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(Long.toString(id)).build();
		return uri.toString();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,
			Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long id) {
		return messageService.removeMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
