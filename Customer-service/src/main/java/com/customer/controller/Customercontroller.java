package com.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.model.Login;
import com.customer.model.Signup;
import com.customer.service.CustomerServiceImplementation;
import com.customer.service.LoginService;

@RestController
@RequestMapping("/user")
public class Customercontroller {

	@Autowired
	private LoginService user;
	@Autowired
	private CustomerServiceImplementation service;

	@Autowired
	private RestTemplate restTemplate;
	
	
	private static final Logger log = LoggerFactory.getLogger(Customercontroller.class);

	@PostMapping("/adduser")
	public Signup saveUser(@RequestBody Signup signup) {
		signup.setId(service.getSequenceNumber(Signup.SEQUENCE_NAME));
		log.trace("Add User");
		
		return service.addUser(signup);
	}
    @GetMapping("/allusers")
	public List<Signup> findAllUsers() {
		log.trace("All User");
		return service.getuser();
	}

    @PutMapping("/updateUser")
	public String updateUser(@RequestBody Signup signup) {
		String result = service.Updateuser(signup);
		log.trace("Update User");
		return result;
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteuser(@RequestParam int id) {
		log.trace("Delete User");
		return service.deleteUser(id);
	}

	@PostMapping("/login")
	public String userLogin(@RequestBody Login login) {
		log.trace("Login User");
		return user.userLogin(login);
	}


}
