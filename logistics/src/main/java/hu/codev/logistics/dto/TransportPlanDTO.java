package hu.codev.logistics.dto;

import org.hibernate.annotations.ManyToAny;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class TransportPlanDTO {
	
	private long id;
	
	@NotEmpty
	@Min(1)
	private long amount;
	
	@NotEmpty
	@ManyToAny
	private SectionDTO section;
	
	public TransportPlanDTO() {}

	public TransportPlanDTO(long id, @NotEmpty @Min(1) long amount, @NotEmpty SectionDTO section) {
		this.id = id;
		this.amount = amount;
		this.section = section;
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

	public SectionDTO getSection() {
		return section;
	}

	public void setSection(SectionDTO section) {
		this.section = section;
	}
	
}
