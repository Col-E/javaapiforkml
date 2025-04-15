package de.micromata.jak;

import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.NamespaceFilterHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests {@link NamespaceFilterHandler} allows registration of 3rd party KML specifications.
 * <br>
 * This can be used by 3rd parties that have KML 2.X complaint specifications with minor tweaks
 * not related to data model structure.
 */
public class AlternativeKmlSpecTest
{
    @Test
    void test()
    {
        String impl = "http://example.com/kml/2.X";
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<kml xmlns=\"" + impl + "\">\n"
                + "    <Document>\n"
                + "        <name>NDBC BuoyCAM Stations</name>\n"
                + "        <open>1</open>\n"
                + "    </Document>\n"
                + "</kml>\n";
        try
        {
            assertNull(Kml.unmarshal(content), "Expected unknown KML spec to fail initially");
            NamespaceFilterHandler.addKml2SpecUri(impl);
            assertNotNull(Kml.unmarshal(content));
        } catch (Exception ex)
        {
            fail(ex.getMessage());
        }
    }
}
