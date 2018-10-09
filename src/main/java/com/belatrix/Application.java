package com.belatrix;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAutoConfiguration
@EnableRetry
@ComponentScan
@ImportResource("integration-context.xml")
public class Application implements ApplicationContextAware{
	private static ApplicationContext applicationContext; 

	public static void main(String[] args) throws ParseException, InterruptedException  {
		Options options =  new Options();
		options.addOption("input", true, "a location of a file that has a list of URLs");
		options.addOption("output", true, "a location of a folder to write the results");
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;
		
		try { 
			cmd = parser.parse( options, args);
		}catch(UnrecognizedOptionException ex) {
			formatter.printHelp("html-context-extractor", options);
			return;
		}
		
		if(cmd.hasOption("input")) {
			System.setProperty("file.name", cmd.getOptionValue("input"));
		} else {
		    formatter.printHelp("html-context-extractor", options);
		    return;
		}
		
		if(cmd.hasOption("output")) {
			System.setProperty("output.folder.name", cmd.getOptionValue("output"));
		}
		
		SpringApplication.run(Application.class, args);
		
		while(true) {
			Thread.sleep(1000);
			ThreadPoolTaskExecutor myThreadPoolTaskExecutor = applicationContext.getBean("myThreadPoolTaskExecutor", ThreadPoolTaskExecutor.class);
			if(myThreadPoolTaskExecutor.getActiveCount() == 0) {
				System.exit(0);
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
