package com.michael.email.persistense;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michael.email.model.Email;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, String>  {

    @Query(value = "SELECT * FROM email  WHERE fromId=?1 ", nativeQuery = true)
    List<Email> findAllByFromId(String id);

    @Query(value = "SELECT * FROM email  WHERE toId=?1 ", nativeQuery = true)
    List<Email> findAllByToId(String id);
}
