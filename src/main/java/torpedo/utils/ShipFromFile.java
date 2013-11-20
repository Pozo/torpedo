package torpedo.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import torpedo.Ship;
import torpedo.coordinate.Coordinate;


public class ShipFromFile implements ShipRepository {

	private final String filePath; 
	
	public ShipFromFile(String filePath) {
		this.filePath = filePath;
	}
	
	public ArrayList<Ship> getShips() {
		ArrayList<Ship> list = new ArrayList<Ship>();
		try {
			String rawFileCOntent = readFileContent(filePath);
			
			list = parseFileContent(rawFileCOntent);
		} catch(IOException exception) {
			System.err.println(exception.toString());
		}
		return list;
	}
	private ArrayList<Ship> parseFileContent(String rawFileCOntent) {
		ArrayList<Ship> list = new ArrayList<Ship>();
		String[] lines = rawFileCOntent.split("\n");
		
		StringBuilder sb = new StringBuilder();
		String shipNumber = "";
		
		for (String line : lines) {
			
			if(line.length() == 1) {
				if(sb.length()>0) {
					list.addAll(parseShips(shipNumber, sb.toString()));					
				}
			
				shipNumber = line;
				sb.delete(0, sb.length());
			} else {
				sb.append(line);
				sb.append("\n");
			}
			
		}
		return list;
	}
	private ArrayList<Ship> parseShips(String shipNumberRaw, String rawCoordinates) {
		ArrayList<Ship> list = new ArrayList<Ship>();
		
		ArrayList<Coordinate> parsedCoordinates = parseCoordinates(rawCoordinates);
		
		int shipNumber = Integer.valueOf(shipNumberRaw);
		
		for (int i = 0; i < shipNumber; i++) {
			list.add(new Ship(parsedCoordinates));
		}
		return list;
	}
	private ArrayList<Coordinate> parseCoordinates(String rawCoordinates) {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		
		String[] line = rawCoordinates.split("\n");
		
		for (int i = line.length-1; i >= 0; i--) {
			for (int j = 0; j < line[i].length(); j++) {
				char chr = line[i].charAt(j);
				if(chr == 'X') {
					list.add(new Coordinate(j, line.length-i-1));
				}
			}
		}

		return list;
	}
	private String readFileContent(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String fileContent = "";
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append('\n');
	            line = br.readLine();
	        }
	        fileContent = sb.toString();
	    } finally {
	        br.close();
	    }
		return fileContent;
	}
}
