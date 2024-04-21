package com.work.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Table(name = "roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@NotEmpty
	@Column(nullable = false)	
	private String name;
	@ManyToMany(mappedBy = "roles")
	List<User> users;
	
	
	
}
