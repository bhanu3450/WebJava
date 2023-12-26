package com.app.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.app.daos.TeamDaoImpl;

import static com.app.utils.HibernateUtils.getFactory;
public class updateMaxAgeAndBattingAvg {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			TeamDaoImpl dao = new TeamDaoImpl();
			System.out.println("Enter maxAge & batting Avg");
			System.out.println(
					dao.updateMaxAgeAndBattingAvg(sc.next(),sc.nextInt(),sc.nextDouble()));
		} // JVM : sc.close , sf.close=> DBCP is cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
