package cz.muni.fi.pv260.productfilter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AtLeastNOfFilterTest<T> {
    @Mock
    private Filter<T> filter;

    @Mock
    private T item;

    @Before
    public void setup() {
        filter = mock(Filter.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIllegalArgumentException() {
        new AtLeastNOfFilter<>(0, filter);
    }

    @Test(expected = FilterNeverSucceeds.class)
    public void testConstructorThrowsFilterNeverSucceeds() {
        new AtLeastNOfFilter<>(2, filter);
    }

    @Test
    public void testFilterPasses() {
        AtLeastNOfFilter<T> atLeastNOfFilter = new AtLeastNOfFilter<>(1, filter);
        when(filter.passes(item)).thenReturn(true);
        assertTrue(atLeastNOfFilter.passes(item));
    }

    @Test
    public void testFilterFails() {
        AtLeastNOfFilter<T> atLeastNOfFilter = new AtLeastNOfFilter<>(1, filter);
        when(filter.passes(item)).thenReturn(false);
        assertFalse(atLeastNOfFilter.passes(item));
    }
}