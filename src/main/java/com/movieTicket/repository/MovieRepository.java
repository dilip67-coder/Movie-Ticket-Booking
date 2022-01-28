package com.movieTicket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieTicket.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
