/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.terminaldo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jakubwawak.database.Database;
import com.jakubwawak.maintanance.Properties;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;

/**
 * Main class for Terminaldo application
 */
@SpringBootApplication
@EnableVaadin({"com.jakubwawak.server.website"})
@Theme(value = "terminaldotheme")
public class TerminaldoApplication {

	public static final String version = "0.0.1";
	public static final String build = "todo04122024REV1";

	public static Properties properties;
	public static Database database;


	/**
	 * Main application method
	 */
	public static void main(String[] args) {
		showHeader();
		properties = new Properties("terminaldo.properties");
		database = new Database();

		if ( properties.fileExists ){
			properties.parsePropertiesFile();
			database.setDatabase_url(properties.getValue("databaseUrl"));
			database.connect();
			SpringApplication.run(TerminaldoApplication.class, args);
		}
		else{
			System.out.println("Properties file not found");
			properties.createPropertiesFile();
			System.out.println("Properties file created");
		}
		
	}

	/**
	 * Function for showing header
	 */
	static void showHeader(){
		String header = " _                      _             _ ____   ___  \n" + //
						"| |_ ___ _ __ _ __ ___ (_)_ __   __ _| |  _ \\ / _ \\ \n" + //
						"| __/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | | | | |\n" + //
						"| ||  __/ |  | | | | | | | | | | (_| | | |_| | |_| |\n" + //
						" \\__\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|____/ \\___/ ";

		System.out.println(header + " version " + version + " build " + build);
	}

}
