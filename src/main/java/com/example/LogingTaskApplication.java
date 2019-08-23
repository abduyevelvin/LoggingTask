package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Log;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class LogingTaskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LogingTaskApplication.class, args);
	}

	@Override
	public void run(String[] args) throws IOException {

		/*
		 * Log log = null; List<Log> logs = new ArrayList<Log>();
		 * 
		 * // create ObjectMapper instance ObjectMapper mapper = new ObjectMapper();
		 * 
		 * File file = new
		 * File(getClass().getClassLoader().getResource("logfile.txt").getFile());
		 * 
		 * logs = mapper.readValue(file, new TypeReference<List<Log>>(){});
		 * 
		 * for (Log l : logs) { // print customer details System.out.println(l); }
		 */
		
		Map<String, List<Log>> logMap = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		File file = new File(getClass().getClassLoader().getResource("logfile.txt").getFile());
		
		try {
			FileInputStream inputStream = new FileInputStream(file);
			Scanner sc = new Scanner(inputStream, "UTF-8");

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Log log = mapper.readValue(line, Log.class);
				
				List<Log> logs = logMap.get(log.getId());
				
				if (logs == null) {
					logs = new ArrayList<>();
				}
				
				logs.add(log);
				
				logMap.put(log.getId(), logs);
				
				System.out.println(logMap.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for (String key: logMap.keySet()) {
			List<Log> logs = logMap.get(key);
			
			Log log1 = logs.get(0);
			Log log2 = logs.get(1);
			System.out.println(log1.getId() + " - " + log2.getId() + " --- " + (Math.abs(log1.getTimestamp() - log2.getTimestamp())));
		}
	}
}
