package de.micromata.jak.examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests fot the reading CSV files
 */
public class ReadCsvFileTest
{

    @Disabled
    void printAlphabeticalOrder() throws IOException
    {
        Map<String, Double> data = Utils.readCSVDoubleData("src/test/resources/exampledata/mobile_phone_2008.csv", 0, 3);
        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(data.keySet());
        Collections.sort(keys);
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }

    @Test
    void readCsvFileTest() throws IOException
    {
        Map<String, Double> data = Utils.readCSVDoubleData("src/test/resources/exampledata/mobile_phone_2008.csv", 0, 3);
        data.remove("maximum");
        data.remove("minimum");

        assertEquals(153, data.size()); // check size

        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(data.keySet());
        Collections.sort(keys);
        Iterator<String> iterator = keys.iterator();
        String code = null;
        int counter = 0;
        while (iterator.hasNext())
        {
            code = iterator.next();
            // check first and last element
            switch (counter)
            {
                case 0:
                    assertEquals("ABW", code);
                    break;
                case 153:
                    assertEquals("ZWE", code);
                    break;
            }
            counter++;
        }
    }

    @Test
    void readMultiDataFromCsvFile() throws IOException
    {
        Map<String, HashMap<String, Double>> result = Utils.readMultiDataFromCVS(
                "src/test/resources/exampledata/mobile_phone.csv", 0, 3, 2);
        result.remove("info");
        assertEquals(217, result.size()); // check size

        HashMap<String, Double> afg = result.get("AFG");
        assertNotNull(afg);
        ArrayList<String> afg_keys = new ArrayList<String>();
        afg_keys.addAll(afg.keySet());
        Collections.sort(afg_keys);
        Iterator<String> afg_iterator = afg_keys.iterator();
        String year = null;
        while (afg_iterator.hasNext())
        {
            year = afg_iterator.next();
            int year_int = Integer.parseInt(year);
            switch (year_int)
            {
                case 2002:
                    assertEquals(25000.0, afg.get(year), 0.1);
                    break;
                case 2003:
                    assertEquals(200000.0, afg.get(year), 0.1);
                    break;
                case 2004:
                    assertEquals(600000.0, afg.get(year), 0.1);
                    break;
                case 2005:
                    assertEquals(1200000.0, afg.get(year), 0.1);
                    break;
                case 2006:
                    assertEquals(2520366.0, afg.get(year), 0.1);
                    break;
                case 2007:
                    assertEquals(4668096.0, afg.get(year), 0.1);
                    break;
                case 2008:
                    assertEquals(7898909.0, afg.get(year), 0.1);
                    break;
                default:
                    assertEquals(0.0, afg.get(year), 0.1); // 1960 - 2001
            }
        }

    }

}
