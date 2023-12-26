package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.app.daos.TeamDaoImpl;

public class displayTeamsByMaxAgeAndWicketsTaken {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();
				Scanner sc = new Scanner(System.in)) {
			 // create dao instance
			TeamDaoImpl dao = new TeamDaoImpl();
			System.out.println("Enter age and wickets taken");
			System.out.println(dao.displayTeamsByMaxAgeAndWicketsTaken(sc.nextInt(), sc.nextInt())); // auto boxing
		}// JVM : sc.close , sf.close=> DBCP is cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
