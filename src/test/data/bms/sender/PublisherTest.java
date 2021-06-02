package data.bms.sender;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.bms.FetchProperties;
import data.bms.model.BmsData;
import data.bms.provider.DataProvider;
import data.bms.utils.JsonConverter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
@ExtendWith(MockitoExtension.class)
class PublisherTest {

    private Publisher publisher;
    private Logger logger;
    @Mock
    private DataProvider dataProvider;
    private JsonConverter jsonConverter;
    @Mock
    private FetchProperties fetchProperties;

    @BeforeEach
    void setup() {
        jsonConverter = new JsonConverter(new ObjectMapper());
        logger = spy(new Logger());
        publisher = new Publisher(logger, dataProvider, jsonConverter, fetchProperties);
    }

    @Test
    void givenDataProvider_whenInvokedPublishing_thenPublishDataOnConsole() throws
          InterruptedException,
          JsonProcessingException
    {
        // ARRANGE
        InOrder loggerInOrder = inOrder(logger);
        int noOfSamples = 60;
        int delayInMilli = 300;
        when(fetchProperties.getSamples()).thenReturn(noOfSamples);
        when(fetchProperties.getDelayInMilliSec()).thenReturn(delayInMilli);
        when(dataProvider.provide(noOfSamples)).thenReturn(getBmsData());
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // ACT
        publisher.publishData();

        // ASSERT
        loggerInOrder.verify(logger).printWithDelay(argumentCaptor.capture(), eq(delayInMilli));
        assertEquals(jsonConverter.writeData(getBmsData().get(0)), argumentCaptor.getValue());
        loggerInOrder.verifyNoMoreInteractions();
    }

    @Test
    void givenDataProvider_whenInvokedPublishing_thenThrowsException() throws JsonProcessingException
    {
        // ARRANGE
        jsonConverter = mock(JsonConverter.class);
        publisher = new Publisher(logger, dataProvider, jsonConverter, fetchProperties);
        InOrder loggerInOrder = inOrder(logger);
        int noOfSamples = 60;
        int delayInMilli = 300;
        when(fetchProperties.getSamples()).thenReturn(noOfSamples);
        when(fetchProperties.getDelayInMilliSec()).thenReturn(delayInMilli);
        when(dataProvider.provide(noOfSamples)).thenReturn(getBmsData());
        when(jsonConverter.writeData(getBmsData().get(0))).thenThrow(JsonProcessingException.class);

        // ACT
        publisher.publishData();

        // VERIFY
        loggerInOrder.verify(logger).printException(any());
        loggerInOrder.verifyNoMoreInteractions();
    }

    private static List<BmsData> getBmsData() {
        List<BmsData> bmsData = new ArrayList<>();
        bmsData.add(new BmsData(50, 30, 15));
        return bmsData;
    }

}
