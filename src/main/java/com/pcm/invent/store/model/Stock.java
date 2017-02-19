package com.pcm.invent.store.model;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_NULL)
@TypeAlias("stock")
public class Stock {

	@Id
	@TextIndexed
	private String stockId;

	@NotNull
	@Indexed(unique= true)
	private int itemCode;

	@NotNull
	private float availableQuantity;

	@NotNull
	private float maxLevel;

	@NotNull
	private float minLevel;

	@NotNull
	private float reorderLevel;

	@NotNull
	private double costPrice;

	@NotBlank
	private String rackNumber;

	private String remark;

	private Instant created;

	private Instant lastModified;

	@NotBlank
	private String createdBy;

	@NotBlank
	private String lastModifiedBy;

	public Stock() {

	}

	public Stock(String stockId, int itemCode, float availableQuantity, float maxLevel, float minLevel,
			float reorderLevel, double costPrice, String rackNumber, String remark, Instant created,
			Instant lastModified, String createdBy, String lastModifiedBy) {
		super();
		this.stockId = stockId;
		this.itemCode = itemCode;
		this.availableQuantity = availableQuantity;
		this.maxLevel = maxLevel;
		this.minLevel = minLevel;
		this.reorderLevel = reorderLevel;
		this.costPrice = costPrice;
		this.rackNumber = rackNumber;
		this.remark = remark;
		this.created = created;
		this.lastModified = lastModified;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public float getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(float availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public float getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(float maxLevel) {
		this.maxLevel = maxLevel;
	}

	public float getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(float minLevel) {
		this.minLevel = minLevel;
	}

	public float getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(float reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public String getRackNumber() {
		return rackNumber;
	}

	public void setRackNumber(String rackNumber) {
		this.rackNumber = rackNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
