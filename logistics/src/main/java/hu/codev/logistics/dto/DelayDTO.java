package hu.codev.logistics.dto;

import jakarta.validation.constraints.NotBlank;

public class DelayDTO {

	@NotBlank
	private long milestoneId;

	@NotBlank
	private int delay;

	public DelayDTO() {
	}

	public DelayDTO(long milestoneId, int delay) {
		this.milestoneId = milestoneId;
		this.delay = delay;
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
