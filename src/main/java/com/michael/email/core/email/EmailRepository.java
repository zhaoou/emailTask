package com.michael.email.core.email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, String>  {

//    @Query(value = "SELECT * FROM email  WHERE from_Id=?1 ", nativeQuery = true)
//    List<Email> findAllByFromId(String id);
//
//    @Query(value = "SELECT * FROM email  WHERE to_Id=?1 ", nativeQuery = true)
//    List<Email> findAllByToId(String id);


    List<Email> findByToUserId(String id);
}
