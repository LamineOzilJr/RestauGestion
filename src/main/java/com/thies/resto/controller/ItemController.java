package com.thies.resto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thies.resto.model.Item;
import com.thies.resto.service.impl.ItemServImpl;

@Controller
@RequestMapping("api/menu/items")
public class ItemController {
	
	private ItemServImpl service;

	  public ItemController(ItemServImpl service) {
	    this.service = service;
	  }
	  
	// New! GET controller methods
	    @GetMapping
	    public ResponseEntity<List<Item>> findAll() {
	        List<Item> items = service.findAll();
	        return ResponseEntity.ok().body(items);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Item> find(@PathVariable("id") Long id) {
	        Optional<Item> item = service.find(id);
	        return ResponseEntity.of(item);
	    }
	    
	    //New!POST definition
	    @PostMapping
	    public ResponseEntity<Item> create(@RequestBody Item item) {
	        Item created = service.create(item);
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(created.getId())
	                .toUri();
	        return ResponseEntity.created(location).body(created);
	    }
	    
	 // New! PUT definition
	    @PutMapping("/{id}")
	    public ResponseEntity<Item> update(
	            @PathVariable("id") Long id,
	            @RequestBody Item updatedItem) {

	        Optional<Item> updated = service.update(id, updatedItem);

	        return updated
	                .map(value -> ResponseEntity.ok().body(value))
	                .orElseGet(() -> {
	                    Item created = service.create(updatedItem);
	                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                            .path("/{id}")
	                            .buildAndExpand(created.getId())
	                            .toUri();
	                    return ResponseEntity.created(location).body(created);
	                });
	    }

	    // DELETE definition 
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Item> delete(@PathVariable("id") Long id) {
	        service.delete(id);
	        return ResponseEntity.noContent().build();
	    }

	

}
