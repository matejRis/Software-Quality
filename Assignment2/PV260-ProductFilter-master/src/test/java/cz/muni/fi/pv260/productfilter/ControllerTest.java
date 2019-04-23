package cz.muni.fi.pv260.productfilter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ControllerTest {
    private Controller controller;
    private Filter<Product> filter;
    private Product productBlack;
    private Product productWhite;

    private Input input;
    private Output output;
    private Logger logger;

    @Before
    public void setup() {
        input = mock(Input.class);
        output = mock(Output.class);
        logger = mock(Logger.class);
        productBlack = mock(Product.class);
        when(productBlack.getColor()).thenReturn(Color.BLACK);
        productWhite = mock(Product.class);
        when(productWhite.getColor()).thenReturn(Color.WHITE);
        controller = new Controller(input, output, logger);
        filter = new ColorFilter(Color.BLACK);
    }

    @Test
    public void testSelectedProductsToOutput() throws ObtainFailedException {
        when(input.obtainProducts()).thenReturn(Arrays.asList(productBlack, productWhite));
        ArgumentCaptor<Collection> argument = ArgumentCaptor.forClass((Collection.class));
        controller.select(filter);
        verify(output).postSelectedProducts(argument.capture());
        assertEquals("Argument should contain only one black product.",
                Arrays.asList(productBlack), argument.getValue());
    }

    @Test
    public void testControllerLogsMessageOnSuccess() throws ObtainFailedException {
        when(input.obtainProducts()).thenReturn(Arrays.asList(productBlack, productWhite));
        ArgumentCaptor<String> argumentTag = ArgumentCaptor.forClass((String.class));
        ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass((String.class));
        controller.select(filter);
        verify(logger).log(argumentTag.capture(), argumentString.capture());
        assertTrue("Logged message should be equal.",
                Controller.class.getSimpleName().equals(argumentTag.getValue()) &&
                "Successfully selected 1 out of 2 available products.".equals(argumentString.getValue()));
    }

    @Test
    public void testExceptionOccursWhenObtainingProductData() throws ObtainFailedException {
        when(input.obtainProducts()).thenThrow(ObtainFailedException.class);
        ArgumentCaptor<String> argumentTag = ArgumentCaptor.forClass((String.class));
        ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass((String.class));
        controller.select(filter);
        verify(logger).log(argumentTag.capture(), argumentString.capture());
        assertTrue("Logged message should be equal.",
                Controller.class.getSimpleName().equals(argumentTag.getValue()) &&
                ("Filter procedure failed with exception: " + ObtainFailedException.class.getName()).equals(argumentString.getValue()));
    }

    @Test
    public void testExceptionOccursWhenObtainingProductDataAndNothingIsInOutput() throws ObtainFailedException {
        when(input.obtainProducts()).thenThrow(ObtainFailedException.class);
        controller.select(filter);
        verify(output, never()).postSelectedProducts(Arrays.asList(productBlack));
    }
}