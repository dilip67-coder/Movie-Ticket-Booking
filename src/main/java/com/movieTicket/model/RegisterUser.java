package com.movieTicket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class RegisterUser {

	@Id
	@GeneratedValue
	private long id;
	private String registerName;
	private String registerPassword;
	private String registerEmail;
	private long registerPhNo;
}
