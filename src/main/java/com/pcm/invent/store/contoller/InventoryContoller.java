package com.pcm.invent.store.contoller;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcm.invent.store.error.BadRequest;
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

	@GetMapping("/{inventoryName}")
	public Inventory get(@PathVariable String inventoryName) {
		return inventoryRepo.findByName(inventoryName);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> post(@RequestBody @Validated Inventory inventory) {
		Instant created = Instant.now();
		inventory.setItemCode(counterService.getNextSquence("itemCode"));
		inventory.setCreated(created);
		inventory.setLastModified(created);
		Inventory saved = inventoryRepo.insert(inventory);
		return new ResponseEntity<Inventory>(saved, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{itemCode}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> put(@PathVariable String itemCode, @RequestBody @Validated Inventory inventory) {

		if (!Objects.equals(itemCode, inventory.getItemCode())) {
			return new BadRequest("The request path doesn't match the Id in reqauet body " + itemCode)
					.asResponseEntity();
		}
		Inventory found = inventoryRepo.findByItemCode(itemCode);
		if (found == null) {
			return new BadRequest("Record with Inventory Id provided to update is not present in system")
					.asResponseEntity();
		}
		Instant lastModified = Instant.now();
		inventory.setLastModified(lastModified);
		inventory.setCreated(found.getCreated());
		Inventory saved = inventoryRepo.save(inventory);
		return new ResponseEntity<Inventory>(saved, HttpStatus.OK);

	}

}
