package com.movieTicket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieTicket.model.Bookings;
import com.movieTicket.repository.BookingReposotory;

@Service
public class BookingService {
	
	@Autowired
	private BookingReposotory repository;
	
	public List<Bookings> getAllbookings() {
		return repository.findAll();
	}
	public void saveBooking(Bookings booking) {
		repository.save(booking);
	}
	public List<Bookings> getAllBookingsById(Long id) {
		boolean flag=false;
		List<Bookings> bookingById = null;
		List<Bookings> findAll = repository.findAll();
		String userId = null;
		
		for(int i = 0; i < findAll.size(); i++) {
		userId = findAll.get(i).getUserId();
		long parseLong = Long.parseLong(userId);
			if(id == parseLong) {
				bookingById = repository.findAllById(userId);
				/* System.out.println("Bookings ==> "+bookingById); */
				flag = true;
				
			}
		}
		
	return bookingById;
			
	}
}
