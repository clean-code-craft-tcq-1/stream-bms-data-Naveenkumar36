package data.bms.sender;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import data.bms.FetchProperties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
class FetchPropertiesTest {

    @Test
    void givenPropertiesFile_whenInvokedFetchProperties_thenReturnProperties() throws IOException {
        // ARRANGE ACT
        FetchProperties fetchProperties = new FetchProperties("application.properties");

        // ASSERT
        assertTrue(fetchProperties.getDelayInMilliSec() > 0);
        assertTrue(fetchProperties.getSamples() > 0);
        assertNotNull(fetchProperties.getDataProvider());
    }

    @Test
    void givenFakePropertiesFile_whenInvokedFetchProperties_thenThrowException() {
        // ARRANGE ACT ASSERT
        assertThrows(IOException.class, () -> new FetchProperties("no.properties"));
    }

}
