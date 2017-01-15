package com.pcm.invent.store.model;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
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

	@NotBlank
	private String name;

	@NotBlank
	private String categoryId;

	@NotBlank
	private String subCategoryId;

	@NotBlank
	private String unitId;

	@NotNull
	private boolean uesd;

	@NotNull
	private boolean consumable;

	@NotBlank
	private String supplierId;

	private Instant created;

	private Instant lastModified;

	@NotBlank
	private String createdBy;

	@NotBlank
	private String lastModifiedBy;

	public Inventory() {

	}

	@PersistenceConstructor
	public Inventory(int itemCode, String name, String categoryId, String subCategoryId, String unitId, boolean uesd,
			boolean consumable, String supplierId, Instant created, Instant lastModified, String createdBy,
			String lastModifiedBy) {
		super();
		this.itemCode = itemCode;
		this.name = name;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.unitId = unitId;
		this.uesd = uesd;
		this.consumable = consumable;
		this.supplierId = supplierId;
		this.created = created;
		this.lastModified = lastModified;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
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

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public boolean isUesd() {
		return uesd;
	}

	public void setUesd(boolean uesd) {
		this.uesd = uesd;
	}

	public boolean isConsumable() {
		return consumable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public static int getID_MAX() {
		return ID_MAX;
	}

}
