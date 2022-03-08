package com.gaida.exam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaida.exam.models.User;



@Repository
public interface UserRepository extends CrudRepository<User,Long>{

	Optional <User> findByEmail(String email);

}
