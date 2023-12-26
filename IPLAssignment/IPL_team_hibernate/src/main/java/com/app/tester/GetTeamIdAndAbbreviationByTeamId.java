package com.app.tester;
import static com.app.utils.HibernateUtils.getFactory;

import org.hibernate.SessionFactory;

import com.app.daos.TeamDaoImpl;

public class GetTeamIdAndAbbreviationByTeamId {

	public static void main(String[] args) {
		
		try(SessionFactory sf = getFactory()) {
			TeamDaoImpl dao = new TeamDaoImpl();
			System.out.println("Team List: ");
			dao.getAllTeamIdAndAbbrevaitionById().forEach(t -> System.out.println("TeamId : " + t.getTeamId() +" ,abbreviation: " + t.getAbbreviation()));
		}// JVM : sc.close , sf.close=> DBCP is cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
