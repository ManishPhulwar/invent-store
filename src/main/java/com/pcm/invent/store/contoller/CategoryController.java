package com.pcm.invent.store.contoller;

import java.time.Instant;
import java.util.List;

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

import com.pcm.invent.store.model.Category;
import com.pcm.invent.store.mongo.MongoCategoryRepository;
import com.pcm.invent.store.service.CounterService;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private MongoCategoryRepository categoryRepo;

	@Autowired
	private CounterService counterService;

	@GetMapping
	public List<Category> query() {
		return categoryRepo.findAll();
	}

	@GetMapping("/{categoryName}")
	public Category get(@PathVariable String categoryName) {
		return categoryRepo.findByName(categoryName);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> post(@RequestBody @Validated Category category) {
		Instant created = Instant.now();
		category.setId(counterService.getNextSquence("categoryId"));
		category.setCreated(created);
		category.setLastModified(created);
		Category saved = categoryRepo.insert(category);
		return new ResponseEntity<Category>(saved, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{categoryId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> put(@PathVariable String categoryId, @RequestBody @Validated Category category) {
		
		Category retrieivedCategory = categoryRepo.findOne(categoryId);
		if(retrieivedCategory!=null){
			
		}
		return new ResponseEntity<Category>(category, HttpStatus.OK);

	}

}
