package com.michael.email.user;


import com.michael.email.email.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@ToString(exclude = "sentEmails" )
public class User {

	@Id
	String id;
	String name;

	@OneToMany(mappedBy="sentByUser", fetch=FetchType.EAGER)
	Set<Email> sentEmails = new HashSet<>();

	@PrePersist
	void init() {
		this.id = UUID.randomUUID().toString();
	}


}
