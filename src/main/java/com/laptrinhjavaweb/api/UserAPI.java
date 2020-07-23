package com.laptrinhjavaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.config.TokenProvider;
import com.laptrinhjavaweb.dto.AuthToken;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;


@RestController
public class UserAPI{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenProvider jwtTokenUtil;
	
	@GetMapping(value="/api-user/{role_code}")
	public List<UserDTO> getStaffs(@PathVariable("rolecode") String role_code){
		return userService.findByRole(role_code);
	}
	
	@GetMapping(value="/api-user/{buildingid}/staffs")
	public List<UserDTO> getStaffByBuilding(@PathVariable("buildingid") long buildingid){
		return userService.getAssignmentBuilding(buildingid);
	}
	@PostMapping(value="/api-user")
	public UserDTO insertUser(@RequestBody UserDTO userDTO){
		return userService.save(userDTO);
	}
	
	@PostMapping(value="/authentication")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO) throws AuthenticationException{
		final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassWord()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
	}
	
	@PutMapping(value ="/api-user/{userid}")
	public UserDTO updateUser(@PathVariable("userid") Long userid,@RequestBody UserDTO userDTO){ 
		userDTO.setId(userid);
		return userService.save(userDTO);
	}
	
	@DeleteMapping(value ="/api-user")
	public void deleteUser(@RequestBody Long[] ids){
		userService.delete(ids);
	}
}
