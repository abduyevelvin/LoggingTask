package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.EventEntity;

/**
 * Java interface for saving event to the DB by extending CrudRepository
 * 
 * @author Abduyev Elvin
 * 
 */
@Repository
public interface EventRepository extends CrudRepository<EventEntity, String> {

}
