package com.pcm.invent.store.model;

import java.time.Instant;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_NULL)
@TypeAlias("category")
public class Category {

	@Id
	private int id;

	@Indexed(unique = true)
	private String name;

	@NotBlank
	private String desription;

	private List<SubCategory> subcategories;

	private Instant created;

	private Instant lastModified;

	@NotBlank
	private String createdBy;

	@NotBlank
	private String lastModifiedBy;

	public Category() {

	}

	public Category(int id, String name, String desription, List<SubCategory> subcategories,
			Instant created, Instant lastModified, String createdBy, String lastModifedBy) {
		super();
		this.id = id;
		this.name = name;
		this.desription = desription;
		this.subcategories = subcategories;
		this.created = created;
		this.lastModified = lastModified;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesription() {
		return desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public List<SubCategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getLastModified() {
		return lastModified;
	}

	public void setLastModified(Instant lastModified) {
		this.lastModified = lastModified;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifedBy() {
		return lastModifiedBy;
	}

	public void setLastModifedBy(String lastModifedBy) {
		this.lastModifiedBy = lastModifedBy;
	}

}
