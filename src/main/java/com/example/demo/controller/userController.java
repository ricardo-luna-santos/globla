package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.userRepository;

@RestController
public class userController {
	@Autowired
	userRepository repUser;
	@GetMapping("/usuarios")
	public ResponseEntity<List<User>> getAllUser(@RequestParam(required=false) String iduser){
		try {
			List<User> user= new ArrayList<User>();
			if(iduser==null)
				repUser.findAll().forEach(user::add);
			else
				repUser.findByIduser(iduser).forEach(user::add);
				if(user.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
			return new ResponseEntity<>(user, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*@GetMapping("/login/{user}")
	public ResponseEntity<User> getAllUser(@PathVariable String iduser, @PathVariable String password){
			
			Optional<User> pass = repUser.findByIduser(iduser);
			if(iduser==null || password==null)
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			else if(repUser.)
				repUser.findByIduser(iduser).forEach(user::add);
				if(user.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
			return new ResponseEntity<>(user, HttpStatus.OK);
	}*/
	@PostMapping("/addusuario")
	 public ResponseEntity<User> createProduct(@RequestBody User user){
		 try {
			 User _user = repUser
					 .save(new User(  			
							 user.getIduser(),
							 user.getPassword(),
							 user.getIdrol()					 
							 ));
			 return new ResponseEntity<>( _user, HttpStatus.CREATED); 
		 }catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	 }
	
	 @DeleteMapping("/delusuario/{id}")
	 public ResponseEntity<HttpStatus> deletePost(@PathVariable Integer id){ 
		 try {
			 repUser.deleteById(id);
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);			 
		 }catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	 
	 @PutMapping("/updateusuario/{id}")
	 public ResponseEntity<User> update (@PathVariable("id") Integer id, @RequestBody User user ){
			Optional<User> userData = repUser.findById(id);
			if (userData.isPresent()) {
				User _user= userData.get();
				_user.setPassword(user.getPassword());
				_user.setIdrol(user.getIdrol());		
	    	 return new ResponseEntity<>( repUser.save(_user), HttpStatus.OK);
			 }else{
		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
		 }

}
