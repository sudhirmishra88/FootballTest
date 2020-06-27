package com.coding.football.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.football.model.FootBallStandingResponseModel;
import com.coding.football.service.FootballLeagueService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@MockBean
	private FootballLeagueService service;

	//@Test
	public void getLeaguethenReturnJson() throws Exception {
		FootBallStandingResponseModel model = new FootBallStandingResponseModel();
		Mockito.when(service.getStandings("England", "Championship", "Stoke")).thenReturn(model);
		//assertTrue(1, 1);
	}

}
