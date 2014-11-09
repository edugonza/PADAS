package org.processmining.redologs.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;

public class Config {

	private static Config _instance = null;
	
	private static String CONFIG_FILE = "inspector.conf";
	public List<DatabaseConnectionData> connections = new Vector<>();
	
	public static Config getInstance() {
		if (_instance == null) {
			_instance = new Config();
			_instance.load();
		}
		return _instance;
	}
	
	private Config() {
	}
	
	public List<DatabaseConnectionData> getConnections() {
		return connections;
	}
	
	private void load() {
		Gson gson = new Gson();
		
		File configFile = new File(CONFIG_FILE);
		BufferedReader reader;
		try {
			if (!configFile.exists()) {
				save();
			}
			reader = new BufferedReader(new FileReader(configFile));
			_instance = gson.fromJson(reader, this.getClass());
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		Gson gson = new Gson();
		String data = gson.toJson(this);
		
		File configFile = new File(CONFIG_FILE);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
			writer.write(data);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
