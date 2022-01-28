package com.movieTicket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movieTicket.model.Bookings;

public interface BookingReposotory extends JpaRepository<Bookings, Long> {

	@Query("from Bookings b where b.userId =:bookingId")
	List<Bookings> findAllById(@Param("bookingId") String bookingId);

}
