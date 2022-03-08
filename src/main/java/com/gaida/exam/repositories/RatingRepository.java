package com.gaida.exam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaida.exam.models.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {

}
