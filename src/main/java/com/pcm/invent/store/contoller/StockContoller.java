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
import com.pcm.invent.store.model.Stock;
import com.pcm.invent.store.mongo.MongoStockRepository;

@RestController
@RequestMapping("stock")
public class StockContoller {

	@Autowired
	private MongoStockRepository stockRepo;

	@GetMapping
	public List<Stock> query() {
		return stockRepo.findAll();
	}

	@GetMapping("/{stockId}")
	public Stock get(@PathVariable String stockId) {
		return stockRepo.findOne(stockId);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> post(@RequestBody @Validated Stock Stock) {
		Instant created = Instant.now();
		Stock.setStockId(newId());
		Stock.setCreated(created);
		Stock.setLastModified(created);
		Stock saved = stockRepo.insert(Stock);
		return new ResponseEntity<Stock>(saved, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{stockId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> put(@PathVariable String stockId, @RequestBody @Validated Stock stock) {

		if (!Objects.equals(stockId, stock.getStockId())) {
			return new BadRequest("The request path doesn't match the Id in reqauet body " + stockId)
					.asResponseEntity();
		}
		Stock found = stockRepo.findOne(stockId);
		if (found == null) {
			return new BadRequest("Record with Stock Id provided to update is not present in system")
					.asResponseEntity();
		}
		if(found.getItemCode()!= stock.getItemCode()){
			return new BadRequest("Item Code can not be changed while updating stock")
					.asResponseEntity();
		}
		Instant lastModified = Instant.now();
		stock.setLastModified(lastModified);
		stock.setCreated(found.getCreated());
		Stock saved = stockRepo.save(stock);
		return new ResponseEntity<Stock>(saved, HttpStatus.OK);

	}

}
