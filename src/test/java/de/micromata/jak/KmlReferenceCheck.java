package de.micromata.jak;

import de.micromata.opengis.kml.v_2_2_0.Alias;
import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.BalloonStyle;
import de.micromata.opengis.kml.v_2_2_0.Camera;
import de.micromata.opengis.kml.v_2_2_0.Change;
import de.micromata.opengis.kml.v_2_2_0.ColorMode;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Create;
import de.micromata.opengis.kml.v_2_2_0.Delete;
import de.micromata.opengis.kml.v_2_2_0.DisplayMode;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.ExtendedData;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.GridOrigin;
import de.micromata.opengis.kml.v_2_2_0.GroundOverlay;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.ItemIconState;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LabelStyle;
import de.micromata.opengis.kml.v_2_2_0.LatLonAltBox;
import de.micromata.opengis.kml.v_2_2_0.LatLonBox;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.LineStyle;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.Link;
import de.micromata.opengis.kml.v_2_2_0.ListItemType;
import de.micromata.opengis.kml.v_2_2_0.ListStyle;
import de.micromata.opengis.kml.v_2_2_0.Location;
import de.micromata.opengis.kml.v_2_2_0.Lod;
import de.micromata.opengis.kml.v_2_2_0.LookAt;
import de.micromata.opengis.kml.v_2_2_0.Model;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.NetworkLink;
import de.micromata.opengis.kml.v_2_2_0.NetworkLinkControl;
import de.micromata.opengis.kml.v_2_2_0.Orientation;
import de.micromata.opengis.kml.v_2_2_0.Pair;
import de.micromata.opengis.kml.v_2_2_0.PhotoOverlay;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.PolyStyle;
import de.micromata.opengis.kml.v_2_2_0.Polygon;
import de.micromata.opengis.kml.v_2_2_0.RefreshMode;
import de.micromata.opengis.kml.v_2_2_0.Region;
import de.micromata.opengis.kml.v_2_2_0.Scale;
import de.micromata.opengis.kml.v_2_2_0.Schema;
import de.micromata.opengis.kml.v_2_2_0.ScreenOverlay;
import de.micromata.opengis.kml.v_2_2_0.Shape;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.StyleMap;
import de.micromata.opengis.kml.v_2_2_0.StyleState;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
import de.micromata.opengis.kml.v_2_2_0.Units;
import de.micromata.opengis.kml.v_2_2_0.Update;
import de.micromata.opengis.kml.v_2_2_0.ViewRefreshMode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class KmlReferenceCheck
{

    public static void ballonStyle(final BalloonStyle balloonstyle)
    {
        assertEquals("ID", balloonstyle.getId());
        assertEquals("ffffffff", balloonstyle.getBgColor());
        assertEquals("ff000000", balloonstyle.getTextColor());
        assertEquals("...", balloonstyle.getText());
        assertEquals(DisplayMode.DEFAULT, balloonstyle.getDisplayMode());
    }

    public static void ballonStyle(BalloonStyle balloonstyle, BalloonStyle marshalledAndBackAgain)
    {
        assertEquals(balloonstyle, marshalledAndBackAgain);
    }

    public static void ballonStyleExample(final Kml kml)
    {
        assertEquals("BalloonStyle.kml", kml.getFeature().getName());
        assertTrue(kml.getFeature().isOpen());
        assertEquals("exampleBalloonStyle", kml.getFeature().getStyleSelector().get(0).getId());

        final BalloonStyle balloonstyle = ((Style) kml.getFeature().getStyleSelector().get(0)).getBalloonStyle();
        assertEquals("ffffffbb", balloonstyle.getBgColor());
        assertEquals(
                "<![CDATA[" + "<b><font color='#CC0000' size='+3'>$[name]</font></b>" + "<br/><br/>" + "<font face='Courier'>$[description]</font>" + "<br/><br/>" + "Extra text that will appear in the description balloon" + "<br/><br/>" + "<!-- insert the to/from hyperlinks -->" + "$[geDirections]]]>",
                balloonstyle.getText());

        final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("BalloonStyle", placemark.getName());
        assertEquals("An example of BalloonStyle", placemark.getDescription());
        assertEquals("#exampleBalloonStyle", placemark.getStyleUrl());
        assertEquals(new Coordinate(-122.370533, 37.823842, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
    }

    public static void camera(final Camera camera)
    {
        assertEquals(0.0d, camera.getLongitude(), 0.00001d);
        assertEquals(0.0d, camera.getLatitude(), 0.00001d);
        assertEquals(0.0d, camera.getAltitude(), 0.00001d);
        assertEquals(0.0d, camera.getHeading(), 0.00001d);
        assertEquals(0.0d, camera.getTilt(), 0.00001d);
        assertEquals(0.0d, camera.getRoll(), 0.00001d);
        assertEquals(0.0d, camera.getHeading(), 0.00001d);
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, camera.getAltitudeMode());
    }

    public static void document(final Document document)
    {
        assertEquals("...", document.getName());
        assertTrue(document.isVisibility());
        assertFalse(document.isOpen());
        assertEquals("...", document.getAtomAuthor().getNameOrUriOrEmail().get(0));
        assertNotNull(document.getAtomLink());
        assertEquals("...", document.getAddress());
        assertNotNull(document.getXalAddressDetails());
        assertEquals("...", document.getPhoneNumber());
        assertEquals(2, document.getSnippet().getMaxLines());
        assertEquals("...", document.getSnippet().getValue());
        assertNotNull(document.getAbstractView());
        assertNotNull(document.getTimePrimitive());
        assertEquals("...", document.getStyleUrl());
        assertNotNull(document.getStyleSelector());
        assertNotNull(document.getRegion());
        assertNotNull(document.getMetadata());
        assertNotNull(document.getExtendedData());
    }

    public static void documentExample(final Kml kml)
    {
        assertEquals("Document.kml", kml.getFeature().getName());
        assertTrue(kml.getFeature().isOpen());

        assertEquals("exampleStyleDocument", kml.getFeature().getStyleSelector().get(0).getId());

        final LabelStyle labelstyle = ((Style) kml.getFeature().getStyleSelector().get(0)).getLabelStyle();
        assertEquals("ff0000cc", labelstyle.getColor());

        final Placemark placemark1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("Document Feature 1", placemark1.getName());
        assertEquals("#exampleStyleDocument", placemark1.getStyleUrl());
        assertEquals(new Coordinate(-122.371, 37.816, 0.0), ((Point) placemark1.getGeometry()).getCoordinates().get(0));

        final Placemark placemark2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
        assertEquals("Document Feature 2", placemark2.getName());
        assertEquals("#exampleStyleDocument", placemark2.getStyleUrl());
        assertEquals(new Coordinate(-122.370, 37.817, 0.0), ((Point) placemark2.getGeometry()).getCoordinates().get(0));
    }

    public static void extendedData(final ExtendedData extendedData)
    {
        assertEquals("string", extendedData.getData().get(0).getName());
        assertEquals("...", extendedData.getData().get(0).getDisplayName());
        assertEquals("...", extendedData.getData().get(0).getValue());

        assertEquals("anyURI", extendedData.getSchemaData().get(0).getSchemaUrl());
        assertEquals("", extendedData.getSchemaData().get(0).getSimpleData().get(0).getName());
        assertEquals("...", extendedData.getSchemaData().get(0).getSimpleData().get(0).getValue());
    }

    public static void extendedDataValue(final Placemark placemark)
    {
        assertEquals("Club house", placemark.getName());
        assertEquals("holeNumber", placemark.getExtendedData().getData().get(0).getName());
        assertEquals("1", placemark.getExtendedData().getData().get(0).getValue());
        assertEquals("holePar", placemark.getExtendedData().getData().get(1).getName());
        assertEquals("4", placemark.getExtendedData().getData().get(1).getValue());
    }

    public static void extendedDataSimpleData(final Document document)
    {
        final Placemark p1 = (Placemark) document.getFeature().get(0);
        assertEquals("Easy trail", p1.getName());
        assertEquals("#TrailHeadTypeId", p1.getExtendedData().getSchemaData().get(0).getSchemaUrl());
        assertEquals("TrailHeadName", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getName());
        assertEquals("Pi in the sky", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getValue());
        assertEquals("TrailLength", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getName());
        assertEquals("3.14159", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getValue());
        assertEquals("ElevationGain", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getName());
        assertEquals("10", p1.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getValue());
        assertEquals(new Coordinate(-122.000, 37.002), ((Point) p1.getGeometry()).getCoordinates().get(0));

        final Placemark p2 = (Placemark) document.getFeature().get(1);
        assertEquals("Difficult trail", p2.getName());
        assertEquals("#TrailHeadTypeId", p2.getExtendedData().getSchemaData().get(0).getSchemaUrl());
        assertEquals("TrailHeadName", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getName());
        assertEquals("Mount Everest", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(0).getValue());
        assertEquals("TrailLength", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getName());
        assertEquals("347.45", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(1).getValue());
        assertEquals("ElevationGain", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getName());
        assertEquals("10000", p2.getExtendedData().getSchemaData().get(0).getSimpleData().get(2).getValue());
        assertEquals(new Coordinate(-122.000, 37.002), ((Point) p2.getGeometry()).getCoordinates().get(0));
    }

    public static void extendedDataNameSapcePrefix(final ExtendedData extendedData)
    {
        // FIXME:!!!
    }

    public static void featureAscriptionElement(final Kml kml)
    {
        assertEquals("J. K. Rowling", kml.getFeature().getAtomAuthor().getNameOrUriOrEmail().get(0));
        assertEquals("http://www.harrypotter.com", kml.getFeature().getAtomLink().getHref());

        final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("Hogwarts", p1.getName());
        assertEquals(new Coordinate(1.0, 1.0, 0.0), ((Point) p1.getGeometry()).getCoordinates().get(0));

        final Placemark p2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
        assertEquals("Little Hangleton", p2.getName());
        assertEquals(new Coordinate(1.0, 2.0, 0.0), ((Point) p2.getGeometry()).getCoordinates().get(0));
    }

    @SuppressWarnings("deprecation")
    public static void folder(final Folder folder)
    {
        assertEquals("ID", folder.getId());
        assertEquals("...", folder.getName());
        assertTrue(folder.isVisibility());
        assertFalse(folder.isOpen());
        assertEquals("...", folder.getAtomAuthor().getNameOrUriOrEmail().get(0));
        assertNotNull(folder.getAtomLink());
        assertEquals("...", folder.getAddress());
        assertNotNull(folder.getXalAddressDetails());
        assertEquals("...", folder.getPhoneNumber());
        assertEquals(2, folder.getSnippet().getMaxLines());
        assertEquals("...", folder.getSnippet().getValue());
        assertNotNull(folder.getAbstractView());
        assertNotNull(folder.getTimePrimitive());
        assertEquals("...", folder.getStyleUrl());
        assertNotNull(folder.getStyleSelector());
        assertNotNull(folder.getRegion());
        assertNotNull(folder.getMetadata());
        assertNotNull(folder.getExtendedData());
    }

    public static void folderExample(final Kml kml)
    {
        final Folder folder = (Folder) kml.getFeature();
        assertEquals("Folder.kml", folder.getName());
        assertTrue(folder.isOpen());
        assertEquals("A folder is a container that can hold multiple other objects", folder.getDescription());

        final Placemark p1 = (Placemark) folder.getFeature().get(0);
        assertEquals("Folder object 1 (Placemark)", p1.getName());
        assertEquals(new Coordinate(-122.377588, 37.830266, 0.0), ((Point) p1.getGeometry()).getCoordinates().get(0));

        final Placemark p2 = (Placemark) folder.getFeature().get(1);
        assertEquals("Folder object 2 (Polygon)", p2.getName());
        assertEquals(new Coordinate("-122.377830,37.830445,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
                .get(0));
        assertEquals(new Coordinate("-122.377576,37.830631,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
                .get(1));
        assertEquals(new Coordinate("-122.377840,37.830642,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
                .get(2));
        assertEquals(new Coordinate("-122.377830,37.830445,0.0"), ((Polygon) p2.getGeometry()).getOuterBoundaryIs().getLinearRing().getCoordinates()
                .get(3));

        final Placemark p3 = (Placemark) folder.getFeature().get(2);
        assertEquals("Folder object 3 (Path)", p3.getName());
        assertEquals(new Coordinate(-122.378009, 37.830128, 0.0), ((LineString) p3.getGeometry()).getCoordinates().get(0));
        assertEquals(new Coordinate(-122.377885, 37.830379, 0.0), ((LineString) p3.getGeometry()).getCoordinates().get(1));
    }

    @SuppressWarnings("deprecation")
    public static void groundoverlay(final GroundOverlay groundoverlay)
    {
        // <!-- inherited from Feature element -->
        assertEquals("ID", groundoverlay.getId());
        assertEquals("...", groundoverlay.getName());
        assertTrue(groundoverlay.isVisibility());
        assertFalse(groundoverlay.isOpen());
        assertEquals("...", groundoverlay.getAtomAuthor().getNameOrUriOrEmail().get(0));
        assertNotNull(groundoverlay.getAtomLink());
        assertEquals("...", groundoverlay.getAddress());
        assertNotNull(groundoverlay.getXalAddressDetails());
        assertEquals("...", groundoverlay.getPhoneNumber());
        assertEquals(2, groundoverlay.getSnippet().getMaxLines());
        assertEquals("...", groundoverlay.getSnippet().getValue());
        assertNotNull(groundoverlay.getAbstractView());
        assertNotNull(groundoverlay.getTimePrimitive());
        assertEquals("...", groundoverlay.getStyleUrl());
        assertNotNull(groundoverlay.getStyleSelector());
        assertNotNull(groundoverlay.getRegion());
        assertNotNull(groundoverlay.getMetadata());
        assertNotNull(groundoverlay.getExtendedData());

        // <!-- inherited from Overlay element -->
        assertEquals("ffffffff", groundoverlay.getColor());
        assertEquals(0, groundoverlay.getDrawOrder());
        assertEquals("...", groundoverlay.getIcon().getHref());

        // <!-- specific to GroundOverlay -->
        assertEquals(0.0d, groundoverlay.getAltitude(), 0.0001d);
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, groundoverlay.getAltitudeMode());
        assertEquals(0.0d, groundoverlay.getLatLonBox().getNorth(), 0.0001d);
        assertEquals(0.0d, groundoverlay.getLatLonBox().getSouth(), 0.0001d);
        assertEquals(0.0d, groundoverlay.getLatLonBox().getEast(), 0.0001d);
        assertEquals(0.0d, groundoverlay.getLatLonBox().getWest(), 0.0001d);
        assertEquals(0.0d, groundoverlay.getLatLonBox().getRotation(), 0.0001d);
    }

    public static void groundOverlayLatLonBox(final LatLonBox latlonBox)
    {
        assertEquals(48.25475939255556d, latlonBox.getNorth(), 0.0001d);
        assertEquals(48.25207367852141d, latlonBox.getSouth(), 0.0001d);
        assertEquals(-90.86591508839973d, latlonBox.getEast(), 0.0001d);
        assertEquals(-90.8714285289695d, latlonBox.getWest(), 0.0001d);
        assertEquals(39.37878630116985d, latlonBox.getRotation(), 0.0001d);
    }

    public static void groundOverlayExample(final Kml kml)
    {
        final GroundOverlay groundoverlay = (GroundOverlay) kml.getFeature();
        assertEquals("GroundOverlay.kml", groundoverlay.getName());
        assertEquals("7fffffff", groundoverlay.getColor());
        assertEquals(1, groundoverlay.getDrawOrder());

        assertEquals("http://www.google.com/intl/en/images/logo.gif", groundoverlay.getIcon().getHref());
        assertEquals(RefreshMode.ON_INTERVAL, groundoverlay.getIcon().getRefreshMode());
        assertEquals(86400d, groundoverlay.getIcon().getRefreshInterval(), 0.0001);
        assertEquals(0.75d, groundoverlay.getIcon().getViewBoundScale(), 0.0001);

        assertEquals(37.83234d, groundoverlay.getLatLonBox().getNorth(), 0.0001d);
        assertEquals(37.832122d, groundoverlay.getLatLonBox().getSouth(), 0.0001d);
        assertEquals(-122.373033d, groundoverlay.getLatLonBox().getEast(), 0.0001d);
        assertEquals(-122.373724d, groundoverlay.getLatLonBox().getWest(), 0.0001d);
        assertEquals(45.0d, groundoverlay.getLatLonBox().getRotation(), 0.0001d);
    }

    public static void icon(final Icon icon)
    {
        assertEquals("...", icon.getHref());
        assertEquals(RefreshMode.ON_CHANGE, icon.getRefreshMode());
        assertEquals(4d, icon.getRefreshInterval(), 0.0001);
        assertEquals(ViewRefreshMode.NEVER, icon.getViewRefreshMode());
        assertEquals(4d, icon.getViewRefreshTime(), 0.0001);
        assertEquals(1d, icon.getViewBoundScale(), 0.0001);
        assertEquals("...", icon.getHttpQuery());
    }

    public static void iconStyle(final IconStyle iconstyle)
    {
        assertEquals("ffffffff", iconstyle.getColor());
        assertEquals(ColorMode.NORMAL, iconstyle.getColorMode());

        assertEquals(1d, iconstyle.getScale(), 0.0001);
        assertEquals(0, iconstyle.getHeading(), 0.0001);
        assertEquals("...", iconstyle.getIcon().getHref());

        assertEquals(0.5d, iconstyle.getHotSpot().getX(), 0.0001);
        assertEquals(0.5d, iconstyle.getHotSpot().getY(), 0.0001);
        assertEquals(Units.FRACTION, iconstyle.getHotSpot().getXunits());
        assertEquals(Units.FRACTION, iconstyle.getHotSpot().getYunits());
    }

    public static void iconStyleExample(final Kml kml)
    {
        final Style style = (Style) ((Document) kml.getFeature()).getStyleSelector().get(0);
        assertEquals("randomColorIcon", style.getId());
        assertEquals("ff00ff00", style.getIconStyle().getColor());
        assertEquals(ColorMode.RANDOM, style.getIconStyle().getColorMode());
        assertEquals(1.1d, style.getIconStyle().getScale(), 0.0001);
        assertEquals("http://maps.google.com/mapfiles/kml/pal3/icon21.png", style.getIconStyle().getIcon().getHref());

        final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("IconStyle.kml", placemark.getName());
        assertEquals("#randomColorIcon", placemark.getStyleUrl());
        assertEquals(new Coordinate(-122.36868, 37.831145, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
    }

    public static void kml(final Kml kml)
    {
        assertEquals("sky", kml.getHint());
    }

    public static void labelStyle(final LabelStyle labelstyle)
    {
        // <!-- inherited from ColorStyle -->
        assertEquals("ffffffff", labelstyle.getColor());
        assertEquals(ColorMode.NORMAL, labelstyle.getColorMode());

        // <!-- specific to LabelStyle -->
        assertEquals(1d, labelstyle.getScale(), 0.0001);
    }

    public static void labelStyleExample(final Kml kml)
    {
        final Style style = (Style) ((Document) kml.getFeature()).getStyleSelector().get(0);
        assertEquals("randomLabelColor", style.getId());
        assertEquals("ff0000cc", style.getLabelStyle().getColor());
        assertEquals(ColorMode.RANDOM, style.getLabelStyle().getColorMode());
        assertEquals(1.5d, style.getLabelStyle().getScale(), 0.0001);

        final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("LabelStyle.kml", placemark.getName());
        assertEquals("#randomLabelColor", placemark.getStyleUrl());
        assertEquals(new Coordinate(-122.367375, 37.829192, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
    }

    public static void linearRing(final LinearRing linearring)
    {
        assertEquals("ID", linearring.getId());

        // <!-- specific to LinearRing -->
        assertTrue(linearring.isExtrude());
        assertTrue(linearring.isTessellate());
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, linearring.getAltitudeMode());
        assertEquals(new Coordinate(0.0, 0.0, 0.0), linearring.getCoordinates().get(0));
    }

    public static void linearRingExample(final Kml kml)
    {
        final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("LinearRing.kml", placemark.getName());
        assertEquals(new Coordinate("-122.365662,37.826988,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(0));
        assertEquals(new Coordinate("-122.365202,37.826302,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(1));
        assertEquals(new Coordinate("-122.364581,37.82655,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(2));
        assertEquals(new Coordinate("-122.365038,37.827237,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(3));
        assertEquals(new Coordinate("-122.365662,37.826988,0.0"), ((Polygon) placemark.getGeometry()).getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(4));
    }

    public static void lineString(final LineString linestring)
    {
        assertEquals("ID", linestring.getId());

        // <!-- specific to LineString -->
        assertFalse(linestring.isExtrude());
        assertFalse(linestring.isTessellate());
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, linestring.getAltitudeMode());
        assertEquals(new Coordinate(0.0, 0.0, 0.0), linestring.getCoordinates().get(0));
    }

    public static void lineStringExample(final Kml kml)
    {
        assertEquals("LineString.kml", kml.getFeature().getName());
        assertTrue(kml.getFeature().isOpen());

        final LookAt lookat = ((LookAt) kml.getFeature().getAbstractView());
        assertEquals(-122.36415, lookat.getLongitude(), 0.0001);
        assertEquals(37.824553, lookat.getLatitude(), 0.0001);
        assertEquals(50.0, lookat.getTilt(), 0.0001);
        assertEquals(150.0, lookat.getRange(), 0.0001);
        assertEquals(0.0, lookat.getHeading(), 0.0001);

        final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("unextruded", p1.getName());
        assertFalse(((LineString) p1.getGeometry()).isExtrude());
        assertTrue(((LineString) p1.getGeometry()).isTessellate());
        assertEquals(new Coordinate(-122.364383, 37.824664, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(0));
        assertEquals(new Coordinate(-122.364152, 37.824322, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(1));

        final Placemark p2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
        assertEquals("extruded", p2.getName());
        assertTrue(((LineString) p2.getGeometry()).isExtrude());
        assertTrue(((LineString) p2.getGeometry()).isTessellate());
        assertEquals(new Coordinate(-122.364167, 37.824787, 50), ((LineString) p2.getGeometry()).getCoordinates().get(0));
        assertEquals(new Coordinate(-122.363917, 37.824423, 50), ((LineString) p2.getGeometry()).getCoordinates().get(1));

    }

    public static void lineStyle(final LineStyle linestyle)
    {
        assertEquals("ID", linestyle.getId());

        // <!-- inherited from ColorStyle -->
        assertEquals("ffffffff", linestyle.getColor());
        assertEquals(ColorMode.NORMAL, linestyle.getColorMode());

        // <!-- specific to LineStyle -->
        assertEquals(1.0, linestyle.getWidth(), 0.0001);

    }

    public static void lineStyleExample(final Kml kml)
    {
        assertEquals("LineStyle.kml", kml.getFeature().getName());
        assertTrue(kml.getFeature().isOpen());

        final Style style = (Style) ((Document) kml.getFeature()).getStyleSelector().get(0);
        assertEquals("linestyleExample", style.getId());
        assertEquals("7f0000ff", style.getLineStyle().getColor());
        assertEquals(4.0d, style.getLineStyle().getWidth(), 0.0001);

        final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("LineStyle Example", p1.getName());
        assertEquals("#linestyleExample", p1.getStyleUrl());
        assertTrue(((LineString) p1.getGeometry()).isExtrude());
        assertTrue(((LineString) p1.getGeometry()).isTessellate());
        assertEquals(new Coordinate(-122.364383, 37.824664, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(0));
        assertEquals(new Coordinate(-122.364152, 37.824322, 0.0), ((LineString) p1.getGeometry()).getCoordinates().get(1));
    }

    public static void link(final Link link)
    {
        assertEquals("ID", link.getId());
        assertEquals("...", link.getHref());
        assertEquals(RefreshMode.ON_CHANGE, link.getRefreshMode());
        assertEquals(4.0d, link.getRefreshInterval(), 0.0001);
        assertEquals(ViewRefreshMode.NEVER, link.getViewRefreshMode());
        assertEquals(4.0d, link.getViewRefreshTime(), 0.0001);
        assertEquals(1.0d, link.getViewBoundScale(), 0.0001);
        assertEquals("BBOX=bboxWest", link.getViewFormat());
        assertEquals("...", link.getHttpQuery());
    }

    public static void linkExample(final NetworkLink networklink)
    {
        assertEquals("NE US Radar", networklink.getName());
        assertTrue(networklink.isFlyToView());
        final Link link = networklink.getLink();
        assertEquals("http://www.example.com/geotiff/NE/MergedReflectivityQComposite.kml", link.getHref());
        assertEquals(RefreshMode.ON_INTERVAL, link.getRefreshMode());
        assertEquals(30.0d, link.getRefreshInterval(), 0.0001);
        assertEquals(ViewRefreshMode.ON_STOP, link.getViewRefreshMode());
        assertEquals(7.0d, link.getViewRefreshTime(), 0.0001);
        assertEquals("BBOX=bboxWest", link.getViewFormat());
    }

    public static void listStyle(final ListStyle liststyle)
    {
        assertEquals("ID", liststyle.getId());
        assertEquals(ListItemType.CHECK, liststyle.getListItemType());
        assertEquals("ffffffff", liststyle.getBgColor());
        assertEquals(ItemIconState.OPEN, liststyle.getItemIcon().get(0).getState().get(0));
        assertEquals("...", liststyle.getItemIcon().get(0).getHref());

    }

    public static void lookAt(final LookAt lookat)
    {
        assertEquals(0.0, lookat.getLongitude(), 0.0001);
        assertEquals(0.0, lookat.getLatitude(), 0.0001);
        assertEquals(0.0, lookat.getRange(), 0.0001);
        assertEquals(0.0, lookat.getTilt(), 0.0001);
        assertEquals(0.0, lookat.getHeading(), 0.0001);
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, lookat.getAltitudeMode());
    }

    public static void lookAtExample(final Kml kml)
    {
        assertEquals("LookAt.kml", kml.getFeature().getName());

        final LookAt lookat = ((LookAt) kml.getFeature().getAbstractView());
        // FIXME: <gx:TimeStamp>
        // FIXME: <when>1994</when>
        // FIXME: </gx:TimeStamp>

        assertEquals(-122.363, lookat.getLongitude(), 0.0001);
        assertEquals(37.81, lookat.getLatitude(), 0.0001);
        assertEquals(2000.0, lookat.getAltitude(), 0.0001);
        assertEquals(500.0, lookat.getRange(), 0.0001);
        assertEquals(45.0, lookat.getTilt(), 0.0001);
        assertEquals(0.0, lookat.getHeading(), 0.0001);
        assertEquals(AltitudeMode.RELATIVE_TO_GROUND, lookat.getAltitudeMode());

        final Placemark placemark = (Placemark) kml.getFeature();
        assertEquals(new Coordinate(-122.363, 37.82, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
    }

    public static void model(final Model model)
    {
        assertEquals("ID", model.getId());
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, model.getAltitudeMode());

        assertEquals(0.0, model.getLocation().getLongitude(), 0.0001);
        assertEquals(0.0, model.getLocation().getLatitude(), 0.0001);
        assertEquals(0.0, model.getLocation().getAltitude(), 0.0001);

        assertEquals(0.0, model.getOrientation().getHeading(), 0.0001);
        assertEquals(0.0, model.getOrientation().getTilt(), 0.0001);
        assertEquals(0.0, model.getOrientation().getRoll(), 0.0001);

        assertEquals(1.0, model.getScale().getX(), 0.0001);
        assertEquals(1.0, model.getScale().getY(), 0.0001);
        assertEquals(1.0, model.getScale().getZ(), 0.0001);

        assertEquals("...", model.getLink().getHref());

        assertEquals("...", model.getResourceMap().getAlias().get(0).getTargetHref());
        assertEquals("...", model.getResourceMap().getAlias().get(0).getSourceHref());

    }

    public static void modelLocation(final Location location)
    {
        assertEquals(39.55375305703105, location.getLongitude(), 0.0001);
        assertEquals(-118.9813220168456, location.getLatitude(), 0.0001);
        assertEquals(1223.0, location.getAltitude(), 0.0001);

    }

    public static void modelOrientation(final Orientation orientation)
    {
        assertEquals(45.0, orientation.getHeading(), 0.0001);
        assertEquals(10.0, orientation.getTilt(), 0.0001);
        assertEquals(0.0, orientation.getRoll(), 0.0001);

    }

    public static void modelScale(final Scale scale)
    {
        assertEquals(2.5, scale.getX(), 0.0001);
        assertEquals(2.5, scale.getY(), 0.0001);
        assertEquals(3.5, scale.getZ(), 0.0001);

    }

    public static void modelResourceMap(final Alias alias)
    {
        assertEquals("../images/foo.jpg", alias.getTargetHref());
        assertEquals("c:\\mytextures\\foo.jpg", alias.getSourceHref());
    }

    public static void modelExample(final Model model)
    {
        assertEquals("khModel543", model.getId());
        assertEquals(AltitudeMode.RELATIVE_TO_GROUND, model.getAltitudeMode());

        assertEquals(39.55375305703105, model.getLocation().getLongitude(), 0.0001);
        assertEquals(-118.9813220168456, model.getLocation().getLatitude(), 0.0001);
        assertEquals(1223.0, model.getLocation().getAltitude(), 0.0001);

        assertEquals(45.0, model.getOrientation().getHeading(), 0.0001);
        assertEquals(10.0, model.getOrientation().getTilt(), 0.0001);
        assertEquals(0.0, model.getOrientation().getRoll(), 0.0001);

        assertEquals(1.0, model.getScale().getX(), 0.0001);
        assertEquals(1.0, model.getScale().getY(), 0.0001);
        assertEquals(1.0, model.getScale().getZ(), 0.0001);

        assertEquals("house.dae", model.getLink().getHref());
        // FIXME: TODO <refreshMode>once</refreshMode>
        assertEquals(RefreshMode.ON_CHANGE, model.getLink().getRefreshMode());

        assertEquals("../files/CU-Macky---Center-StairsnoCulling.jpg", model.getResourceMap().getAlias().get(0).getTargetHref());
        assertEquals("CU-Macky---Center-StairsnoCulling.jpg", model.getResourceMap().getAlias().get(0).getSourceHref());

        assertEquals("../files/CU-Macky-4sideturretnoCulling.jpg", model.getResourceMap().getAlias().get(1).getTargetHref());
        assertEquals("CU-Macky-4sideturretnoCulling.jpg", model.getResourceMap().getAlias().get(1).getSourceHref());

        assertEquals("../files/CU-Macky-Back-NorthnoCulling.jpg", model.getResourceMap().getAlias().get(2).getTargetHref());
        assertEquals("CU-Macky-Back-NorthnoCulling.jpg", model.getResourceMap().getAlias().get(2).getSourceHref());
    }

    public static void multiGeometry(final MultiGeometry multigeometry)
    {
        assertEquals("ID", multigeometry.getId());
    }

    public static void multiGeometryExample(final Placemark placemark)
    {
        assertEquals("SF Marina Harbor Master", placemark.getName());
        assertFalse(placemark.isVisibility());
        final List<Geometry> multigeometry = ((MultiGeometry) placemark.getGeometry()).getGeometry();
        assertEquals(new Coordinate(-122.4425587930444, 37.80666418607323, 0.0), ((LineString) multigeometry.get(0)).getCoordinates()
                .get(0));
        assertEquals(new Coordinate(-122.4428379594768, 37.80663578323093, 0.0), ((LineString) multigeometry.get(0)).getCoordinates()
                .get(1));
        assertEquals(new Coordinate(-122.4425509770566, 37.80662588061205, 0.0), ((LineString) multigeometry.get(1)).getCoordinates()
                .get(0));
        assertEquals(new Coordinate(-122.4428340530617, 37.8065999493009, 0.0), ((LineString) multigeometry.get(1)).getCoordinates()
                .get(1));
    }

    @SuppressWarnings("deprecation")
    public static void networkLink(final NetworkLink networklink)
    {
        assertEquals("ID", networklink.getId());
        // <!-- inherited from Feature element -->
        assertEquals("...", networklink.getName());
        assertTrue(networklink.isVisibility());
        assertFalse(networklink.isOpen());
        assertEquals("...", networklink.getAtomAuthor().getNameOrUriOrEmail().get(0));
        assertNotNull(networklink.getAtomLink());
        assertEquals("...", networklink.getAddress());
        assertNotNull(networklink.getXalAddressDetails());
        assertEquals("...", networklink.getPhoneNumber());
        assertEquals(2, networklink.getSnippet().getMaxLines());
        assertEquals("...", networklink.getSnippet().getValue());
        assertNotNull(networklink.getAbstractView());
        assertNotNull(networklink.getTimePrimitive());
        assertEquals("...", networklink.getStyleUrl());
        assertNotNull(networklink.getStyleSelector());
        assertNotNull(networklink.getRegion());
        assertNotNull(networklink.getMetadata());
        assertNotNull(networklink.getExtendedData());

        // <!-- specific to NetworkLink -->
        assertFalse(networklink.isRefreshVisibility());
        assertFalse(networklink.isFlyToView());
        assertEquals("...", networklink.getLink().getHref());
    }

    public static void networkLinkExample(final Document document)
    {
        assertTrue(document.isVisibility());
        final NetworkLink networklink = (NetworkLink) document.getFeature().get(0);
        assertEquals("NE US Radar", networklink.getName());
        assertTrue(networklink.isRefreshVisibility());
        assertTrue(networklink.isFlyToView());
        assertEquals("...", networklink.getLink().getHref());
    }

    @SuppressWarnings("deprecation")
    public static void networkLinkControl(final NetworkLinkControl networklinkcontrol)
    {
        assertEquals(0.0, networklinkcontrol.getMinRefreshPeriod(), 0.0001);
        assertEquals(-1.0, networklinkcontrol.getMaxSessionLength(), 0.0001);
        assertEquals("...", networklinkcontrol.getCookie());
        assertEquals("...", networklinkcontrol.getMessage());
        assertEquals("...", networklinkcontrol.getLinkName());
        assertEquals("...", networklinkcontrol.getLinkDescription());
        assertEquals(2, networklinkcontrol.getLinkSnippet().getMaxLines());
        assertEquals("...", networklinkcontrol.getLinkSnippet().getValue());
        assertEquals("...", networklinkcontrol.getExpires());
        assertNotNull(networklinkcontrol.getUpdate());
        assertNotNull(networklinkcontrol.getAbstractView());

    }

    public static void networkLinkControlExample(final Kml kml)
    {
        assertEquals("This is a pop-up message. You will only see this once", kml.getNetworkLinkControl().getMessage());
        assertEquals("cookie=sometext", kml.getNetworkLinkControl().getCookie());
        assertEquals("New KML features", kml.getNetworkLinkControl().getLinkName());
        assertEquals("<![CDATA[KML now has new features available!]]>", kml.getNetworkLinkControl().getLinkDescription());

    }

    public static void overlayIcon(final Icon icon)
    {
        assertEquals("icon.jpg", icon.getHref());
    }

    @SuppressWarnings("deprecation")
    public static void photoOverlay(final PhotoOverlay photooverlay)
    {
        // <!-- inherited from Feature element -->
        assertEquals("...", photooverlay.getName());
        assertTrue(photooverlay.isVisibility());
        assertFalse(photooverlay.isOpen());
        assertNotNull(photooverlay.getAtomAuthor());
        assertNotNull(photooverlay.getAtomLink());
        assertEquals("...", photooverlay.getAddress());
        assertNotNull(photooverlay.getXalAddressDetails());
        assertEquals("...", photooverlay.getPhoneNumber());
        assertEquals(2, photooverlay.getSnippet().getMaxLines());
        assertEquals("...", photooverlay.getSnippet().getValue());
        assertNotNull(photooverlay.getAbstractView());
        assertNotNull(photooverlay.getTimePrimitive());
        assertEquals("...", photooverlay.getStyleUrl());
        assertNotNull(photooverlay.getStyleSelector());
        assertNotNull(photooverlay.getRegion());
        assertNotNull(photooverlay.getMetadata());
        assertNotNull(photooverlay.getExtendedData());

        // <!-- inherited from Overlay element -->
        assertEquals("ffffffff", photooverlay.getColor());
        assertEquals(0, photooverlay.getDrawOrder());
        assertEquals("...", photooverlay.getIcon().getHref());

        // <!-- specific to PhotoOverlay -->
        assertEquals(0, photooverlay.getRotation(), 0.0001);
        assertEquals(0.0, photooverlay.getViewVolume().getLeftFov(), 0.0001);
        assertEquals(0.0, photooverlay.getViewVolume().getRightFov(), 0.0001);
        assertEquals(0.0, photooverlay.getViewVolume().getBottomFov(), 0.0001);
        assertEquals(0.0, photooverlay.getViewVolume().getTopFov(), 0.0001);
        assertEquals(0.0, photooverlay.getViewVolume().getNear(), 0.0001);

        assertEquals(0, photooverlay.getImagePyramid().getTileSize());
        assertEquals(0, photooverlay.getImagePyramid().getMaxWidth());
        assertEquals(0, photooverlay.getImagePyramid().getMaxHeight());
        assertEquals(GridOrigin.LOWER_LEFT, photooverlay.getImagePyramid().getGridOrigin());
        assertEquals(new Coordinate(0.0, 0.0, 0.0), photooverlay.getPoint().getCoordinates().get(0));
        assertEquals(Shape.RECTANGLE, photooverlay.getShape());
    }

    public static void photoOverlayExample(final PhotoOverlay photooverlay)
    {
        // <!-- inherited from Feature element -->
        assertEquals("A simple non-pyramidal photo", photooverlay.getName());
        assertEquals("High above the ocean", photooverlay.getDescription());

        // <!-- Overlay elements -->
        // <!-- A simple normal jpeg image -->
        assertEquals("small-photo.jpg", photooverlay.getIcon().getHref());

        // <!-- PhotoOverlay elements -->
        // <!-- default: <rotation> default is 0 -->
        assertEquals(0, photooverlay.getRotation(), 0.0001);
        assertEquals(1000.0, photooverlay.getViewVolume().getNear(), 0.0001);
        assertEquals(-60.0, photooverlay.getViewVolume().getLeftFov(), 0.0001);
        assertEquals(60.0, photooverlay.getViewVolume().getRightFov(), 0.0001);
        assertEquals(-45.0, photooverlay.getViewVolume().getBottomFov(), 0.0001);
        assertEquals(45.0, photooverlay.getViewVolume().getTopFov(), 0.0001);

        assertEquals(new Coordinate(1.0, 1.0, 0.0), photooverlay.getPoint().getCoordinates().get(0));
        // <!-- default: <shape> -->
        assertEquals(Shape.RECTANGLE, photooverlay.getShape());
    }

    @SuppressWarnings("deprecation")
    public static void placemark(final Placemark placemark)
    {
        // <!-- inherited from Feature element -->
        assertEquals("...", placemark.getName());
        assertTrue(placemark.isVisibility());
        assertFalse(placemark.isOpen());
        assertNotNull(placemark.getAtomAuthor());
        assertNotNull(placemark.getAtomLink());
        assertEquals("...", placemark.getAddress());
        assertNotNull(placemark.getXalAddressDetails());
        assertEquals("...", placemark.getPhoneNumber());
        assertEquals(2, placemark.getSnippet().getMaxLines());
        assertEquals("...", placemark.getSnippet().getValue());
        assertNotNull(placemark.getAbstractView());
        assertNotNull(placemark.getTimePrimitive());
        assertEquals("...", placemark.getStyleUrl());
        assertNotNull(placemark.getStyleSelector());
        assertNotNull(placemark.getRegion());
        assertNotNull(placemark.getMetadata());
        assertNotNull(placemark.getExtendedData());

        // <!-- specific to Placemark element -->
        assertNotNull(placemark.getGeometry());
    }

    public static void placemarkExample(final Placemark placemark)
    {
        assertEquals("Google Earth - New Placemark", placemark.getName());
        assertEquals("Some Descriptive text.", placemark.getDescription());

        final LookAt lookat = (LookAt) placemark.getAbstractView();
        assertEquals(-90.86879847669974, lookat.getLongitude(), 0.0001);
        assertEquals(48.25330383601299, lookat.getLatitude(), 0.0001);
        assertEquals(440.8, lookat.getRange(), 0.0001);
        assertEquals(8.3, lookat.getTilt(), 0.0001);
        assertEquals(2.7, lookat.getHeading(), 0.0001);

        assertEquals(new Coordinate(-90.86948943473118, 48.25450093195546, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(
                0));
    }

    public static void point(final Point point)
    {
        assertEquals("ID", point.getId());

        // <!-- specific to Point -->
        assertFalse(point.isExtrude());
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, point.getAltitudeMode());

        assertEquals(new Coordinate(0.0, 0.0, 0.0), point.getCoordinates().get(0));
    }

    public static void pointExample(final Point point)
    {
        assertEquals(new Coordinate(-90.86948943473118, 48.25450093195546), point.getCoordinates().get(0));
    }

    public static void polygon(final Polygon polygon)
    {
        assertEquals("ID", polygon.getId());

        // <!-- specific to Polygon -->
        assertFalse(polygon.isExtrude());
        assertFalse(polygon.isTessellate());
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, polygon.getAltitudeMode());

        assertEquals(new Coordinate(0.0, 0.0, 0.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(0));
        assertEquals(new Coordinate(0.0, 0.0, 0.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates().get(0));

    }

    public static void polygonExample(final Kml kml)
    {
        assertEquals("Polygon.kml", kml.getFeature().getName());
        assertFalse(kml.getFeature().isOpen());

        final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("hollow box", placemark.getName());
        final Polygon polygon = (Polygon) placemark.getGeometry();
        assertTrue(polygon.isExtrude());
        assertEquals(AltitudeMode.RELATIVE_TO_GROUND, polygon.getAltitudeMode());

        assertEquals(new Coordinate(-122.366278, 37.818844, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(0));
        assertEquals(new Coordinate(-122.365248, 37.819267, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(1));
        assertEquals(new Coordinate(-122.365640, 37.819861, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(2));
        assertEquals(new Coordinate(-122.366669, 37.819429, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(3));
        assertEquals(new Coordinate(-122.366278, 37.818844, 30.0), polygon.getOuterBoundaryIs().getLinearRing().getCoordinates().get(4));

        assertEquals(new Coordinate(-122.366212, 37.818977, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(0));
        assertEquals(new Coordinate(-122.365424, 37.819294, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(1));
        assertEquals(new Coordinate(-122.365704, 37.819731, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(2));
        assertEquals(new Coordinate(-122.366488, 37.819402, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(3));
        assertEquals(new Coordinate(-122.366212, 37.818977, 30.0), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(4));

    }

    public static void polyStyle(final PolyStyle polystyle)
    {
        assertEquals("ID", polystyle.getId());

        // <!-- inherited from ColorStyle -->
        assertEquals("ffffffff", polystyle.getColor());
        assertEquals(ColorMode.NORMAL, polystyle.getColorMode());

        // <!-- specific to PolyStyle -->
        assertTrue(polystyle.isFill());
        assertTrue(polystyle.isOutline());
    }

    public static void polyStyleExample(final Kml kml)
    {
        assertEquals("PolygonStyle.kml", kml.getFeature().getName());
        assertTrue(kml.getFeature().isOpen());

        final Style style = ((Style) kml.getFeature().getStyleSelector().get(0));
        assertEquals("examplePolyStyle", style.getId());
        assertEquals("ff0000cc", style.getPolyStyle().getColor());
        assertEquals(ColorMode.RANDOM, style.getPolyStyle().getColorMode());

        final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("hollow box", placemark.getName());
        assertEquals("#examplePolyStyle", placemark.getStyleUrl());

        final Polygon polygon = (Polygon) placemark.getGeometry();
        assertTrue(polygon.isExtrude());
        assertEquals(AltitudeMode.RELATIVE_TO_GROUND, polygon.getAltitudeMode());

        assertEquals(new Coordinate(-122.3662784465226, 37.81884427772081, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(0));
        assertEquals(new Coordinate(-122.3652480684771, 37.81926777010555, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(1));
        assertEquals(new Coordinate(-122.365640222455, 37.81986126286519, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(2));
        assertEquals(new Coordinate(-122.36666937925, 37.81942987753481, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(3));
        assertEquals(new Coordinate(-122.3662784465226, 37.81884427772081, 30.0), polygon.getOuterBoundaryIs().getLinearRing()
                .getCoordinates().get(4));

        assertEquals(new Coordinate("-122.366212593918,37.81897719083808,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(0));
        assertEquals(new Coordinate("-122.3654241733188,37.81929450992014,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(1));
        assertEquals(new Coordinate("-122.3657048517827,37.81973175302663,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(2));
        assertEquals(new Coordinate("-122.3664882465854,37.81940249291773,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(3));
        assertEquals(new Coordinate("-122.366212593918,37.81897719083808,30.0"), polygon.getInnerBoundaryIs().get(0).getLinearRing().getCoordinates()
                .get(4));
    }

    public static void region(final Region region)
    {
        assertEquals("ID", region.getId());

        assertEquals(0.0, region.getLatLonAltBox().getNorth(), 0.0001);
        assertEquals(0.0, region.getLatLonAltBox().getSouth(), 0.0001);
        assertEquals(0.0, region.getLatLonAltBox().getEast(), 0.0001);
        assertEquals(0.0, region.getLatLonAltBox().getWest(), 0.0001);
        assertEquals(0.0, region.getLatLonAltBox().getMinAltitude(), 0.0001);
        assertEquals(0.0, region.getLatLonAltBox().getMaxAltitude(), 0.0001);
        assertEquals(AltitudeMode.CLAMP_TO_GROUND, region.getLatLonAltBox().getAltitudeMode());

        assertEquals(0.0, region.getLod().getMinLodPixels(), 0.0001);
        assertEquals(-1.0, region.getLod().getMaxLodPixels(), 0.0001);
        assertEquals(0.0, region.getLod().getMinFadeExtent(), 0.0001);
        assertEquals(0.0, region.getLod().getMaxFadeExtent(), 0.0001);

    }

    public static void regionLatLonAltBox(final LatLonAltBox latlonAltBox)
    {
        assertEquals(43.374, latlonAltBox.getNorth(), 0.0001);
        assertEquals(42.983, latlonAltBox.getSouth(), 0.0001);
        assertEquals(-0.335, latlonAltBox.getEast(), 0.0001);
        assertEquals(-1.423, latlonAltBox.getWest(), 0.0001);
        assertEquals(0.0, latlonAltBox.getMinAltitude(), 0.0001);
        assertEquals(0.0, latlonAltBox.getMaxAltitude(), 0.0001);
    }

    public static void regionLod(final Lod lod)
    {
        assertEquals(256.0, lod.getMinLodPixels(), 0.0001);
        assertEquals(-1.0, lod.getMaxLodPixels(), 0.0001);
        assertEquals(0.0, lod.getMinFadeExtent(), 0.0001);
        assertEquals(0.0, lod.getMaxFadeExtent(), 0.0001);

    }

    public static void regionExample(final Region region)
    {
        assertEquals(50.625, region.getLatLonAltBox().getNorth(), 0.0001);
        assertEquals(45.0, region.getLatLonAltBox().getSouth(), 0.0001);
        assertEquals(28.125, region.getLatLonAltBox().getEast(), 0.0001);
        assertEquals(22.5, region.getLatLonAltBox().getWest(), 0.0001);
        assertEquals(10.0, region.getLatLonAltBox().getMinAltitude(), 0.0001);
        assertEquals(50.0, region.getLatLonAltBox().getMaxAltitude(), 0.0001);

        assertEquals(128.0, region.getLod().getMinLodPixels(), 0.0001);
        assertEquals(1024.0, region.getLod().getMaxLodPixels(), 0.0001);
        assertEquals(128.0, region.getLod().getMinFadeExtent(), 0.0001);
        assertEquals(128.0, region.getLod().getMaxFadeExtent(), 0.0001);
    }

    public static void schema(final Schema schema)
    {
        assertEquals("ID", schema.getId());
        assertEquals("string", schema.getName());
        assertEquals("string", schema.getSimpleField().get(0).getType());
        assertEquals("string", schema.getSimpleField().get(0).getName());
        assertEquals("...", schema.getSimpleField().get(0).getDisplayName());
    }

    public static void schemaExample(final Kml kml)
    {
        final Schema schema = ((Document) kml.getFeature()).getSchema().get(0);
        assertEquals("TrailHeadTypeId", schema.getId());
        assertEquals("TrailHeadType", schema.getName());

        assertEquals("string", schema.getSimpleField().get(0).getType());
        assertEquals("TrailHeadName", schema.getSimpleField().get(0).getName());
        assertEquals("<![CDATA[<b>Trail Head Name</b>]]>", schema.getSimpleField().get(0).getDisplayName());

        assertEquals("double", schema.getSimpleField().get(1).getType());
        assertEquals("TrailLength", schema.getSimpleField().get(1).getName());
        assertEquals("<![CDATA[<i>The length in miles</i>]]>", schema.getSimpleField().get(1).getDisplayName());

        assertEquals("int", schema.getSimpleField().get(2).getType());
        assertEquals("ElevationGain", schema.getSimpleField().get(2).getName());
        assertEquals("<![CDATA[<i>change in altitude</i>]]>", schema.getSimpleField().get(2).getDisplayName());

    }

    @SuppressWarnings("deprecation")
    public static void screenOverlay(final ScreenOverlay screenoverlay)
    {
        // <!-- inherited from Feature element -->
        assertEquals("...", screenoverlay.getName());
        assertTrue(screenoverlay.isVisibility());
        assertFalse(screenoverlay.isOpen());
        assertNotNull(screenoverlay.getAtomAuthor());
        assertNotNull(screenoverlay.getAtomLink());
        assertEquals("...", screenoverlay.getAddress());
        assertNotNull(screenoverlay.getXalAddressDetails());
        assertEquals("...", screenoverlay.getPhoneNumber());
        assertEquals(2, screenoverlay.getSnippet().getMaxLines());
        assertEquals("...", screenoverlay.getSnippet().getValue());
        assertNotNull(screenoverlay.getAbstractView());
        assertNotNull(screenoverlay.getTimePrimitive());
        assertEquals("...", screenoverlay.getStyleUrl());
        assertNotNull(screenoverlay.getStyleSelector());
        assertNotNull(screenoverlay.getRegion());
        assertNotNull(screenoverlay.getMetadata());
        assertNotNull(screenoverlay.getExtendedData());

        // <!-- inherited from Overlay element -->
        assertEquals("ffffffff", screenoverlay.getColor());
        assertEquals(0, screenoverlay.getDrawOrder());
        assertEquals("...", screenoverlay.getIcon().getHref());

        // <!-- specific to ScreenOverlay -->
        assertEquals(0.5, screenoverlay.getOverlayXY().getX(), 0.0001);
        assertEquals(0.5, screenoverlay.getOverlayXY().getY(), 0.0001);
        assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getXunits());
        assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getYunits());

        assertEquals(0.5, screenoverlay.getScreenXY().getX(), 0.0001);
        assertEquals(0.5, screenoverlay.getScreenXY().getY(), 0.0001);
        assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getXunits());
        assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getYunits());

        assertEquals(0.5, screenoverlay.getSize().getX(), 0.0001);
        assertEquals(0.5, screenoverlay.getSize().getY(), 0.0001);
        assertEquals(Units.FRACTION, screenoverlay.getSize().getXunits());
        assertEquals(Units.FRACTION, screenoverlay.getSize().getYunits());

        assertEquals(0, screenoverlay.getRotation(), 0.0001);

    }

    public static void screenOverlayExample(final ScreenOverlay screenoverlay)
    {
        assertEquals("khScreenOverlay756", screenoverlay.getId());
        assertEquals("Simple crosshairs", screenoverlay.getName());
        assertEquals("This screen overlay uses fractional positioning to put the image in the exact center of the screen", screenoverlay
                .getDescription());

        assertEquals("http://myserver/myimage.jpg", screenoverlay.getIcon().getHref());

        // <!-- specific to ScreenOverlay -->
        assertEquals(0.5, screenoverlay.getOverlayXY().getX(), 0.0001);
        assertEquals(0.5, screenoverlay.getOverlayXY().getY(), 0.0001);
        assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getXunits());
        assertEquals(Units.FRACTION, screenoverlay.getOverlayXY().getYunits());

        assertEquals(0.5, screenoverlay.getScreenXY().getX(), 0.0001);
        assertEquals(0.5, screenoverlay.getScreenXY().getY(), 0.0001);
        assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getXunits());
        assertEquals(Units.FRACTION, screenoverlay.getScreenXY().getYunits());

        assertEquals(39.37878630116985, screenoverlay.getRotation(), 0.0001);

        assertEquals(0, screenoverlay.getSize().getX(), 0.0001);
        assertEquals(0, screenoverlay.getSize().getY(), 0.0001);
        assertEquals(Units.PIXELS, screenoverlay.getSize().getXunits());
        assertEquals(Units.PIXELS, screenoverlay.getSize().getYunits());
    }

    public static void style(final Style style)
    {
        assertEquals("ID", style.getId());

        // <!-- specific to Style -->
        assertNotNull(style.getIconStyle());
        assertNotNull(style.getLabelStyle());
        assertNotNull(style.getLineStyle());
        assertNotNull(style.getPolyStyle());
        assertNotNull(style.getBalloonStyle());
        assertNotNull(style.getListStyle());
    }

    public static void styleExample(final Kml kml)
    {
        final Style style = ((Style) kml.getFeature().getStyleSelector().get(0));
        assertEquals("myDefaultStyles", style.getId());

        assertEquals("a1ff00ff", style.getIconStyle().getColor());
        assertEquals(1.399999976158142, style.getIconStyle().getScale(), 0.0001);
        assertEquals("http://myserver.com/icon.jpg", style.getIconStyle().getIcon().getHref());

        assertEquals("7fffaaff", style.getLabelStyle().getColor());
        assertEquals(1.5, style.getLabelStyle().getScale(), 0.0001);

        assertEquals("ff0000ff", style.getLineStyle().getColor());
        assertEquals(15.0, style.getLineStyle().getWidth(), 0.0001);

        assertEquals("7f7faaaa", style.getPolyStyle().getColor());
        assertEquals(ColorMode.RANDOM, style.getPolyStyle().getColorMode());

        final Placemark p1 = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("Google Earth - New Polygon", p1.getName());
        assertEquals("Here is some descriptive text", p1.getDescription());
        assertEquals("#myDefaultStyles", p1.getStyleUrl());

        final Placemark p2 = (Placemark) ((Document) kml.getFeature()).getFeature().get(1);
        assertEquals("Google Earth - New Path", p2.getName());
        assertEquals("#myDefaultStyles", p2.getStyleUrl());

    }

    public static void styleMap(final StyleMap stylemap)
    {
        assertEquals("ID", stylemap.getId());

        // <!-- extends StyleSelector -->
        // <!-- elements specific to StyleMap -->
        assertEquals("ID", stylemap.getPair().get(0).getId());
        assertEquals(StyleState.NORMAL, stylemap.getPair().get(0).getKey());
        assertEquals("...", stylemap.getPair().get(0).getStyleUrl());

    }

    public static void styleMapPair(final Pair pair)
    {
        assertEquals(StyleState.NORMAL, pair.getKey());
        assertEquals("http://myserver.com/populationProject.xml#example_style_off", pair.getStyleUrl());
    }

    public static void styleMapExample(final Kml kml)
    {
        assertEquals("StyleMap.kml", kml.getFeature().getName());
        assertTrue(kml.getFeature().isOpen());

        final Style style1 = ((Style) kml.getFeature().getStyleSelector().get(0));
        assertEquals("normalState", style1.getId());
        assertEquals(1.0, style1.getIconStyle().getScale(), 0.0001);
        assertEquals("http://maps.google.com/mapfiles/kml/pal3/icon55.png", style1.getIconStyle().getIcon().getHref());
        assertEquals(1.0, style1.getLabelStyle().getScale(), 0.0001);

        final Style style2 = ((Style) kml.getFeature().getStyleSelector().get(1));
        assertEquals("highlightState", style2.getId());
        assertEquals(1.1, style2.getIconStyle().getScale(), 0.0001);
        assertEquals("http://maps.google.com/mapfiles/kml/pal3/icon60.png", style2.getIconStyle().getIcon().getHref());
        assertEquals(1.1, style2.getLabelStyle().getScale(), 0.0001);
        assertEquals("ff0000c0", style2.getLabelStyle().getColor());

        final StyleMap stylemap = (StyleMap) kml.getFeature().getStyleSelector().get(2);
        assertEquals("styleMapExample", stylemap.getId());
        assertEquals(StyleState.NORMAL, stylemap.getPair().get(0).getKey());
        assertEquals("#normalState", stylemap.getPair().get(0).getStyleUrl());
        assertEquals(StyleState.HIGHLIGHT, stylemap.getPair().get(1).getKey());
        assertEquals("#highlightState", stylemap.getPair().get(1).getStyleUrl());

        final Placemark placemark = (Placemark) ((Document) kml.getFeature()).getFeature().get(0);
        assertEquals("StyleMap example", placemark.getName());
        assertEquals("#styleMapExample", placemark.getStyleUrl());
        assertEquals(new Coordinate(-122.368987, 37.817634, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));

    }

    public static void timeSpan(final TimeSpan timespan)
    {
        assertEquals("...", timespan.getBegin());
        assertEquals("...", timespan.getEnd());

    }

    public static void timeStamp(final TimeStamp timestamp)
    {
        assertEquals("...", timestamp.getWhen());
    }

    public static void updateChange(final NetworkLinkControl networklink)
    {
        final Update update = networklink.getUpdate();
        assertEquals("http://www/~sam/January14Data/Point.kml", update.getTargetHref());

        final Change change = (Change) update.getCreateOrDeleteOrChange().get(0);
        final Point point = (Point) change.getAbstractObject().get(0);
        assertEquals("point123", point.getTargetId());
        assertEquals(new Coordinate(-95.48, 40.43, 0.0), point.getCoordinates().get(0));

    }

    public static void updateCreate(final Update update)
    {
        assertEquals("http://myserver.com/Point.kml", update.getTargetHref());

        final Create create = (Create) update.getCreateOrDeleteOrChange().get(0);
        final Document document = (Document) create.getContainer().get(0);
        assertEquals("region24", document.getTargetId());
        final Placemark placemark = (Placemark) document.getFeature().get(0);
        assertEquals("placemark891", placemark.getId());
        assertEquals(new Coordinate(-95.48, 40.43, 0.0), ((Point) placemark.getGeometry()).getCoordinates().get(0));
    }

    public static void updateDelete(final Update update)
    {
        assertEquals("http://www.foo.com/Point.kml", update.getTargetHref());

        final Delete delete = (Delete) update.getCreateOrDeleteOrChange().get(0);
        final Placemark placemark = (Placemark) delete.getFeature().get(0);
        assertEquals("pa3556", placemark.getTargetId());
    }

}
