package com.raihan.springsecurity.repository;

import com.raihan.springsecurity.entity.Users;
import com.raihan.springsecurity.services.UsersInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = """
                    select full_name as fullName, 
                           username as username,
                           phone_number as phoneNumber,
                           email as email
                    from user_info;""", nativeQuery = true)
    List<UsersInfoProjection> getAllUser();

    Users findUsersByEmail(String email);
}
