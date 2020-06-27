package com.coding.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.football.model.FootBallStandingResponseModel;
import com.coding.football.service.FootballLeagueService;

@RestController
@RequestMapping("/football")
public class FootballLeagueController {

	@Autowired
	FootballLeagueService service;

	@RequestMapping(method = RequestMethod.GET, path = "/getStandings", produces = "application/JSON")
	public FootBallStandingResponseModel getStandingDetails(@RequestParam String countryName,
			@RequestParam String leagueName, @RequestParam String teamName) {
		FootBallStandingResponseModel response = new FootBallStandingResponseModel();
		response = service.getStandings(countryName, leagueName, teamName);

		return response;
	}

}
