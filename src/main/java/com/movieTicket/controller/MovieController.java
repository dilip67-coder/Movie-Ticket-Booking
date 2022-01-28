package com.movieTicket.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.WriterException;
import com.movieTicket.model.Bookings;
import com.movieTicket.model.Movie;
import com.movieTicket.qrCode.QRCodeGenerator;
import com.movieTicket.service.BookingService;
import com.movieTicket.service.MovieService;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;
	@Autowired
	private BookingService bService;
	private Movie movie;
	@GetMapping("/")
	public String home( Model model) {
		List<Movie> moviesList = movieService.getAllMovies();
		
		model.addAttribute("movies", moviesList);
		
		return "index";
	}
	@GetMapping("/admin")
	public String adminHome() {
		
		return "admin";
	}
	
	@PostMapping("/addMovie")
	public String addMovies(@RequestParam("file") MultipartFile file, 
		String movieName, String movieDesc, String ticketPrice) {
			
		movieService.saveMovie(file,movieName, movieDesc, ticketPrice);	
		
		return "redirect:/";
	}
	
	
	  @GetMapping("/bookTicket/{id}") 
	  public String bookTicket(@PathVariable Long id,
			  									Model model) 
	  { 
		  
		Movie movieById = movieService.getMovieById(id);
		/* System.out.println("By Id ---====>>>>>"+movieById.getMovieName()); */
		model.addAttribute("movie", movieById);
	  return "bookTicket";
	  }
	  
	 
	  @GetMapping("/reserveSeat")
	  public String reserveSeat(@ModelAttribute Bookings booking) throws WriterException, IOException {
		  
		  int max=10000;
		  int min =1;
		  Random rand=new Random();
		  int ticketSerial =min+rand.nextInt(max); 
			/* System.out.println("TicketSerial ==> BookinG000000"+ticketSerial); */
		  String ticketSerialNumber = "BookinG000000"+ticketSerial;
		  booking.setBookingSerialNumber(ticketSerialNumber);
		  
		  	System.out.println("User Id --> "+booking.getUserId());
		  
		  String bookingContent = " Name: "+booking.getName() + 
				  "\n Booking serial Number: "+booking.getBookingSerialNumber() +  
				  "\n Booking Id: "+booking.getBookingId() + 
				  "\n Booking Movie Name: "+ booking.getMovieName() + 
				  "\n Booking Seat Number: "+ booking.getSeatNumber() +  
				  "\n Booking Seat Row Number: "+booking.getSeatRow() +  
				  "\n Show time: "+booking.getShowTime() ;
		  
		  //QRCodeGenerator.generateQrCodeImage(bookingContent, 200, 200, filePath);
		  byte[] qrCodeImage = QRCodeGenerator.getQRCodeImage(bookingContent, 200, 200);
		  booking.setBookingQrCode(Base64.getEncoder().encodeToString(qrCodeImage));
		  
		  bService.saveBooking(booking);
		  
		  return "redirect:/";
	  }
	  
	  
	@GetMapping("/showBookings")
	public String showBookings(Model model) {
		
		List<Bookings> allData = bService.getAllbookings();
		model.addAttribute("bookings", allData);
		
		return "showBookings";
	}
	

}
