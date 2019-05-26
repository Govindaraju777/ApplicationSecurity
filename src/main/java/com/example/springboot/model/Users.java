/**
 * 
 */
package com.example.springboot.model;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author govindaraju.v
 *
 */
public class Users {
	@Id
	public ObjectId _id;

	public String username;
	public String password;
	@DBRef
	private Set<String> roles;

	public Users() {
	}

	public Users(ObjectId _id, String username, String password, Set<String> roles) {
		this._id = _id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String get_id() {
		return this._id.toHexString();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
