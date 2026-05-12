package bg.tu_varna.sit.f24621689.data;

import bg.tu_varna.sit.f24621689.models.Planet;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * Handles the serialization of the application data to an XML file.
 */
public class UniverseXmlWriter {

    /**
     * Writes the current state of the Universe's planets to an XML file.
     * It utilizes Java's built-in {@link XMLEncoder} to convert
     * the compliant JavaBean objects into a structured XML format.
     *
     * @param universe The central {@link Universe} instance containing the data to be saved.
     * @param filePath The exact system file path where the XML file will be created or overwritten.
     * @throws Exception if the file stream cannot be opened, created, or if an error occurs
     * during the encoding process.
     */
    public void write(Universe universe, String filePath) throws Exception {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            encoder.writeObject(universe.getPlanets());
        }
    }
}