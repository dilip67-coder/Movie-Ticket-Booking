package com.movieTicket.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movieTicket.model.Movie;
import com.movieTicket.repository.MovieRepository;

@Service
public class MovieService {
	@Autowired
	private MovieRepository repo;
	
	public List<Movie> getAllMovies() {
		
		return repo.findAll();
	}

	public void saveMovie(MultipartFile file, String movieName, String movieDesc, String ticketPrice) {
	
		Movie movie = new Movie();
		movie.setMovieName(movieName);
		movie.setMovieDesc(movieDesc);
		movie.setTicketPrice(ticketPrice);
		
	try {
		movie.setMovieImage(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	repo.save(movie);
		
	}

	public Movie getMovieById(long id) {
		Optional<Movie> movie = repo.findById(id);
		return movie.get();
	}
	
	
}
