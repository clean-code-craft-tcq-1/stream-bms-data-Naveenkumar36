package data.bms.provider;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import data.bms.model.BmsData;
import data.bms.utils.ClassHandler;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
class DataProviderTest {

    @ParameterizedTest
    @CsvSource({"20", "0"})
    void givenSamples_whenInvokedRandomProvide_returnData(int samples) {
        // ARRANGE ACT
        DataProvider dataProvider = new RandomDataProvider();
        List<BmsData> bmsData = dataProvider.provide(samples);

        // ASSERT
        assertNotNull(bmsData);
        if (samples > 0) {
            assertFalse(bmsData.isEmpty());
        } else {
            assertTrue(bmsData.isEmpty());
        }
        assertEquals(samples, bmsData.size());
    }

    @Test
    void givenSamples_whenInvokedCSVProvide_thenThrowException() {
        // ARRANGE
        DataProvider dataProvider = new CsvDataProvider();

        // ACT ASSERT
        assertThrows(UnsupportedOperationException.class, () -> dataProvider.provide(10));
    }

    @Nested
    class InstanceCreationTest {

        @ParameterizedTest
        @CsvSource({"Random", "Csv", "Unknown"})
        void givenName_whenInvokedClassHandle_thenReturnObject(String provider) throws Throwable {
            // ARRANGE
            DataProvider dataProvider;

            if ("Random".equals(provider)) {
                // ACT
                dataProvider = ClassHandler.obtainDataProviderInstance(provider);
                // ASSERT
                assertNotNull(dataProvider);
                assertEquals(10, dataProvider.provide(10).size());
            }
            if ("Csv".equals(provider)) {
                // ACT ASSERT
                assertThrows(
                      UnsupportedOperationException.class,
                      () -> ClassHandler.obtainDataProviderInstance(provider).provide(10)
                );
            }
            if ("Unknown".equals(provider)) {
                // ACT ASSERT
                assertThrows(Exception.class, () -> ClassHandler.obtainDataProviderInstance(provider));
            }
        }
    }
}
