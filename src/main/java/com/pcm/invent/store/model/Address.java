package com.pcm.invent.store.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_NULL)
public class Address {

	@NotNull
	private String plotNumber;

	@NotNull
	private String adressLine;

	@NotNull
	private String landmark;

	@NotNull
	private String city;

	@NotNull
	private String state;

	@NotNull
	private String country;

	@NotNull
	private String postalCode;

	public Address() {

	}

	public Address(String plotNumber, String adressLine, String landmark, String city, String state, String country,
			String postalCode) {
		super();
		this.plotNumber = plotNumber;
		this.adressLine = adressLine;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
	}

	public String getPlotNumber() {
		return plotNumber;
	}

	public void setPlotNumber(String plotNumber) {
		this.plotNumber = plotNumber;
	}

	public String getAdressLine() {
		return adressLine;
	}

	public void setAdressLine(String adressLine) {
		this.adressLine = adressLine;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
