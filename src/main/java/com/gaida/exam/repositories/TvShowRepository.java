package com.gaida.exam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaida.exam.models.TvShow;

@Repository
public interface TvShowRepository extends CrudRepository<TvShow, Long> {

}
