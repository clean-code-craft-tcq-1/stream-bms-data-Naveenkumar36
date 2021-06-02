package data.bms.provider;

import java.util.List;

import data.bms.model.BmsData;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public interface DataProvider {

    /**
     * Returns data.
     *
     * @param samples int no of samples
     * @return List of  Bmsdata
     */
    List<BmsData> provide(int samples);
}
