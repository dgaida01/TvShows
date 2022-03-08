package com.gaida.exam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaida.exam.models.Rating;
import com.gaida.exam.models.TvShow;
import com.gaida.exam.models.User;
import com.gaida.exam.repositories.RatingRepository;
import com.gaida.exam.repositories.TvShowRepository;
import com.gaida.exam.repositories.UserRepository;

@Service
public class ApplicationService {
	
	@Autowired
	RatingRepository rateRepo;
	@Autowired
	TvShowRepository tvRepo;
	@Autowired 
	UserRepository userRepo;

	public User findUserByID(long id) {
	 return	this.userRepo.findById(id).orElse(null);
	}
	
	public List <TvShow> findAllShows(){
		return (List<TvShow>) this.tvRepo.findAll();
	}
	
	public void addShow(TvShow show) {	
		
		this.tvRepo.save(show);
	}
	
	public TvShow findShowById(long showid) {
		return this.tvRepo.findById(showid).orElse(null);
	}
	
	public void deleteShow(TvShow show) {
		this.tvRepo.delete(show);
		
	}
	
	public void addRating(Rating theRating) {
		this.rateRepo.save(theRating);
	}
	
	public List<User>findAllUsers() {
		return (List<User>) this.userRepo.findAll();
	}
	
	
	
	
}
