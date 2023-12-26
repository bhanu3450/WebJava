package com.app.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
      private static SessionFactory factory;
      // static init block to create singleton SF
      // Configuration --> configure --> build SessionFactory
      
      static {
    	  // build SF
    	  System.out.println("inside static init block");
    	  factory = new Configuration() // empty config created
    			  .configure() // reads hibernate config xml file n populates the config 
    			  .buildSessionFactory(); //  builds instance of SF from the populated(filled up) config 
    	  System.out.println("Sf created");
      }
      
      public static SessionFactory getFactory() {
    	  return factory;
      }
}
