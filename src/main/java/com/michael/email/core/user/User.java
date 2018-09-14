package com.michael.email.core.user;


import com.michael.email.core.email.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.*;

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
	List<Email> sentEmails = new ArrayList<>();

	@PrePersist
	void init() {
		this.id = UUID.randomUUID().toString();
	}


}
