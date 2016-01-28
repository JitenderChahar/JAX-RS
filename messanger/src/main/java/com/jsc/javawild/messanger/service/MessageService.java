package com.jsc.javawild.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsc.javawild.messanger.database.DatabaseStub;
import com.jsc.javawild.messanger.model.Comment;
import com.jsc.javawild.messanger.model.Message;

public class MessageService {
	Map<Long, Message> messages = DatabaseStub.getMessages();

	public MessageService() {
		Message m1 = new Message(1L, "Hello world", "jittu");
		Map<Long, Comment> map1 = new HashMap<Long, Comment>();
		map1.put(1L, new Comment(1L, "bob", "Luking nice"));
		map1.put(2L, new Comment(2L, "Builder", "super cool"));
		m1.setComments(map1);
		
		Message m2 = new Message(2L, "Hello Jersey", "jittu");
		Map<Long, Comment> map2 =new HashMap<Long, Comment>();
		map2.put(1L, new Comment(1L, "bill", "I have send u a bill"));
		map2.put(2L, new Comment(2L, "kill", "i will kill you"));
		m2.setComments(map2);
		
		this.messages.put(m1.getId(), m1);
		this.messages.put(m2.getId(), m2);
		
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();

		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessageForPagination(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()){
			return new ArrayList<Message>();
		}
		
		return list.subList(start, start + size);
	}

	public Message getMessage(long id) {
		return messages.get(id);
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}

	public Message addMessage(Message message) {
		message.setId((long) (messages.size() + 1));
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (messages.size() <= 0) {
			return null;
		}

		messages.put(message.getId(), message);
		return message;
	}

}
