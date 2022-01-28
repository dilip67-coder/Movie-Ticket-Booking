package com.movieTicket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Movie {

	@Id
	@GeneratedValue
	private long movieId;
	
	private String movieName;
	private String movieDesc;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String movieImage;
	
	private String ticketPrice;
	
	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public String getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(String string) {
		this.movieImage = string;
	}

	
}
