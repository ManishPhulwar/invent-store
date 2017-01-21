package com.pcm.invent.store.model;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_NULL)
@TypeAlias("itemsuppliermap")
public class ItemSupplierMap {

	@Id
	private String id;

	@NotNull
	private int itemCode;

	@NotNull
	private int supplierCode;

	@NotNull
	private double itemCostPrice;

	@NotNull
	private float suppliedQuantity;

	private Instant created;

	private Instant lastModified;

	@NotBlank
	private String createdBy;

	@NotBlank
	private String lastModifiedBy;

	public ItemSupplierMap() {

	}

	public ItemSupplierMap(String id, int itemCode, int supplierCode, double itemCostPrice, float suppliedQuantity,
			Instant created, Instant lastModified, String createdBy, String lastModifiedBy) {
		super();
		this.id = id;
		this.itemCode = itemCode;
		this.supplierCode = supplierCode;
		this.itemCostPrice = itemCostPrice;
		this.suppliedQuantity = suppliedQuantity;
		this.created = created;
		this.lastModified = lastModified;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public int getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(int supplierCode) {
		this.supplierCode = supplierCode;
	}

	public double getItemCostPrice() {
		return itemCostPrice;
	}

	public void setItemCostPrice(double itemCostPrice) {
		this.itemCostPrice = itemCostPrice;
	}

	public float getSuppliedQuantity() {
		return suppliedQuantity;
	}

	public void setSuppliedQuantity(float suppliedQuantity) {
		this.suppliedQuantity = suppliedQuantity;
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

}
