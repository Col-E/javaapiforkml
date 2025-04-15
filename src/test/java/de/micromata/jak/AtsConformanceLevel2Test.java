package de.micromata.jak;

import de.micromata.jak.internal.IAtsConformanceLevel2;
import de.micromata.opengis.kml.v_2_2_0.Alias;
import de.micromata.opengis.kml.v_2_2_0.BalloonStyle;
import de.micromata.opengis.kml.v_2_2_0.Camera;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Data;
import de.micromata.opengis.kml.v_2_2_0.ExtendedData;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.GroundOverlay;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.ImagePyramid;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LabelStyle;
import de.micromata.opengis.kml.v_2_2_0.LatLonBox;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Link;
import de.micromata.opengis.kml.v_2_2_0.ListStyle;
import de.micromata.opengis.kml.v_2_2_0.Location;
import de.micromata.opengis.kml.v_2_2_0.Model;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Overlay;
import de.micromata.opengis.kml.v_2_2_0.Pair;
import de.micromata.opengis.kml.v_2_2_0.PhotoOverlay;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.PolyStyle;
import de.micromata.opengis.kml.v_2_2_0.ResourceMap;
import de.micromata.opengis.kml.v_2_2_0.Scale;
import de.micromata.opengis.kml.v_2_2_0.ScreenOverlay;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.StyleMap;
import de.micromata.opengis.kml.v_2_2_0.StyleState;
import de.micromata.opengis.kml.v_2_2_0.ViewRefreshMode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Flori (f.bachmann@micromata.de)
 */
public class AtsConformanceLevel2Test implements IAtsConformanceLevel2
{
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(KmlReferencePojoTest.class.getName());

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc42PolyStyle()
     */
    @Test
    public void atc42PolyStyle()
    {
        // a kml:Scale element is not a descendant of kml:Update
        assertNull(Utils.findClass(PolyStyle.class, "update"));

        assertNotNull(Utils.findField(PolyStyle.class, "color"));
        assertNotNull(Utils.findField(PolyStyle.class, "colorMode"));
        assertNotNull(Utils.findField(PolyStyle.class, "fill"));
        assertNotNull(Utils.findField(PolyStyle.class, "outline"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc43CoordinatesAltitudeMode()
     */
    @Test
    public void atc43CoordinatesAltitudeMode()
    {
        // Applies to the control points
        // in kml:AbstractGeometryType/kml:coordinates
        // and kml:Model/kml:Location.
        Field location = Utils.findField(Model.class, "location");
        assertNotNull(location);
        assertEquals(Location.class, location.getType());

        // check if LinearRing contains coordinats
        Field coordinatesLinerarRing = Utils.findField(LinearRing.class, "coordinates");
        assertNotNull(coordinatesLinerarRing);
        assertEquals(Coordinate.class, Utils.getClassForGenericList(coordinatesLinerarRing.getGenericType().toString()));

        // check if Point contains coordinats
        Field coordinatesPoint = Utils.findField(Point.class, "coordinates");
        assertNotNull(coordinatesPoint);
        assertEquals(Coordinate.class, Utils.getClassForGenericList(coordinatesPoint.getGenericType().toString()));

        // check if Model contains coordinats
        Field locationModel = Utils.findField(Model.class, "location");
        assertNotNull(locationModel);
        assertEquals(Location.class, locationModel.getType());

        // check if LineString contains coordinats
        Field coordinatesLineString = Utils.findField(LineString.class, "coordinates");
        assertNotNull(coordinatesLineString);
        assertEquals(Coordinate.class, Utils.getClassForGenericList(coordinatesLineString.getGenericType().toString()));

        // check if Location contains altitude as double type
        Field locationAltitude = Utils.findField(Location.class, "altitude");
        assertNotNull(locationAltitude);
        assertEquals("double", locationAltitude.getType().getSimpleName());

        // check if Location contains altitude as double type
        Field coordinateAltitude = Utils.findField(Coordinate.class, "altitude");
        assertNotNull(coordinateAltitude);
        assertEquals("double", coordinateAltitude.getType().getSimpleName());
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc44ScaleMinimalContent()
     */
    @Test
    public void atc44ScaleMinimalContent()
    {
        // a kml:Scale element is not a descendant of kml:Update
        assertNull(Utils.findClass(Scale.class, "Update"));

        assertNotNull(Utils.findField(Scale.class, "x"));
        assertNotNull(Utils.findField(Scale.class, "y"));
        assertNotNull(Utils.findField(Scale.class, "z"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc45KMLMinimalContent()
     */
    @Test
    public void atc45KMLMinimalContent()
    {
        assertNotNull(Utils.findField(Kml.class, "networkLinkControl"));
        assertNotNull(Utils.findField(Kml.class, "feature"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc46ViewFormat()
     */
    @Test
    public void atc46ViewFormat()
    {
        assertNotNull(Utils.findField(Link.class, "viewFormat"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc47HttpQuery()
     */
    @Test
    public void atc47HttpQuery()
    {
        assertNotNull(Utils.findField(Link.class, "httpQuery"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc48LinearRingInPolygon()
     */
    @Test
    public void atc48LinearRingInPolygon()
    {
        //TODO: take a second look
        assertNotNull(Utils.findField(LinearRing.class, "extrude"));
        assertNotNull(Utils.findField(LinearRing.class, "tessellate"));
        assertNotNull(Utils.findField(LinearRing.class, "altitudeMode"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc49Data()
     */
    @Test
    public void atc49Data()
    {
        assertNotNull(Utils.findField(Data.class, "displayName"));
        assertNotNull(Utils.findField(Data.class, "value"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc50ResourceMapAlias()
     */
    @Test
    public void atc50ResourceMapAlias()
    {
        assertNotNull(Utils.findField(ResourceMap.class, "alias"));
        assertNotNull(Utils.findField(Alias.class, "sourceHref"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc51LinkRefreshValues()
     */
    @Test
    public void atc51LinkRefreshValues()
    {
        //TODO: take a second look
        assertNotNull(Utils.findField(Link.class, "refreshInterval"));
        assertNotNull(Utils.findField(Link.class, "viewRefreshTime"));
        assertNotNull(Utils.findField(ViewRefreshMode.class, "ON_STOP"));
        assertNotNull(Utils.findField(Icon.class, "refreshInterval"));
        assertNotNull(Utils.findField(Icon.class, "viewRefreshTime"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc52PhotoOverlay()
     */
    @Test
    public void atc52PhotoOverlay()
    {
        //TODO: take a second look
        assertNotNull(Utils.findField(PhotoOverlay.class, "icon"));
        // The parameters are embedded within the URL;
        // i.e, http://server.company.com/bigphoto/$[level]/row_$[x]_column_$[y].jpg.
        // Check for the kml:ImagePyramid when the x, y, level parameters are present,
        // or if the kml:ImagePyramid is present check for the x, y, level parameters.

        Icon icon = new Icon();
        icon.setHref("http://server.company.com/bigphoto/$[level]/row_$[x]_column_$[y].jpg");
        assertEquals(icon.getHref(), "http://server.company.com/bigphoto/$[level]/row_$[x]_column_$[y].jpg");
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc53GroundOverlayMinimalContent()
     */
    @Test
    public void atc53GroundOverlayMinimalContent()
    {
        // a kml:GroundOverlay element is not a descendant of kml:Update
        assertNull(Utils.findClass(GroundOverlay.class, "Update"));

        Field latlonBoxGroundOverlay = Utils.findField(GroundOverlay.class, "latLonBox");
        assertNotNull(latlonBoxGroundOverlay);
        assertEquals(LatLonBox.class, latlonBoxGroundOverlay.getType());
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc54Camera()
     */
    @Test
    public void atc54Camera()
    {
        //TODO: take a second look
        //TODO: if set to altitude is present the altitudeMode is not clmapToGround"

        assertNull(Utils.findClass(Camera.class, "Update"));
        assertNotNull(Utils.findField(Camera.class, "longitude"));
        assertNotNull(Utils.findField(Camera.class, "latitude"));
        assertNotNull(Utils.findField(Camera.class, "altitude"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc55Location()
     */
    @Test
    public void atc55Location()
    {
        assertNotNull(Utils.findField(Location.class, "longitude"));
        assertNotNull(Utils.findField(Location.class, "latitude"));
        assertNotNull(Utils.findField(Location.class, "altitude"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc56Overlay()
     */
    @Test
    public void atc56Overlay()
    {
        assertNull(Utils.findClass(Overlay.class, "Update"));
        assertNull(Utils.findClass(ScreenOverlay.class, "Update"));
        assertNull(Utils.findClass(PhotoOverlay.class, "Update"));
        assertNull(Utils.findClass(GroundOverlay.class, "Update"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc57ScreenOverlay()
     */
    @Test
    public void atc57ScreenOverlay()
    {
        assertNull(Utils.findClass(ScreenOverlay.class, "Update"));
        assertNotNull(Utils.findField(ScreenOverlay.class, "screenXY"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc58BaloonStyle()
     */
    @Test
    public void atc58BaloonStyle()
    {
        assertNull(Utils.findClass(BalloonStyle.class, "Update"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc59ExtendedData()
     */
    @Test
    public void atc59ExtendedData()
    {
        assertNotNull(Utils.findField(ExtendedData.class, "data"));
        assertNotNull(Utils.findField(ExtendedData.class, "schemaData"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc60Folder()
     */
    @Test
    public void atc60Folder()
    {
        assertNull(Utils.findClass(Folder.class, "Update"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc61IconStyle()
     */
    @Test
    public void atc61IconStyle()
    {
        assertNull(Utils.findClass(IconStyle.class, "Update"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc62ImagePyramid()
     */
    @Test
    public void atc62ImagePyramid()
    {
        assertNull(Utils.findClass(ImagePyramid.class, "Update"));

        assertNotNull(Utils.findField(ImagePyramid.class, "tileSize"));
        assertNotNull(Utils.findField(ImagePyramid.class, "maxWidth"));
        assertNotNull(Utils.findField(ImagePyramid.class, "maxHeight"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc63LabelStyle()
     */
    @Test
    public void atc63LabelStyle()
    {
        assertNull(Utils.findClass(LabelStyle.class, "Update"));

        assertNotNull(Utils.findField(LabelStyle.class, "color"));
        assertNotNull(Utils.findField(LabelStyle.class, "colorMode"));
        assertNotNull(Utils.findField(LabelStyle.class, "scale"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc64ListStyle()
     */
    @Test
    public void atc64ListStyle()
    {
        assertNull(Utils.findClass(ListStyle.class, "Update"));

        assertNotNull(Utils.findField(ListStyle.class, "listItemType"));
        assertNotNull(Utils.findField(ListStyle.class, "bgColor"));
        assertNotNull(Utils.findField(ListStyle.class, "itemIcon"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc65Sytle()
     */
    @Test
    public void atc65Sytle()
    {
        assertNull(Utils.findClass(Style.class, "Update"));
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc66MultiGeometry()
     */
    @Test
    public void atc66MultiGeometry()
    {
        assertNull(Utils.findClass(MultiGeometry.class, "Update"));

        Field geometryList = Utils.findField(MultiGeometry.class, "geometry");
        assertNotNull(geometryList);
        assertEquals(List.class, geometryList.getType());
    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc67Placemark()
     */
    @Test
    public void atc67Placemark()
    {
        assertNull(Utils.findClass(Placemark.class, "Update"));

        Field geometry = Utils.findField(Placemark.class, "geometry");
        assertNotNull(geometry);
        assertEquals(Geometry.class, geometry.getType());

    }

    /**
     * @see de.micromata.jak.internal.IAtsConformanceLevel2#atc68StyleMap()
     */
    @Test
    public void atc68StyleMap()
    {
        assertNull(Utils.findClass(StyleMap.class, "Update"));

        // check if StyleMap contains pair
        Field pair = Utils.findField(StyleMap.class, "pair");
        assertNotNull(pair);
        assertEquals(List.class, pair.getType());
        assertEquals(Pair.class, Utils.getClassForGenericList(pair.getGenericType().toString()));

        assertNotNull(Utils.findField(StyleState.class, "NORMAL"));
        assertNotNull(Utils.findField(StyleState.class, "HIGHLIGHT"));
    }

}
