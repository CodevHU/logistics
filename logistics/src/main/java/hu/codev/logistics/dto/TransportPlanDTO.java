package hu.codev.logistics.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class TransportPlanDTO {
	
	private long id;
	
	@NotEmpty
	@Min(1)
	private long amount;
	
	@OneToMany(mappedBy = "transportPlan")
	List<SectionDTO> sections = new ArrayList<>();
	
	public TransportPlanDTO() {}

	public TransportPlanDTO(long id, @NotEmpty @Min(1) long amount, @NotEmpty List<SectionDTO> sections) {
		this.id = id;
		this.amount = amount;
		this.sections = sections;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public List<SectionDTO> getSections() {
		return sections;
	}

	public void setSections(List<SectionDTO> sections) {
		this.sections = sections;
	}
	
}
