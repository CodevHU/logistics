package hu.codev.logistics.dto;

import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;

public class SectionDTO {

	private long id;
	
	@ManyToMany
	private MilestoneDTO fromMilestone;
	
	@ManyToMany
	private MilestoneDTO toMilestone;
	
	@Min(0)
	private long number;
	
	public SectionDTO() {}

	public SectionDTO(long id, MilestoneDTO fromMilestone, MilestoneDTO toMilestone, @Min(0) long number) {
		this.id = id;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MilestoneDTO getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(MilestoneDTO fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public MilestoneDTO getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(MilestoneDTO toMilestone) {
		this.toMilestone = toMilestone;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
}
