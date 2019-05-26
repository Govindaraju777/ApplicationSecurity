/**
 * 
 */
package com.example.springboot.repositories.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Users;

/**
 * @author govindaraju.v
 *
 */
@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
	Users findByUsername(String username);
}
