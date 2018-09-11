package com.michael.email.email;

import com.michael.email.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;


// many side is always the owner
// update the owner after change
@Setter
@Getter
@Accessors(chain = true)
@Entity
public class Email {

	@Id
	String id;
	String content;
	String title;

	@ManyToOne
	User sentByUser;

	//unidirectional: entity with Annotation is always the owner.
	@OneToOne
	User toUser;


	@PrePersist
	void init() {
		this.id = UUID.randomUUID().toString();
	}


}
