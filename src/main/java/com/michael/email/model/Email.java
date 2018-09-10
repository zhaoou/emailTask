package com.michael.email.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Email {

	@Id
	private String id;

	private String fromId;
	private String toId;
	private String content;

	@ManyToOne
	private User user;

	public Email() {
	}

	public Email(String fromId, String toId, String content) {
		this.fromId = fromId;
		this.toId = toId;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@PrePersist
	void init() {
		this.id = UUID.randomUUID().toString();
	}


	//@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Email{" +
				"id=" + id +
				", fromId='" + fromId + '\'' +
				", toId='" + toId + '\'' +
				", content='" + content + '\'' +
				'}';
	}

}
