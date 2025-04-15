package de.micromata.jak.examples;

import de.micromata.opengis.kml.v_2_2_0.Boundary;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Polygon;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static de.micromata.jak.Utils.DOUBLE_CHECK_DELTA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CloneTest
{

    @Test
    public void testClonePlacemark()
    {
        String path = "src/test/resources/exampledata/worldBorders.kml";
        Kml kml = Kml.unmarshal(new File(path));
        Document document = (Document) kml.getFeature();
        Folder folder = (Folder) document.getFeature().get(0);
        Placemark pm = (Placemark) folder.getFeature().get(17);

        Placemark placemark = pm.clone();//Utils.clonePlacemark(pm);
        assertEquals(placemark, pm); // country with multipolygon, outer and inner linear ring objects

        assertEquals(pm.getId(), "BGD"); // country with multipolygon, outer and inner linear ring objects
        assertNotSame(pm, placemark);
        assertEquals(pm.getId(), placemark.getId());
        assertEquals(pm.getName(), placemark.getName());

        MultiGeometry multiGeometry = new MultiGeometry();
        MultiGeometry mg = ((MultiGeometry) pm.getGeometry());
        for (int i = 0; i < mg.getGeometry().size(); i++)
        {

            Polygon p = (Polygon) mg.getGeometry().get(i);
            Polygon polygon = new Polygon();
            polygon.withAltitudeMode(p.getAltitudeMode()).withExtrude(p.isExtrude());
            assertEquals(p.getAltitudeMode(), polygon.getAltitudeMode());
            assertEquals(p.isExtrude(), polygon.isExtrude());

            Boundary outerBoundaryIs = new Boundary();
            List<Coordinate> coordinates = outerBoundaryIs.createAndSetLinearRing().createAndSetCoordinates();

            // set the altitude of all vertices (height of the polygon)
            for (int j = 0; j < p.getOuterBoundaryIs().getLinearRing().getCoordinates().size(); j++)
            {
                Coordinate c = p.getOuterBoundaryIs().getLinearRing().getCoordinates().get(j);
                coordinates.add(j, new Coordinate(c.getLongitude(), c.getLatitude(), c.getAltitude()));
                double longitude = c.getLongitude();
                double latitude = c.getLatitude();
                double altitude = c.getAltitude();
                assertEquals(c.getLongitude(), coordinates.get(j).getLongitude(), DOUBLE_CHECK_DELTA);
                assertEquals(c.getLatitude(), coordinates.get(j).getLatitude(), DOUBLE_CHECK_DELTA);
                assertEquals(c.getAltitude(), coordinates.get(j).getAltitude(), DOUBLE_CHECK_DELTA);
                c.setLongitude(0);
                c.setLatitude(0);
                c.setAltitude(0);
                assertEquals(longitude, coordinates.get(j).getLongitude(), DOUBLE_CHECK_DELTA);
                assertEquals(latitude, coordinates.get(j).getLatitude(), DOUBLE_CHECK_DELTA);
                assertEquals(altitude, coordinates.get(j).getAltitude(), DOUBLE_CHECK_DELTA);

            }
            if (!p.getInnerBoundaryIs().isEmpty())
            {
                for (int j = 0; j < p.getInnerBoundaryIs().size(); j++)
                {
                    Boundary innerBoundary = new Boundary();
                    List<Coordinate> coordinatesInner = innerBoundary.createAndSetLinearRing().createAndSetCoordinates();
                    for (int k = 0; k < p.getInnerBoundaryIs().get(j).getLinearRing().getCoordinates().size(); k++)
                    {
                        Coordinate c = p.getInnerBoundaryIs().get(j).getLinearRing().getCoordinates().get(k);
                        coordinatesInner.add(k, new Coordinate(c.getLongitude(), c.getLatitude(), c.getAltitude()));
                        double longitude = c.getLongitude();
                        double latitude = c.getLatitude();
                        double altitude = c.getAltitude();
                        assertEquals(c.getLongitude(), coordinatesInner.get(k).getLongitude(), DOUBLE_CHECK_DELTA);
                        assertEquals(c.getLatitude(), coordinatesInner.get(k).getLatitude(), DOUBLE_CHECK_DELTA);
                        assertEquals(c.getAltitude(), coordinatesInner.get(k).getAltitude(), DOUBLE_CHECK_DELTA);
                        c.setLongitude(0);
                        c.setLatitude(0);
                        c.setAltitude(0);
                        assertEquals(longitude, coordinatesInner.get(k).getLongitude(), DOUBLE_CHECK_DELTA);
                        assertEquals(latitude, coordinatesInner.get(k).getLatitude(), DOUBLE_CHECK_DELTA);
                        assertEquals(altitude, coordinatesInner.get(k).getAltitude(), DOUBLE_CHECK_DELTA);
                    }
                    polygon.addToInnerBoundaryIs(innerBoundary);
                }
            }
            polygon.setOuterBoundaryIs(outerBoundaryIs);
            multiGeometry.addToGeometry(polygon);
        }

        placemark.setGeometry(multiGeometry);

    }

}
