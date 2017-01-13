package com.pcm.invent.store.model;

import java.time.Instant;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_NULL)
@TypeAlias("inventory_category")
public class InventoryCategory {

	private final String id;

	private final String name;

	private final String desription;

	private final Instant created;

	private final Instant lastModified;

	private final String createdBy;

	public InventoryCategory(String id, String name, String desription, Instant created, Instant lastModified,
			String createdBy) {
		super();
		this.id = id;
		this.name = name;
		this.desription = desription;
		this.created = created;
		this.lastModified = lastModified;
		this.createdBy = createdBy;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesription() {
		return desription;
	}

	public Instant getCreated() {
		return created;
	}

	public Instant getLastModified() {
		return lastModified;
	}

	public String getCreatedBy() {
		return createdBy;
	}

}
