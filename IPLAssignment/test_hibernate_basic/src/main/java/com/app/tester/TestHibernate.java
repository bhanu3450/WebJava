package com.app.tester;
import org.hibernate.*;
import static com.app.utils.HibernateUtils.getFactory;

public class TestHibernate {

	public static void main(String[] args) {
		// get SF from utils
		try(SessionFactory sf = getFactory()) {
			System.out.println("hib up n running !!!");
		}// sf.close() --> HIbernate will auto clean up DBCP 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
