package com.michael.email.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity

public class User {

	@Id
	private String id;
	private String name;
	private String emailAddress;

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<Email> emails = new HashSet<>();

//	private Set<Email> fromEmails = new HashSet<>();
//	private Set<Email> toEmails = new HashSet<>();

	public String getId() {
		return id;
	}

	public User() {
	}
	public User(String name, String emailAddress) {
		this.name =name;
		this.emailAddress = emailAddress;
	}

	public User(String id, String name, String emailAddress) {
		this.id = id;
		this.name =name;
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	@PrePersist
	void init() {
		this.id = UUID.randomUUID().toString();
	}

//	@OneToMany(mappedBy = "user")
//	@JoinColumn(name = "userId")
//	public Set<Email> getFromEmails(){
//		return fromEmails;
//	}
//	public void setFromEmails(Set<Email> fromEmails) {
//		this.fromEmails = fromEmails;
//	}
//
//
//
//	//@JoinColumn(name = "userId")
//	public Set<Email> getToEmails(){
//		return toEmails;
//	}
//	public void setToEmails(Set<Email> toEmails) {
//		this.toEmails = toEmails;
//	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				'}';
	}


}
