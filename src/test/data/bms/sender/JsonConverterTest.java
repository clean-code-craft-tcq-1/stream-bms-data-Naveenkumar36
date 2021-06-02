package data.bms.sender;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.bms.model.BmsData;
import data.bms.utils.JsonConverter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
class JsonConverterTest {

    private final JsonConverter jsonConverter = new JsonConverter(new ObjectMapper());

    @Test
    void givenJsonString_whenInvokedConverter_thenReturnRespectiveObject() throws IOException {
        String jsonBmsData = "{\"temperature\":50,\"soc\":30,\"chargingRate\":15}";
        BmsData bmsData = jsonConverter.readUnchecked(jsonBmsData, BmsData.class);
        assertNotNull(bmsData);
        assertEquals(50, bmsData.getTemperature());
        assertEquals(30, bmsData.getSoc());
        assertEquals(15, bmsData.getChargingRate());
    }

    @Test
    void givenObject_whenInvokedConverter_thenReturnJsonString() throws IOException {
        BmsData bmsData = new BmsData(50, 15, 10);
        assertNotNull(jsonConverter.writeData(bmsData));
    }
}
