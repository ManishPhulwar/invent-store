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
import com.pcm.invent.store.model.Supplier;
import com.pcm.invent.store.mongo.MongoSupplierRepository;

@RestController
@RequestMapping("supplier")
public class SupplierController {

	@Autowired
	private MongoSupplierRepository supplierRepo;

	@GetMapping
	public List<Supplier> query() {
		return supplierRepo.findAll();
	}

	@GetMapping("/{SupplierId}")
	public Supplier get(@PathVariable String SupplierId) {
		return supplierRepo.findOne(SupplierId);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> post(@RequestBody @Validated Supplier Supplier) {
		Instant created = Instant.now();
		Supplier.setSupplierId(newId());
		Supplier.setCreated(created);
		Supplier.setLastModified(created);
		Supplier saved = supplierRepo.insert(Supplier);
		return new ResponseEntity<Supplier>(saved, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{SupplierId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> put(@PathVariable String SupplierId, @RequestBody @Validated Supplier Supplier) {

		if (!Objects.equals(SupplierId, Supplier.getSupplierId())) {
			return new BadRequest("The request path doesn't match the Id in reqauet body " + SupplierId)
					.asResponseEntity();
		}
		Supplier found = supplierRepo.findOne(SupplierId);
		if (found == null) {
			return new BadRequest("Record with Supplier Id provided to update is not present in system")
					.asResponseEntity();
		}
		Instant lastModified = Instant.now();
		Supplier.setLastModified(lastModified);
		Supplier.setCreated(found.getCreated());
		Supplier saved = supplierRepo.save(Supplier);
		return new ResponseEntity<Supplier>(saved, HttpStatus.OK);

	}

}
