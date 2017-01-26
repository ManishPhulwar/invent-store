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
import com.pcm.invent.store.model.SubCategory;
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
		for (SubCategory s : category.getSubcategories()) {
			s.setId(counterService.getNextSquence("subCategoryId"));
		}
		Instant created = Instant.now();
		category.setId(counterService.getNextSquence("categoryId"));
		category.setCreated(created);
		category.setLastModified(created);
		Category saved = categoryRepo.insert(category);
		return new ResponseEntity<Category>(saved, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{categoryId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> put(@PathVariable int categoryId, @RequestBody @Validated Category category) {
		if (categoryId != category.getId()) {
			return new ResponseEntity<String>("Invalid request parameters", HttpStatus.BAD_REQUEST);
		}
		Category retrieivedCategory = categoryRepo.findById(categoryId);
		if (retrieivedCategory == null) {
			return new ResponseEntity<String>("Invalid request: No category found with ID: " + categoryId,
					HttpStatus.NOT_FOUND);
		}
		Instant LastModified = Instant.now();
		retrieivedCategory.setLastModified(LastModified);
		retrieivedCategory.setDesription(category.getDesription());
		retrieivedCategory.setLastModifedBy(category.getLastModifedBy());
		retrieivedCategory.setName(category.getName());
		retrieivedCategory.setSubcategories(category.getSubcategories());
		Category saved = categoryRepo.save(retrieivedCategory);
		return new ResponseEntity<Category>(saved, HttpStatus.OK);

	}

	@GetMapping("/{categoryId}/subcategory")
	public List<SubCategory> querySubCategory(@PathVariable int categoryId) {
		return categoryRepo.findById(categoryId).getSubcategories();
	}

	@GetMapping("/{categoryId}/subcategory/{subcategoryId}")
	public ResponseEntity<?> getSubCategory(@PathVariable int categoryId, @PathVariable int subcategoryId) {

		for (SubCategory s : categoryRepo.findById(categoryId).getSubcategories()) {
			if (s.getId() == subcategoryId) {
				return new ResponseEntity<SubCategory>(s, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Invalid request: No Sub category found with ID: " + subcategoryId,
				HttpStatus.NOT_FOUND);
	}
	@PostMapping(path="/{categoryId}/subcategory",consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> postSubcategory(@PathVariable int categoryId, @RequestBody @Validated SubCategory subCategory) {
		
		Category retrieivedCategory = categoryRepo.findById(categoryId);
		if (retrieivedCategory == null) {
			return new ResponseEntity<String>("Invalid request: No category found with ID: " + categoryId,
					HttpStatus.NOT_FOUND);
		}
		for (SubCategory s : retrieivedCategory.getSubcategories()) {
			if(s.getName().equals(subCategory.getName())){
				return new ResponseEntity<String>("Invalid request: Subcategory: "+s.toString()+" :: already prensent under category Id :" + categoryId,
						HttpStatus.NOT_FOUND);
			}
		}
		Instant lastModified = Instant.now();
		subCategory.setCreated(lastModified);
		subCategory.setLastModified(lastModified);
		retrieivedCategory.setLastModified(lastModified);
		retrieivedCategory.setLastModifedBy(subCategory.getLastModifiedBy());
		retrieivedCategory.getSubcategories().add(subCategory);
		Category saved = categoryRepo.insert(retrieivedCategory);
		return new ResponseEntity<Category>(saved, HttpStatus.CREATED);

	}
	

}
