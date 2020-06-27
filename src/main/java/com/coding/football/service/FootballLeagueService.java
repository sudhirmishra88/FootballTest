package com.coding.football.service;

import com.coding.football.model.FootBallStandingResponseModel;

public interface FootballLeagueService {
	public FootBallStandingResponseModel getStandings(String countryName, String leagueName, String teamName);
}
