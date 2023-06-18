package com.thies.resto.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.stereotype.Service;

import com.thies.resto.model.Item;
import com.thies.resto.service.IitemServ;

@Service
@EnableMapRepositories
public class ItemServImpl {
	private ItemServImpl repoIitemServ;
	
	 public ItemServImpl(IitemServ repoIitemServ) {
		    this.repoIitemServ = repoIitemServ;
		    
		    this.repoIitemServ.saveAll(defaultItems());
		  }
		  
		//  New! List of items 
		  private static List<Item> defaultItems() {
		    return List.of(
		      new Item(1L, "Burger", 599L, "Tasty", "https://cdn.auth0.com/blog/whatabyte/burger-sm.png"),
		      new Item(2L, "Pizza", 299L, "Cheesy", "https://cdn.auth0.com/blog/whatabyte/pizza-sm.png"),
		      new Item(3L, "Tea", 199L, "Informative", "https://cdn.auth0.com/blog/whatabyte/tea-sm.png")
		    );
		  }
		  
		  public List<Item> findAll() {
		        List<Item> list = new ArrayList<>();
		        Iterable<Item> items = repoIitemServ.findAll();
		        items.forEach(list::add);
		        return list;
		    }

		    public Optional<Item> find(Long id) {
		        return repoIitemServ.findById(id);
		    }

		    public Item create(Item item) {
		        // To ensure the item ID remains unique,
		        // use the current timestamp.
		        Item copy = new Item(
		                new Date().getTime(),
		                item.getName(),
		                item.getPrice(),
		                item.getDescription(),
		                item.getImage()
		        );
		        return repository.save(copy);
		    }

		    public Optional<Item> update( Long id, Item newItem) {
		        // Only update an item if it can be found first.
		        return repoIitemServ.findById(id)
		                .map(oldItem -> {
		                   Item updated = oldItem.updateWith(newItem);
		                   return repository.save(updated);
		                });
		    }

		    public void delete(Long id) {
		    	repoIitemServ.deleteById(id);
		    }
}
