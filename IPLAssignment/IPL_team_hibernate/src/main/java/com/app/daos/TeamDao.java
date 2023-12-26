package com.app.daos;

import java.util.List;

import com.app.pojos.Teams;



public interface TeamDao {
 // add a new method to add new team
	public String addNewTeam(Teams newTeam) ;
	// get all team details by id and abbrevaition
    List<Teams> getAllTeamIdAndAbbrevaitionById();
    
 // add a method to get team details by its id
    Teams getTeamById(Integer id);
    
 // get all team details by max age and wickets taken
    List<Teams> displayTeamsByMaxAgeAndWicketsTaken(int maxAge, int wicketsTaken);
    
  //add a method to update team maxage and batting avg
    String updateMaxAgeAndBattingAvg(String name, int age, double battingAvg);
    
 // add a method to delete team details by its id
 	String deleteTeamDetailsById(Integer teamId);
    
}
