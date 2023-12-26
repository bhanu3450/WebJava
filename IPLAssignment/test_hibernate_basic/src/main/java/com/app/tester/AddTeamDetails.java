package com.app.tester;
import static com.app.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.*;

import com.app.daos.TeamDaoImpl;
import com.app.pojos.Teams;
public class AddTeamDetails {

	public static void main(String[] args) {
		// get SF from utils
	   try(SessionFactory sf = getFactory();
		  Scanner sc = new Scanner(System.in)){
		// create team dao
		   TeamDaoImpl teamDao = new TeamDaoImpl();
		   System.out.println(" Enter team details : String name, String abbreviation, String owner, int maxAge, double battingAvg, int wicketsTaken");
		   // create team instance
		   Teams team = new Teams(sc.next(), sc.next(), sc.next(), sc.nextInt(), sc.nextDouble(), sc.nextInt());
		 //call dao's method for persistence
		   System.out.println(teamDao.addNewTeam(team));
	}// sf.close() --> Hibernate will auto clean up DBCP
	   catch (Exception e) {
		e.printStackTrace();
	}
     
	}

}
