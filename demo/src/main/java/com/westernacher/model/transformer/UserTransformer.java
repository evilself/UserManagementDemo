package com.westernacher.model.transformer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

import com.westernacher.model.domain.User;

@Named
public class UserTransformer implements BaseTransformer<User, com.westernacher.model.rest.User> {
	
	@Value("${demo.model.rest.date.pattern}") 
	private String restDatePattern;
	
	@Value("${demo.model.domain.date.pattern}") 
	private String domainDatePattern;

	@Override
	public User transformToDomain(com.westernacher.model.rest.User restModel) {
		User domainUser = new User();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(domainDatePattern);
		LocalDate date = LocalDate.parse(restModel.getDateOfBirth(), formatter);
		domainUser.setDateOfBirth(date);
		
		domainUser.setEmail(restModel.getEmail());
		domainUser.setFirstName(restModel.getFirstName());
		domainUser.setLastName(restModel.getLastName());
		
		return domainUser;
	}

	@Override
	public com.westernacher.model.rest.User transformToRest(User domainModel) {
		com.westernacher.model.rest.User restUser = new com.westernacher.model.rest.User();
		restUser.setId(domainModel.getId());
		restUser.setFirstName(domainModel.getFirstName());
		restUser.setLastName(domainModel.getLastName());
		restUser.setEmail(domainModel.getEmail());		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(restDatePattern);
		String formattedString = domainModel.getDateOfBirth().format(formatter);
		
		restUser.setDateOfBirth(formattedString);
		return restUser;
	}
}