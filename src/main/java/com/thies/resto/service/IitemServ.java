package com.thies.resto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thies.resto.model.Item;

public interface IitemServ {
	
	public void save(Item item);
	public Item update(Item item);
	public List<Item> getAll();
	public void delete(Long id);
}
