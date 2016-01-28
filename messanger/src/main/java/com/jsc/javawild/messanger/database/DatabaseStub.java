package com.jsc.javawild.messanger.database;

import java.util.HashMap;
import java.util.Map;

import com.jsc.javawild.messanger.model.Comment;
import com.jsc.javawild.messanger.model.Message;
import com.jsc.javawild.messanger.model.Profile;

public class DatabaseStub {
	public static Map<Long, Message> messages = new HashMap<Long, Message>();
	public static Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	
	public static Map<Long, Comment> comments = new HashMap<Long, Comment>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

	public static Map<Long, Comment> getComments() {
		return comments;
	}

}
