package com.pcm.invent.store.model;

import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_NULL)
@TypeAlias("supplier")
public class Supplier {

	@Id
	private String supplierId;

	@NotBlank
	private String supplierName;

	@NotBlank
	private String contactPerson;

	@NotNull
	private Address supplierAddress;

	@NotBlank
	private String mobileNumber;

	private String telephoneNumber;

	@Email
	private String emailAddress;

	@NotNull
	private boolean status;

	private Instant created;

	private Instant lastModified;

	@NotBlank
	private String createdBy;

	@NotBlank
	private String lastModifiedBy;

	public Supplier() {

	}

	public Supplier(String supplierId, String supplierName, String contactPerson, Address supplierAddress,
			String mobileNumber, String telephoneNumber, String emailAddress, boolean status, Instant created,
			Instant lastModified, String createdBy, String lastModifiedBy) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.contactPerson = contactPerson;
		this.supplierAddress = supplierAddress;
		this.mobileNumber = mobileNumber;
		this.telephoneNumber = telephoneNumber;
		this.emailAddress = emailAddress;
		this.status = status;
		this.created = created;
		this.lastModified = lastModified;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Address getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(Address supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
