package hu.codev.logistics.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.validation.constraints.Future;

public class MilestoneDTO {

private long id;
	
	@ManyToAny
	private AddressDTO address;
	
	@Future
	private LocalDateTime plannedTime;
	
	public MilestoneDTO() {}

	public MilestoneDTO(long id, AddressDTO address, LocalDateTime plannedTime) {
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}
	
}
