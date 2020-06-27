package com.coding.football.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.coding.football.model.Country;
import com.coding.football.model.FootBallStandingResponseModel;
import com.coding.football.model.League;
import com.coding.football.model.LeagueStanding;
import com.coding.football.util.FootballLeagueApiService;

@Service
public class FootballLeagueServiceImpl implements FootballLeagueService {

	@Autowired
	private FootballLeagueApiService footballApiService;

	@Override
	public FootBallStandingResponseModel getStandings(String countryName, String leagueName, String teamName) {
		FootBallStandingResponseModel response = new FootBallStandingResponseModel();
		Country c = findCountryByName(countryName);
		if (c == null) {
			throw new RuntimeException("Country not found");
		}
		response.setCountryName(countryName);
		response.setCountryId(c.getCountryId());

		League league = findLeagueByCountryId(response.getCountryId());

		if (league == null) {
			throw new RuntimeException("No leagues");
		}

		response.setLeagueId(league.getLeagueId());
		response.setLeagueName(leagueName);

		List<LeagueStanding> standings = findStandingForTeam(response.getLeagueId());

		standings = standings.stream().filter(
				standing -> standing.getCountryName().equals(countryName) && standing.getTeamName().equals(teamName))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(standings)) {
			LeagueStanding standing = standings.get(0);

			response.setTeamId(standing.getTeamId());
			response.setTeamName(standing.getTeamName());
			response.setStandingPosition(standing.getOverallLeaguePosition());
		} else {
			throw new RuntimeException("No country/team in this League");
		}

		return response;
	}

	public Country findCountryByName(String name) {
		List<Country> allCountries = footballApiService.getAllCountries();

		Optional<Country> countryPrd = allCountries.stream().filter(country -> name.equals(country.getCountryName()))
				.findFirst();
		return countryPrd.isPresent() ? countryPrd.get() : null;
	}

	public League findLeagueByCountryId(String countryId) {
		List<League> allLeagues = footballApiService.getLeageForCountry(countryId);

		Optional<League> leaguePrd = allLeagues.stream().filter(league -> countryId.equals(league.getCountryId()))
				.findFirst();
		return leaguePrd.isPresent() ? leaguePrd.get() : null;
	}

	public List<LeagueStanding> findStandingForTeam(String leagueId) {
		return footballApiService.getStandingForCountry(leagueId);
	}
}
