package data.bms.model;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class BmsData {

    private final int temperature;
    private final int soc;
    private final int chargingRate;

    /**
     * Create BmsData model class
     *
     * @param temperature  int
     * @param soc          int
     * @param chargingRate int
     */
    public BmsData(
          int temperature,
          int soc,
          int chargingRate
    )
    {
        this.temperature = temperature;
        this.soc = soc;
        this.chargingRate = chargingRate;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getSoc() {
        return soc;
    }

    public int getChargingRate() {
        return chargingRate;
    }
}
