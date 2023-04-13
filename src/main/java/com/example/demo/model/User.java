package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="c_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	public int id;
	@Column(name="id_user")
	public String iduser;
	@Column(name="password")
	public String password;
	@Column(name="id_rol")
	public String idrol;
	
	public int getId() {
		return id;
	}

	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdrol() {
		return idrol;
	}
	public void setIdrol(String idrol) {
		this.idrol = idrol;
	}
	public User(String iduser, String password, String idrol) {
		super();
		this.iduser = iduser;
		this.password = password;
		this.idrol = idrol;
	}
	public User(String iduser, String password) {
		this.iduser = iduser;
		this.password = password;;
	}
	public User(String iduser) {
		this.iduser = iduser;
	}
	public User(int id) {
		this.id = id;
	}
	public User() {
	}
	
	
}
