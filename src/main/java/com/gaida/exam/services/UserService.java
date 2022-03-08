package com.gaida.exam.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.gaida.exam.models.LoginUser;
import com.gaida.exam.models.User;
import com.gaida.exam.repositories.UserRepository;



@Service
public class UserService {
	
	//this is building the constuctor with this injection we can do this for eah repo we need
	@Autowired
	private UserRepository userRepo;
	
	public User checkRegister(User newUser, BindingResult results) {
		
		Optional <User> potentialUser = this.userRepo.findByEmail(newUser.getEmail());
		if(potentialUser.isPresent()) {
			results.rejectValue("email", "emailTaken","This email is taken try again");
		}
	
		if(!newUser.getConfirm().equals(newUser.getPassword())) {
			results.rejectValue("password", "noMatch","Password and Confirm do not match");
		}
		
		if(results.hasErrors()) {
			return null;
		}
		else { ///no errors we can create a user to do that we must generate bcrypt infor to save password
			String hashed = BCrypt.hashpw(newUser.getPassword(),BCrypt.gensalt());
			newUser.setPassword(hashed);
			
			this.userRepo.save(newUser);
			return newUser;
		}
	}
	
	public User checkLogin(LoginUser newLoginUser, BindingResult results) {
		User regUser = this.userRepo.findByEmail(newLoginUser.getEmail()).orElse(null);
		if(regUser==null) {
			results.rejectValue("email","noEmail", "No such email found");
			return null;
		}
		 if(!BCrypt.checkpw(newLoginUser.getPassword(), regUser.getPassword())) {
			 results.rejectValue("password","invlPass","Invalid Password");
			 return null;
		 }
		
		return regUser;
		
	}
	
	
	public User findByID(long id) {
	return 	this.userRepo.findById(id).orElse(null);
	}
	
	
	public void deleteAccount(long id) {
		this.userRepo.deleteById(id);
	}
	
	
	
	
}


















