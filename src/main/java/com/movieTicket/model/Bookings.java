package com.movieTicket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bookings")
@Data
public class Bookings {

	@Id
	@GeneratedValue
	private long bookingId;
	
	private String movieName;
	private String name;
	private String showTime;
	private String singleTicketPrice;
	private int ticketCount;
	private String totalTicketPrice;
	private String seatRow;
	private String seatNumber;
	private String bookingSerialNumber;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String bookingQrCode;
	
	private String userId;
	
	
	
}
