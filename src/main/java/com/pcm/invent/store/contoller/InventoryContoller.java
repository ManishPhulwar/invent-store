package com.pcm.invent.store.contoller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pcm.invent.store.model.Inventory;
import com.pcm.invent.store.mongo.MongoInventoryRepository;
import com.pcm.invent.store.service.CounterService;

@RestController
@RequestMapping("inventory")
public class InventoryContoller {

	@Autowired
	private MongoInventoryRepository inventoryRepo;

	@Autowired
	private CounterService counterService;

	@GetMapping
	public List<Inventory> query() {
		return inventoryRepo.findAll();
	}

	@GetMapping("/{inventoryId}")
	public Inventory get(@PathVariable String inventoryId) {
		return inventoryRepo.findOne(inventoryId);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> post(@RequestBody @Validated Inventory inventory, BindingResult bindingResult,
			UriComponentsBuilder uri) {
		Instant created = Instant.now();
		inventory.setItemCode(counterService.getNextSquence("inventoryId"));
		inventory.setCreated(created);
		inventory.setLastModified(created);
		Inventory saved = inventoryRepo.insert(inventory);
		return new ResponseEntity<Inventory>(saved, HttpStatus.CREATED);

	}

}
