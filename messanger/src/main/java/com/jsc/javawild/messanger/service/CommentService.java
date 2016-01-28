package com.jsc.javawild.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jsc.javawild.messanger.database.DatabaseStub;
import com.jsc.javawild.messanger.model.Comment;
import com.jsc.javawild.messanger.model.ErrorMessage;
import com.jsc.javawild.messanger.model.Message;

public class CommentService {
	Map<Long, Message> messages = DatabaseStub.getMessages();

	public CommentService() {
	}

	public List<Comment> getAllComments(long messageId) {
		return new ArrayList<Comment>(messages.get(messageId).getComments()
				.values());
	}

	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404,
				"google.com");

		Response response = Response.status(Status.NOT_FOUND)
				.entity(errorMessage).build();
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}

		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}

	public Comment removeComment(long messageId, long commentId) {
		return messages.get(messageId).getComments().remove(commentId);
	}

	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> map = messages.get(messageId).getComments();
		comment.setId((long) (map.size() + 1));
		map.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		if (messages.get(messageId).getComments().size() <= 0) {
			return null;
		}

		messages.get(messageId).getComments().put(comment.getId(), comment);
		return comment;
	}

}
