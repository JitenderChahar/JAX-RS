package com.jsc.javawild.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jsc.javawild.messanger.database.DatabaseStub;
import com.jsc.javawild.messanger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseStub.getProfiles();

	public ProfileService() {
		Profile p1 = new Profile(1L, "Jittu", "Chahar", "jittu.chahar");
		Profile p2 = new Profile(2L, "Mannu", "Chahar", "mannu.chahar");
		profiles.put(p1.getProfileName(), p1);
		profiles.put(p2.getProfileName(), p2);
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile deleteProfile(String profileName) {
		return profiles.remove(profileName);
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty())
			return null;
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
}
