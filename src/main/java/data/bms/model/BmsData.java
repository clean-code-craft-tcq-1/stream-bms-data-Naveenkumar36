package data.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
          @JsonProperty(value = "temperature", required = true) int temperature,
          @JsonProperty(value = "soc", required = true) int soc,
          @JsonProperty(value = "chargingRate", required = true) int chargingRate
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

    public int hashCode() {
        return 5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && getClass() == obj.getClass()) {
            BmsData other = (BmsData) obj;
            return temperature == other.temperature && soc == other.soc && chargingRate == other.chargingRate;
        } else {
            return false;
        }
    }

    public String toString() {
        return "{\n    temperature: " + temperature + "\n    soc: " + soc + "\n    chargingRate: " + chargingRate +
              "\n}";
    }
}
