package com.movieTicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieTicket.model.RegisterUser;

public interface UserRepository extends JpaRepository<RegisterUser, Long> {

}
