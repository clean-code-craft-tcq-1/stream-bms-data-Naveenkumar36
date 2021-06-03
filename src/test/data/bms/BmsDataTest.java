package data.bms;

import org.junit.jupiter.api.Test;

import data.bms.model.BmsData;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
class BmsDataTest {

    private final BmsData bmsData = new BmsData(50, 10, 10);
    private final BmsData bmsData1 = new BmsData(50, 10, 10);

    @Test
    void testHashcodeAndToStringNotNull() {
        assertEquals(bmsData.hashCode(), bmsData1.hashCode());
        assertEquals(bmsData.toString(), bmsData1.toString());
    }

    @Test
    void testEquals() {
        BmsData bmsDataLocal = bmsData;
        assertNotEquals(bmsData, null);
        assertEquals(bmsData, bmsDataLocal);
    }
}
