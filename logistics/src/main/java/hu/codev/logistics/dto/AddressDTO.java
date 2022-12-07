package hu.codev.logistics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressDTO {

	private long id;
	
	@NotNull
	@NotBlank
	@Size(min = 2, max = 2)
	private String isoCode;
	
	@NotNull
	@NotBlank
	private String city;
	
	@NotNull
	@NotBlank
	private String street;
	
	@NotNull
	@NotBlank
	@Size(min = 4)
	private String postCode;
	
	private String houseNumber;
	
	private double latitude;
	
	private double longitude;
	
	public AddressDTO() {};

	public AddressDTO(long id, @NotEmpty @Size(min = 2, max = 2) String isoCode, @NotBlank String city, @NotBlank String street,
			@NotBlank @Size(min = 4) String postCode, String houseNumber, double latitude, double longitude) {
		super();
		this.id = id;
		this.isoCode = isoCode;
		this.city = city;
		this.street = street;
		this.postCode = postCode;
		this.houseNumber = houseNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
