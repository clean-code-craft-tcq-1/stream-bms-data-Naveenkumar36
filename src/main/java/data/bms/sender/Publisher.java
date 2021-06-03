package data.bms.sender;

import data.bms.FetchProperties;
import data.bms.model.BmsData;
import data.bms.provider.DataProvider;
import data.bms.utils.JsonConverter;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class Publisher {

    private final Logger logger;
    private final DataProvider dataProvider;
    private final JsonConverter jsonConverter;
    private final FetchProperties fetchProperties;

    /**
     * Simple constructor.
     *
     * @param logger          Logger
     * @param dataProvider    DataProvider
     * @param jsonConverter   JsonConverter
     * @param fetchProperties FetchProperties
     */
    public Publisher(
          Logger logger,
          DataProvider dataProvider,
          JsonConverter jsonConverter,
          FetchProperties fetchProperties
    )
    {
        this.logger = logger;
        this.dataProvider = dataProvider;
        this.jsonConverter = jsonConverter;
        this.fetchProperties = fetchProperties;
    }

    /**
     * Publish data obtained from provider.
     */
    public void publishData() {
        try {
            for (BmsData bmsData : dataProvider.provide(fetchProperties.getSamples())) {
                logger.printWithDelay(jsonConverter.writeData(bmsData), fetchProperties.getDelayInMilliSec());
            }
        } catch (Exception e) {
            logger.printException(e);
        }
    }
}
