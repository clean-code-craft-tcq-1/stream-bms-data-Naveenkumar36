package data.bms.provider;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import data.bms.model.BmsData;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class RandomDataProvider implements DataProvider {

    public static final int MIN_TEMP = 0;
    public static final int MAX_TEMP = 50;
    public static final int MIN_SOC = 10;
    public static final int MAX_SOC = 90;
    public static final float MIN_CHARGING_RATE = 0f;
    public static final float MAX_CHARGING_RATE = 1f;
    private final Random random = new Random();

    @Override
    public List<BmsData> provide(int samples) {
        return IntStream.range(0, samples).mapToObj(i -> getBmsData()).collect(Collectors.toList());
    }

    private BmsData getBmsData() {
        return new BmsData(getTemperature(), getSoc(), getCurrentValue());
    }

    private float getCurrentValue() {
        return getRandomValue(MIN_CHARGING_RATE, MAX_CHARGING_RATE);
    }

    private float getSoc() {
        return getRandomValue(MIN_SOC, MAX_SOC);
    }

    private float getTemperature() {
        return getRandomValue(MIN_TEMP, MAX_TEMP);
    }

    private float getRandomValue(
          float min,
          float max
    )
    {
        return random.nextFloat() * (max - min) + min;
    }
}
