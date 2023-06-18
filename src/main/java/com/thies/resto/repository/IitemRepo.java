package com.thies.resto.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.thies.resto.model.Item;

@Repository
public interface IitemRepo extends JpaRepository<Item, Long>{
	
}
