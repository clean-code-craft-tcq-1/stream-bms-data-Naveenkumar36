package data.bms.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public class JsonConverter {

    private final ObjectMapper objectMapper;

    /**
     * Simple constructor
     *
     * @param objectMapper ObjectMapper
     */
    public JsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Convert Object into string json format
     *
     * @param object Object
     * @return String
     *
     * @throws JsonProcessingException when conversion fails
     */
    public String writeData(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Convert string json data to respective object
     *
     * @param jsonString String json value
     * @param clazz      Class to which json data is mapped
     * @param <T>        Class of type T
     * @return Object of type T
     *
     * @throws IOException when conversion fails
     */
    public <T> T readUnchecked(
          String jsonString,
          Class<T> clazz
    ) throws IOException
    {
        return objectMapper.readValue(jsonString, clazz);
    }
}
