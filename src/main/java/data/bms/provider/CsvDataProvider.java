package data.bms.provider;

import java.util.List;

import data.bms.model.BmsData;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class CsvDataProvider implements DataProvider {

    @Override
    public List<BmsData> provide(int samples) {
        throw new UnsupportedOperationException("CSV is not yet supported");
    }
}
