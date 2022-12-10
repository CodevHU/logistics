package hu.codev.logistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.codev.logistics.config.LogisticsConfigProperties;
import hu.codev.logistics.dto.DelayDTO;
import hu.codev.logistics.model.Milestone;
import hu.codev.logistics.model.Section;
import hu.codev.logistics.model.TransportPlan;
import hu.codev.logistics.repository.MilestoneRepository;
import hu.codev.logistics.repository.SectionRepository;
import hu.codev.logistics.repository.TransportPlanRepository;
import jakarta.transaction.Transactional;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportPlanRepository;

	@Autowired
	MilestoneRepository milestoneRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	LogisticsConfigProperties configProperties;

	@Transactional
	public TransportPlan delay(long id, DelayDTO delay) {

		TransportPlan transportPlan = transportPlanRepository.findPlanWithSections(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		
		Milestone milestone = milestoneRepository.findById(delay.getMilestoneId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (sectionRepository.countByTransportPlanAndFromMilestoneOrToMilestone(transportPlan, milestone) == 0)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		Section section = sectionRepository.findByTransportPlanAndFromMilestoneOrToMilestone(transportPlan, milestone);
		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay.getDelay()));

		if (delay.getMilestoneId() == section.getFromMilestone().getId()) {
			Milestone endMilestone = milestoneRepository.findById(section.getToMilestone().getId()).get();
			endMilestone.setPlannedTime(endMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
		}

		if (delay.getMilestoneId() == section.getToMilestone().getId()) {
			Optional<Section> nextSection = sectionRepository.findByTransportPlanAndNumber(transportPlan,
					section.getNumber() + 1);

			if (nextSection.isPresent()) {
				Milestone nextFromMilestone = milestoneRepository.findById(nextSection.get().getFromMilestone().getId()).get();
				nextFromMilestone.setPlannedTime(nextFromMilestone.getPlannedTime().plusMinutes(delay.getDelay()));
			}
		}

		if (getDeductionPercent(delay.getDelay()) > 0) {
			transportPlan.setAmount(
					(long) Math.round(transportPlan.getAmount() * (1 - (getDeductionPercent(delay.getDelay()) / 100))));
		}

		return transportPlan;
	}

	private Double getDeductionPercent(int delayMinute) {

		if (delayMinute >= 120) {
			return configProperties.getDeduction().getLimits().get(120);
		} else if (delayMinute >= 60) {
			return configProperties.getDeduction().getLimits().get(60);
		} else if (delayMinute >= 30) {
			return configProperties.getDeduction().getLimits().get(30);
		} else {
			return 0.0;
		}

	}

}
