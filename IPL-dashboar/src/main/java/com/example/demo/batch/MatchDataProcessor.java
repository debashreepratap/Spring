package com.example.demo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Match;
import com.example.demo.model.MatchInput;

public class MatchDataProcessor implements ItemProcessor<MatchInput,Match>{

	
	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	@Override
	public Match process(MatchInput matchInput) throws Exception {
		Match match = new Match();
		
		match.setMatchId(Long.parseLong(matchInput.getMatchId()));
		match.setId(Long.parseLong(matchInput.getId()));
		match.setInning(matchInput.getInning());
		match.setOver(matchInput.getOver());
		match.setBall(matchInput.getBall());
		match.setBatsman(matchInput.getBatsman());
		match.setNonStriker(matchInput.getNon_striker());
		match.setBowler(matchInput.getBowler());
		match.setBatsmanRuns(matchInput.getBatsman_runs());
		match.setTotalRuns(matchInput.getTotal_runs());
		match.setIsWicket(matchInput.getIs_wicket());
		match.setFielder(matchInput.getFielder());
		match.setBattingTeam(matchInput.getBatting_team());
		match.setBowlingTeam(matchInput.getBowling_team());
		
		
		return match;
	}
}
