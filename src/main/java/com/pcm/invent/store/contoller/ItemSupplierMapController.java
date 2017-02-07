package com.pcm.invent.store.contoller;

import static com.pcm.invent.store.Helper.newId;

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
import com.pcm.invent.store.model.ItemSupplierMap;
import com.pcm.invent.store.mongo.MongoItemSupplierMapRepository;

@RestController
@RequestMapping("item-supplier-map")
public class ItemSupplierMapController {

	@Autowired
	private MongoItemSupplierMapRepository itemSupplierMapRepo;

	@GetMapping
	public List<ItemSupplierMap> query() {
		return itemSupplierMapRepo.findAll();
	}

	@GetMapping("/{mappingId}")
	public ItemSupplierMap get(@PathVariable String mappingId) {
		return itemSupplierMapRepo.findOne(mappingId);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> post(@RequestBody @Validated ItemSupplierMap ItemSupplierMap) {
		Instant created = Instant.now();
		ItemSupplierMap.setMappingId(newId());
		ItemSupplierMap.setCreated(created);
		ItemSupplierMap.setLastModified(created);
		ItemSupplierMap saved = itemSupplierMapRepo.insert(ItemSupplierMap);
		return new ResponseEntity<ItemSupplierMap>(saved, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{mappingId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> put(@PathVariable String mappingId,
			@RequestBody @Validated ItemSupplierMap ItemSupplierMap) {

		if (!Objects.equals(mappingId, ItemSupplierMap.getMappingId())) {
			return new BadRequest("The request path doesn't match the Id in reqauet body " + mappingId)
					.asResponseEntity();
		}
		ItemSupplierMap found = itemSupplierMapRepo.findOne(mappingId);
		if (found == null) {
			return new BadRequest("Record with ItemSupplierMap Id provided to update is not present in system")
					.asResponseEntity();
		}
		Instant lastModified = Instant.now();
		ItemSupplierMap.setLastModified(lastModified);
		ItemSupplierMap.setCreated(found.getCreated());
		ItemSupplierMap saved = itemSupplierMapRepo.save(ItemSupplierMap);
		return new ResponseEntity<ItemSupplierMap>(saved, HttpStatus.OK);

	}

}
