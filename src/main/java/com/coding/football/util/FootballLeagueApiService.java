package com.coding.football.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coding.football.config.FootballAppConfig;
import com.coding.football.model.Country;
import com.coding.football.model.League;
import com.coding.football.model.LeagueStanding;

@Service
public class FootballLeagueApiService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private FootballAppConfig appConfig;

	public List<Country> getAllCountries() {
		String url = new StringBuilder().append(appConfig.getHostUrl()).toString();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("action", appConfig.getApiCountries()).queryParam("APIkey", appConfig.getApiKey());

		ResponseEntity<List<Country>> countries = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Country>>() {
				});

		return countries.getBody();
	}

	public List<League> getLeageForCountry(String country) {
		String url = new StringBuilder().append(appConfig.getHostUrl()).toString();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("action", appConfig.getApiLeagues()).queryParam("country_id", country)
				.queryParam("APIkey", appConfig.getApiKey());

		ResponseEntity<List<League>> countries = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<League>>() {
				});

		return countries.getBody();
	}

	public List<LeagueStanding> getStandingForCountry(String league) {
		String url = new StringBuilder().append(appConfig.getHostUrl()).toString();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("action", appConfig.getApiStandings()).queryParam("league_id", league)
				.queryParam("APIkey", appConfig.getApiKey());

		ResponseEntity<List<LeagueStanding>> countries = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<LeagueStanding>>() {
				});

		return countries.getBody();
	}
}
