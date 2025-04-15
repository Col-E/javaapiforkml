package de.micromata.opengis.kml.v_2_2_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IconTest
{
    @Test
    void hashCodeTest()
    {
        Icon icon1 = new Icon();
        Icon icon2 = new Icon();

        // Ensure we don't get an NPE
        assertEquals(icon1.hashCode(), icon2.hashCode());
    }
}
