package com.work.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
@NotEmpty
@Column(nullable = false)	
private String firstname;
private String lastname;
@NotEmpty
@Email(message = "{erros.invalid_email}")
@Column(nullable = false,unique = true )
private String email ;
@NotEmpty
private String password;
@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
@JoinTable(
		 name = "user_role",
		 joinColumns = @JoinColumn(name= "USER_ID",referencedColumnName ="ID" ),
		 inverseJoinColumns = @JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")
		)
private List<Roles> roles;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public List<Roles> getRoles() {
	return roles;
}
public void setRoles(List<Roles> roles) {
	this.roles = roles;
}
public User(User user) {
	super();
	this.id = user.getId();
	this.firstname = user.getFirstname();
	this.lastname = user.getLastname();
	this.email = user.getEmail();
	this.password = user.getPassword();
	this.roles = user.getRoles();
}



}
