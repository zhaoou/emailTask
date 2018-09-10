package com.michael.email.persistense;

import com.michael.email.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>  {

}
