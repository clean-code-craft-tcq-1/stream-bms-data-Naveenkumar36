package data.bms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class FetchProperties {

    private static final String DELAY = "DELAY";
    private static final String PROVIDER = "PROVIDER";
    private static final String NO_OF_SAMPLE = "NO_OF_SAMPLE";
    private static final String FILE_NAME = "FILE_NAME";
    private final int delayInMilliSec;
    private final String dataProvider;
    private final int samples;
    private final String fileName;

    /**
     * Simple constructor.
     *
     * @param propertyName property Name
     * @throws IOException If it's unable to read properties
     */
    public FetchProperties(String propertyName) throws IOException {
        Properties properties = loadProperties(propertyName);
        this.delayInMilliSec = Integer.parseInt(properties.getProperty(DELAY));
        this.dataProvider = properties.getProperty(PROVIDER);
        this.samples = Integer.parseInt(properties.getProperty(NO_OF_SAMPLE));
        this.fileName = properties.getProperty(FILE_NAME);
    }

    private Properties loadProperties(String propertyName) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertyName)) {
            Properties prop = new Properties();
            if (input == null) {
                throw new IOException("Unable to load properties");
            }
            prop.load(input);
            return prop;

        }
    }

    public int getDelayInMilliSec() {
        return delayInMilliSec;
    }

    public String getDataProvider() {
        return dataProvider;
    }

    public int getSamples() {
        return samples;
    }

    public String getFileName() {
        return fileName;
    }
}
