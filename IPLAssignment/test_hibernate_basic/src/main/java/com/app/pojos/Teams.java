package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="teams")
public class Teams {
	// Reco : Make ID property explicitly Serializable (All Wrapper classes are Serializable)
	@Id // primary key
	// to specify automatic id generation using auto increment strategy
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	@Column(name = "team_id")
  private Integer teamId;
	@Column(length = 100, name = "name", unique = true)
  private String name;
	@Column(length = 10, name = "abbreviation", unique = true)
  private String abbreviation;
	@Column(length = 20, name = "owner")
  private String owner;
	@Column(name = "max_age", nullable = false)
  private int maxAge;
	@Column(name="batting_avg")
  private double battingAvg;
	@Column(name="wickets_taken")
  private int wicketsTaken;

public Teams(String name, String abbreviation, String owner, int maxAge, double battingAvg, int wicketsTaken) {
	super();
	this.name = name;
	this.abbreviation = abbreviation;
	this.owner = owner;
	this.maxAge = maxAge;
	this.battingAvg = battingAvg;
	this.wicketsTaken = wicketsTaken;
}

public Integer getTeamId() {
	return teamId;
}

public void setTeamId(Integer teamId) {
	this.teamId = teamId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAbbreviation() {
	return abbreviation;
}

public void setAbbreviation(String abbreviation) {
	this.abbreviation = abbreviation;
}

public String getOwner() {
	return owner;
}

public void setOwner(String owner) {
	this.owner = owner;
}

public int getMaxAge() {
	return maxAge;
}

public void setMaxAge(int maxAge) {
	this.maxAge = maxAge;
}

public double getBattingAvg() {
	return battingAvg;
}

public void setBattingAvg(double battingAvg) {
	this.battingAvg = battingAvg;
}

public int getWicketsTaken() {
	return wicketsTaken;
}

public void setWicketsTaken(int wicketsTaken) {
	this.wicketsTaken = wicketsTaken;
}

@Override
public String toString() {
	return "Teams [teamId=" + teamId + ", name=" + name + ", abbreviation=" + abbreviation + ", owner=" + owner
			+ ", maxAge=" + maxAge + ", battingAvg=" + battingAvg + ", wicketsTaken=" + wicketsTaken + "]";
}
  	
}
