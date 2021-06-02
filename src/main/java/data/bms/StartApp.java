package data.bms;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.bms.provider.DataProvider;
import data.bms.sender.Logger;
import data.bms.sender.Publisher;
import data.bms.utils.JsonConverter;

import static data.bms.utils.ClassHandler.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public final class StartApp {

    private static final String PROPERTY_NAME = "application.properties";

    private StartApp() {
    }

    /**
     * @param args args
     */
    public static void main(String[] args) {
        try {
            FetchProperties fetchProperties = new FetchProperties(PROPERTY_NAME);
            JsonConverter jsonConverter = new JsonConverter(new ObjectMapper());
            Logger logger = new Logger();
            DataProvider dataProvider = obtainDataProviderInstance(fetchProperties.getDataProvider());
            Publisher publisher = new Publisher(logger, dataProvider, jsonConverter, fetchProperties);
            publisher.publishData();
        } catch (Throwable throwable) {
            new Logger().printException(throwable);
        }
    }
}
