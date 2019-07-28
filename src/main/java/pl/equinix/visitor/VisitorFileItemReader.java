package pl.equinix.visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

/**
 * This is the class, that reads file and transform file lines to {@link pl.equinix.visitor.Visitor} objects.
 * Then this objects are putting to {@link pl.equinix.visitor.VisitorRepository}
 *
 * @author Tobiasz Piwowarczyk
 */
public class VisitorFileItemReader {

    private VisitorRepository visitorRepository;



    public VisitorFileItemReader(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }



    /**
     * This method read data from file placed into {@code resources} folder and transform it to {@link Visitor} object.
     * Then transformed objects are saving to {@link pl.equinix.visitor.VisitorRepository} object.
     * Blank lines are skipped.
     * File format is very important. Make sure, that file content looks like this:
     * <pre>
     *     {@code
     *      ...
     *      10:00,13:00
     *      14:00,15:00
     *      11:00,14:00
     *      ...
     *      }
     * </pre>
     *
     * @param filePath path to file with data
     */
    public void readFromFile(String filePath) throws Exception {

        URL resource = getClass().getClassLoader().getResource(filePath);
        if(resource == null)
            throw new IllegalArgumentException("Could not open this file: \"" + filePath + "\"");

        BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));

        String line;
        while((line = reader.readLine()) != null)
            if(!line.equals(""))
                this.visitorRepository.save(VisitorUtils.toVisitor(line));

        reader.close();
    }
}
