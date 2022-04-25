package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Match {
	
	@Id
	private long matchId;
	private long id;
	private String inning;
	private String over;
	private String ball;
	private String batsman;
	private String nonStriker;
	private String bowler;
	private String batsmanRuns;
	private String totalRuns;
	private String isWicket;
	private String fielder;
	private String battingTeam;
	private String bowlingTeam;
	
	
	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInning() {
		return inning;
	}
	public void setInning(String inning) {
		this.inning = inning;
	}
	public String getOver() {
		return over;
	}
	public void setOver(String over) {
		this.over = over;
	}
	public String getBall() {
		return ball;
	}
	public void setBall(String ball) {
		this.ball = ball;
	}
	public String getBatsman() {
		return batsman;
	}
	public void setBatsman(String batsman) {
		this.batsman = batsman;
	}
	public String getNonStriker() {
		return nonStriker;
	}
	public void setNonStriker(String nonStriker) {
		this.nonStriker = nonStriker;
	}
	public String getBowler() {
		return bowler;
	}
	public void setBowler(String bowler) {
		this.bowler = bowler;
	}
	public String getBatsmanRuns() {
		return batsmanRuns;
	}
	public void setBatsmanRuns(String batsmanRuns) {
		this.batsmanRuns = batsmanRuns;
	}
	public String getTotalRuns() {
		return totalRuns;
	}
	public void setTotalRuns(String totalRuns) {
		this.totalRuns = totalRuns;
	}
	public String getIsWicket() {
		return isWicket;
	}
	public void setIsWicket(String isWicket) {
		this.isWicket = isWicket;
	}
	public String getFielder() {
		return fielder;
	}
	public void setFielder(String fielder) {
		this.fielder = fielder;
	}
	public String getBattingTeam() {
		return battingTeam;
	}
	public void setBattingTeam(String battingTeam) {
		this.battingTeam = battingTeam;
	}
	public String getBowlingTeam() {
		return bowlingTeam;
	}
	public void setBowlingTeam(String bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}
	
	

}
