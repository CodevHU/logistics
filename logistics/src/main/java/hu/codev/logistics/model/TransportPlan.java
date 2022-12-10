package hu.codev.logistics.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;

@Entity
public class TransportPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Positive
	private long amount;
	
	@OneToMany(mappedBy = "transportPlan")
	List<Section> sections = new ArrayList<>();

	public TransportPlan() {}
	
	public TransportPlan(long id, @Positive long amount, List<Section> sections) {
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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> section) {
		this.sections = section;
	}
	
	
	
}
