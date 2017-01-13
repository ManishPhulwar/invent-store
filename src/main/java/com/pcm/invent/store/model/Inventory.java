package com.pcm.invent.store.model;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_NULL)
@TypeAlias("inventory")
public class Inventory {

	public static int ID_MAX = 8;

	@Id
	@TextIndexed
	private int itemCode;

	@NotNull
	private String name;

	@NotNull
	private double costPrice;

	private int availabe;

	private int reOrderLevel;

	private Instant created;

	private Instant lastModified;

	private String createdBy;

	private String lastModifiedBy;

	private boolean consumable;

	private String categoryId;

	private String subCategoryId;

	public Inventory() {

	}

	@PersistenceConstructor
	public Inventory(int itemCode, String name, double costPrice, int availabe, int reOrderLevel, Instant created,
			Instant lastModified, String createdBy, String lastModifiedBy, boolean consumable, String categoryId,
			String subCategoryId) {
		super();
		this.itemCode = itemCode;
		this.name = name;
		this.costPrice = costPrice;
		this.availabe = availabe;
		this.reOrderLevel = reOrderLevel;
		this.created = created;
		this.lastModified = lastModified;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
		this.consumable = consumable;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
	}

	
	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public int getAvailabe() {
		return availabe;
	}

	public void setAvailabe(int availabe) {
		this.availabe = availabe;
	}

	public int getReOrderLevel() {
		return reOrderLevel;
	}

	public void setReOrderLevel(int reOrderLevel) {
		this.reOrderLevel = reOrderLevel;
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

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public static int getID_MAX() {
		return ID_MAX;
	}

}
