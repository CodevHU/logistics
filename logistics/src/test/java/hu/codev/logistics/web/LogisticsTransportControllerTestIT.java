package hu.codev.logistics.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.codev.logistics.config.LogisticsConfigProperties;
import hu.codev.logistics.dto.DelayDTO;
import hu.codev.logistics.dto.LoginDTO;
import hu.codev.logistics.dto.TransportPlanDTO;
import hu.codev.logistics.model.Milestone;
import hu.codev.logistics.model.TransportPlan;
import hu.codev.logistics.repository.MilestoneRepository;
import hu.codev.logistics.repository.TransportPlanRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class LogisticsTransportControllerTestIT {

	private static final String BASE_URI = "/api/transportplans";

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MilestoneRepository milestoneRepository;

	@Autowired
	TransportPlanRepository transportPlanRepository;

	@Autowired
	LogisticsConfigProperties configProperties;

	private String transportUserToken;
	private String addressUserToken;

	@BeforeEach
	public void getToken() {

		this.transportUserToken = webTestClient.post().uri("/api/login/")
				.bodyValue(new LoginDTO("transportUser", "password")).exchange().expectStatus().isOk()
				.expectBody(String.class).returnResult().getResponseBody();

		this.addressUserToken = webTestClient.post().uri("/api/login/")
				.bodyValue(new LoginDTO("addressUser", "password")).exchange().expectStatus().isOk()
				.expectBody(String.class).returnResult().getResponseBody();
	}

	@Test
	void testWhenPostDelayRequestsWithoutLogin() throws Exception {
		assertThat(webTestClient.post().uri(BASE_URI + "/1/delay").exchange().expectStatus()
				.isEqualTo(HttpStatus.FORBIDDEN));

	}

	@Test
	void testWhenPostDelayRequestsWithLogin() throws Exception {
		assertThat(webTestClient.post().uri(BASE_URI + "/1/delay").headers(h -> h.setBearerAuth(transportUserToken))
				.bodyValue(new DelayDTO(2, 10)).exchange().expectStatus().isOk());
	}

	@Test
	void testWhenPostDelayRequestsWithLoginWithAddressRoleUser() throws Exception {
		assertThat(webTestClient.post().uri(BASE_URI + "/1/delay").headers(h -> h.setBearerAuth(addressUserToken))
				.bodyValue(new DelayDTO(2, 10)).exchange().expectStatus().isEqualTo(HttpStatus.FORBIDDEN));
	}

	@Test
	void testThatDelayIsAddedToThePlannedTime() throws Exception {

		Milestone beforeMilestone = milestoneRepository.findById(2L).get();

		webTestClient.post().uri(BASE_URI + "/1/delay").headers(h -> h.setBearerAuth(transportUserToken))
				.bodyValue(new DelayDTO(2, 10)).exchange();

		Milestone afterMilestone = milestoneRepository.findById(2L).get();

		assertThat(beforeMilestone.getPlannedTime().plusMinutes(10)).isEqualTo(afterMilestone.getPlannedTime());

	}

	@Test
	void testThatDelayIsAddedToTheNextStartMilestonePlannedTime() throws Exception {

		Milestone beforeNextMilestone = milestoneRepository.findById(3L).get();

		webTestClient.post().uri(BASE_URI + "/1/delay").headers(h -> h.setBearerAuth(transportUserToken))
				.bodyValue(new DelayDTO(2, 10)).exchange();

		Milestone afterNextMilestone = milestoneRepository.findById(3L).get();

		assertThat(beforeNextMilestone.getPlannedTime().plusMinutes(10)).isEqualTo(afterNextMilestone.getPlannedTime());

	}

	@Test
	void testThatDelayIsAddedToTheEndMilestonePlannedTimeIfTheGetMilestoneIsStarMilesone() throws Exception {

		Milestone beforeMilestone = milestoneRepository.findById(2L).get();

		webTestClient.post().uri(BASE_URI + "/1/delay").headers(h -> h.setBearerAuth(transportUserToken))
				.bodyValue(new DelayDTO(1, 10)).exchange();

		Milestone afterMilestone = milestoneRepository.findById(2L).get();

		assertThat(beforeMilestone.getPlannedTime().plusMinutes(10)).isEqualTo(afterMilestone.getPlannedTime());

	}

	@Test
	void testThatThePenaltyIsDeducted() throws Exception {

		TransportPlan before = transportPlanRepository.findById(1L).get();

		Double percent = configProperties.getDeduction().getLimits().get(30);
		Long newAmount = Math.round(before.getAmount() * (1 - (percent / 100)));

		webTestClient.post().uri(BASE_URI + "/1/delay").headers(h -> h.setBearerAuth(transportUserToken))
				.bodyValue(new DelayDTO(2, 35)).exchange();

		TransportPlan after = transportPlanRepository.findById(1L).get();

		assertThat(after.getAmount()).isEqualByComparingTo(newAmount);

	}

}
