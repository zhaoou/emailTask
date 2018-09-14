package com.michael.email.core.email;

import com.michael.email.core.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

// many side is always the owner
// update the owner after change
@Setter
@Getter
@Accessors(chain = true)
@Entity
@ToString(exclude = {"sentByUser", "toUser"})
public class Email {

	@Id
	String id;
	String content;
	String title;
	public long createdTime;
	int countInSameMinute;

	@ManyToOne
	@JsonIgnore
	User sentByUser;

	//unidirectional: entity with Annotation is always the owner.
	@OneToOne
	@JsonIgnore
	User toUser;



	@PrePersist
	void init() {
		this.id = UUID.randomUUID().toString();
		createdTime = ZonedDateTime.now().getMinute();
	}


}
