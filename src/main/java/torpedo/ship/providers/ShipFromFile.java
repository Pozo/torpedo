package torpedo.ship.providers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import torpedo.coordinate.Coordinate;
import torpedo.ship.Ship;

/**
 * ShipFromFile.
 * @author Zoltan_Polgar
 *
 */
public class ShipFromFile implements ShipProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipFromFile.class);
    private final String filePath;

    /**
     * ShipFromFile.
     * @param filePath file path
     */
    public ShipFromFile(String filePath) {
        this.filePath = filePath;
    }
    /**
     * getShips.
     * @return list list
     */
    public List<Ship> getShips() {
        List<Ship> list = new ArrayList<Ship>();
        try {
            String rawFileCOntent = readFileContent(filePath);

            list = parseFileContent(rawFileCOntent);
        } catch (IOException exception) {
            LOGGER.error(exception.toString());
        }
        return list;
    }

    private List<Ship> parseFileContent(String rawFileCOntent) {
        List<Ship> list = new ArrayList<Ship>();
        String[] lines = rawFileCOntent.split("\n");

        StringBuilder sb = new StringBuilder();
        String shipNumber = "";

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() == 1) {
                if (sb.length() > 0) {
                    list.addAll(parseShips(shipNumber, sb.toString()));
                }
                shipNumber = lines[i];
                sb.delete(0, sb.length());
            } else {
                sb.append(lines[i]);
                sb.append("\n");

                if (i == lines.length - 1) {
                    list.addAll(parseShips(shipNumber, sb.toString()));
                }
            }
        }
        return list;
    }

    private List<Ship> parseShips(String shipNumberRaw, String rawCoordinates) {
        List<Ship> list = new ArrayList<Ship>();

        //ArrayList<Coordinate> parsedCoordinates = parseCoordinates(rawCoordinates);

        int shipNumber = Integer.valueOf(shipNumberRaw);

        for (int i = 0; i < shipNumber; i++) {
            list.add(new Ship(parseCoordinates(rawCoordinates)));
        }
        return list;
    }

    private List<Coordinate> parseCoordinates(String rawCoordinates) {
        List<Coordinate> list = new ArrayList<Coordinate>();

        String[] line = rawCoordinates.split("\n");

        for (int i = line.length - 1; i >= 0; i--) {
            for (int j = 0; j < line[i].length(); j++) {
                char chr = line[i].charAt(j);
                if (chr == 'X') {
                    list.add(new Coordinate(j, line.length - i - 1));
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
