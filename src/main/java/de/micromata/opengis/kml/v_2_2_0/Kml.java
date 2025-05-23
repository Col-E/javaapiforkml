
package de.micromata.opengis.kml.v_2_2_0;

import de.micromata.opengis.kml.v_2_2_0.gx.Tour;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * {@code <kml>}
 * <p>
 * {@code <kml xmlns="http://www.opengis.net/kml/2.2"> <NetworkLinkControl> ... </NetworkLinkControl>
 * <!-- 0 or 1 Feature elements --> </kml> }
 * </p>
 * <p>
 * A basic {@code <kml>} element contains 0 or 1 Feature and 0 or 1 NetworkLinkControl: 
 * </p>
 * <p>
 * The {@code <kml>} element may also include the namespace for any external XML schemas that 
 * are referenced within the file. 
 * </p>
 * <p>
 * The root element of a KML file. This element is required. It follows the xml declaration 
 * at the beginning of the file. The hint attribute is used as a signal to Google Earth 
 * to display the file as celestial data. 
 * </p>
 * 
 * Syntax: 
 * <pre>&lt;kml xmlns="http://www.opengis.net/kml/2.2" <span>hint="target=sky"</span>&gt; ... &lt;/kml&gt;</pre>
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmlType", propOrder = {
    "networkLinkControl",
    "feature",
    "kmlSimpleExtension",
    "kmlObjectExtension"
})
@XmlRootElement(name = "kml", namespace = "http://www.opengis.net/kml/2.2")
public class Kml implements Cloneable
{

    /**
     * {@code <NetworkLinkControl>}
     * <p>
     * Controls the behavior of files fetched by a NetworkLink. 
     * </p>
     * 
     * Syntax: 
     * <pre><strong>&lt;NetworkLinkControl&gt;</strong>
     *   &lt;minRefreshPeriod&gt;0&lt;/minRefreshPeriod&gt;           &lt;!-- float --&gt;
     *   <span class="style2">&lt;maxSessionLength&gt;-1&lt;/maxSessionLength&gt;          &lt;!-- float --&gt; </span>
     *   &lt;cookie&gt;<em>...</em>&lt;/cookie&gt;                             &lt;!-- string --&gt;                             
     *   &lt;message&gt;<em>...</em>&lt;/message&gt;                           &lt;!-- string --&gt;
     *   &lt;linkName&gt;<em>...</em>&lt;/linkName&gt;                         &lt;!-- string --&gt;                          
     *   &lt;linkDescription&gt;<em>...</em>&lt;/linkDescription&gt;           &lt;!-- string --&gt;              
     *   &lt;linkSnippet maxLines="2"&gt;<em>...</em>&lt;/linkSnippet&gt;      &lt;!-- string --&gt;                      
     *   &lt;expires&gt;...&lt;/expires&gt;                           &lt;!-- kml:dateTime --&gt;
     *   &lt;Update&gt;...&lt;/Update&gt;                             &lt;!-- Change,Create,Delete --&gt;
     *   <span><em>&lt;AbstractView&gt;...&lt;/AbstractView&gt;</em>                 &lt;!-- LookAt <em>or</em> Camera --&gt;</span>
     * <strong>&lt;/NetworkLinkControl&gt;</strong></pre>
     * 
     * See Also: 
     * NetworkLink
     * Update
     * 
     * 
     * 
     */
    @XmlElement(name = "NetworkLinkControl")
    protected NetworkLinkControl networkLinkControl;
    /**
     * {@code <Feature>}
     * <p>
     * This is an abstract element and cannot be used directly in a KML file. The following 
     * diagram shows how some of a Feature's elements appear in Google Earth. 
     * </p>
     * 
     * Syntax: 
     * <pre>&lt;!-- abstract element; do not create --&gt;
     * <strong>&lt;!--<em> Feature</em> id="ID" --&gt;</strong>                &lt;!-- Document,Folder,
     *                                              NetworkLink,Placemark,
     *                                              GroundOverlay,PhotoOverlay,ScreenOverlay --&gt;
     *   &lt;name&gt;<em>...</em>&lt;/name&gt;                      &lt;!-- string --&gt;
     *   &lt;visibility&gt;1&lt;/visibility&gt;            &lt;!-- boolean --&gt;
     *   &lt;open&gt;0&lt;/open&gt;                        &lt;!-- boolean --&gt;
     *   <span>&lt;atom:author&gt;...&lt;atom:author&gt;         &lt;!-- xmlns:atom --&gt;
     *   &lt;atom:link&gt;...&lt;/atom:link&gt;</span><span>            &lt;!-- xmlns:atom --&gt;</span>
     *   &lt;address&gt;<em>...</em>&lt;/address&gt;                &lt;!-- string --&gt;
     *   &lt;xal:AddressDetails&gt;...&lt;/xal:AddressDetails&gt;  &lt;!-- xmlns:xal --&gt;<br>  &lt;phoneNumber&gt;...&lt;/phoneNumber&gt;        &lt;!-- string --&gt;<br>  &lt;Snippet maxLines="2"&gt;<em>...</em>&lt;/Snippet&gt;   &lt;!-- string --&gt;
     *   &lt;description&gt;<em>...</em>&lt;/description&gt;        &lt;!-- string --&gt;
     *   <span><em>&lt;AbstractView&gt;...&lt;/AbstractView&gt;</em>      &lt;!-- Camera <em>or</em> LookAt --&gt;</span>
     *   &lt;<em>TimePrimitive</em>&gt;...&lt;/<em>TimePrimitive</em>&gt;    &lt;!-- TimeStamp or TimeSpan --&gt;
     *   &lt;styleUrl&gt;<em>...</em>&lt;/styleUrl&gt;              &lt;!-- anyURI --&gt;
     *   &lt;<em>StyleSelector&gt;...&lt;/StyleSelector&gt;</em>
     *   &lt;Region&gt;...&lt;/Region&gt;
     *   <span>&lt;Metadata&gt;...&lt;/Metadata&gt;              &lt;!-- deprecated in KML 2.2 --&gt;
     *   &lt;ExtendedData&gt;...&lt;/ExtendedData&gt;      &lt;!-- new in KML 2.2 --&gt;<br></span>&lt;-- /<em>Feature</em> --&gt;</pre>
     * 
     * Extends: 
     * <br/>
     * 
     * Extended By: 
     *
     *
     *
     *
     *
     * 
     * 
     * 
     */
    @XmlElementRef(name = "AbstractFeatureGroup", namespace = "http://www.opengis.net/kml/2.2", required = false)
    protected Feature feature;
    @XmlElement(name = "KmlSimpleExtensionGroup")
    @XmlSchemaType(name = "anySimpleType")
    protected List<Object> kmlSimpleExtension;
    /**
     * {@code <Object>}
     * <p>
     * This is an abstract base class and cannot be used directly in a KML file. It provides 
     * the id attribute, which allows unique identification of a KML element, and the targetId 
     * attribute, which is used to reference objects that have already been loaded into 
     * Google Earth. The id attribute must be assigned if the Update mechanism is to 
     * be used. 
     * </p>
     * 
     * Syntax: 
     * <pre>&lt;!-- abstract element; do not create --&gt;<strong>
     * &lt;!-- <em>Object</em> id="ID" targetId="NCName" --&gt;
     * &lt;!-- /<em>Object</em>&gt; --&gt;</strong></pre>
     * 
     * 
     * 
     */
    @XmlElement(name = "KmlObjectExtensionGroup")
    protected List<AbstractObject> kmlObjectExtension;
    @XmlAttribute(name = "hint")
    protected String hint;

    private transient Marshaller marshaller = null;
    private transient int missingNameCounter = (1);

    private static JAXBContext jaxbContext = null;
    private final static String SCHEMA_LOCATION = "src/main/resources/schema/ogckml/ogckml22.xsd";

    public Kml() {
        super();
    }

    /**
     *
     * 
     * @return
     *     possible object is
     *     {@link NetworkLinkControl}
     *     
     */
    public NetworkLinkControl getNetworkLinkControl() {
        return networkLinkControl;
    }

    /**
     *
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkLinkControl}
     *     
     */
    public void setNetworkLinkControl(NetworkLinkControl value) {
        this.networkLinkControl = value;
    }

    /**
     *
     * 
     * @return
     *     possible object is
     *     {@code <}{@link Container}{@code >}
     *     {@code <}{@link GroundOverlay}{@code >}
     *     {@code <}{@link NetworkLink}{@code >}
     *     {@code <}{@link Folder}{@code >}
     *     {@code <}{@link PhotoOverlay}{@code >}
     *     {@code <}{@link Document}{@code >}
     *     {@code <}{@link Tour}{@code >}
     *     {@code <}{@link ScreenOverlay}{@code >}
     *     {@code <}{@link Feature}{@code >}
     *     {@code <}{@link Placemark}{@code >}
     *     {@code <}{@link Overlay}{@code >}
     *     
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     *
     * 
     * @param value
     *     allowed object is
     *     {@code <}{@link Container}{@code >}
     *     {@code <}{@link GroundOverlay}{@code >}
     *     {@code <}{@link NetworkLink}{@code >}
     *     {@code <}{@link Folder}{@code >}
     *     {@code <}{@link PhotoOverlay}{@code >}
     *     {@code <}{@link Document}{@code >}
     *     {@code <}{@link Tour}{@code >}
     *     {@code <}{@link ScreenOverlay}{@code >}
     *     {@code <}{@link Feature}{@code >}
     *     {@code <}{@link Placemark}{@code >}
     *     {@code <}{@link Overlay}{@code >}
     *     
     */
    public void setFeature(Feature value) {
        this.feature = value;
    }

    /**
     *
     * 
     */
    public List<Object> getKmlSimpleExtension() {
        if (kmlSimpleExtension == null) {
            kmlSimpleExtension = new ArrayList<>();
        }
        return this.kmlSimpleExtension;
    }

    /**
     *
     * 
     */
    public List<AbstractObject> getKmlObjectExtension() {
        if (kmlObjectExtension == null) {
            kmlObjectExtension = new ArrayList<>();
        }
        return this.kmlObjectExtension;
    }

    /**
     *
     * 
     * @return
     *     possible object is
     *     {@link String}
     *     
     */
    public String getHint() {
        return hint;
    }

    /**
     *
     * 
     * @param value
     *     allowed object is
     *     {@link String}
     *     
     */
    public void setHint(String value) {
        this.hint = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = ((prime*result)+((networkLinkControl == null)? 0 :networkLinkControl.hashCode()));
        result = ((prime*result)+((feature == null)? 0 :feature.hashCode()));
        result = ((prime*result)+((kmlSimpleExtension == null)? 0 :kmlSimpleExtension.hashCode()));
        result = ((prime*result)+((kmlObjectExtension == null)? 0 :kmlObjectExtension.hashCode()));
        result = ((prime*result)+((hint == null)? 0 :hint.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Kml)) {
            return false;
        }
        Kml other = ((Kml) obj);
        if (networkLinkControl == null) {
            if (other.networkLinkControl!= null) {
                return false;
            }
        } else {
            if (!networkLinkControl.equals(other.networkLinkControl)) {
                return false;
            }
        }
        if (feature == null) {
            if (other.feature!= null) {
                return false;
            }
        } else {
            if (!feature.equals(other.feature)) {
                return false;
            }
        }
        if (kmlSimpleExtension == null) {
            if (other.kmlSimpleExtension!= null) {
                return false;
            }
        } else {
            if (!kmlSimpleExtension.equals(other.kmlSimpleExtension)) {
                return false;
            }
        }
        if (kmlObjectExtension == null) {
            if (other.kmlObjectExtension!= null) {
                return false;
            }
        } else {
            if (!kmlObjectExtension.equals(other.kmlObjectExtension)) {
                return false;
            }
        }
        if (hint == null) {
            return other.hint == null;
        } else {
            return hint.equals(other.hint);
        }
    }

    /**
     * Creates a new instance of {@link NetworkLinkControl} and set it to networkLinkControl.
     * <br/>
     * This method is a short version for:
     * {@code
     * NetworkLinkControl networkLinkControl = new NetworkLinkControl();
     * this.setNetworkLinkControl(networkLinkControl); }
     * 
     * 
     */
    public NetworkLinkControl createAndSetNetworkLinkControl() {
        NetworkLinkControl newValue = new NetworkLinkControl();
        this.setNetworkLinkControl(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link Tour} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * Tour tour = new Tour();
     * this.setFeature(tour); }
     * 
     * 
     */
    public Tour createAndSetTour() {
        Tour newValue = new Tour();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link ScreenOverlay} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * ScreenOverlay screenOverlay = new ScreenOverlay();
     * this.setFeature(screenOverlay); }
     * 
     * 
     */
    public ScreenOverlay createAndSetScreenOverlay() {
        ScreenOverlay newValue = new ScreenOverlay();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link PhotoOverlay} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * PhotoOverlay photoOverlay = new PhotoOverlay();
     * this.setFeature(photoOverlay); }
     * 
     * 
     */
    public PhotoOverlay createAndSetPhotoOverlay() {
        PhotoOverlay newValue = new PhotoOverlay();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link GroundOverlay} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * GroundOverlay groundOverlay = new GroundOverlay();
     * this.setFeature(groundOverlay); }
     * 
     * 
     */
    public GroundOverlay createAndSetGroundOverlay() {
        GroundOverlay newValue = new GroundOverlay();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link NetworkLink} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * NetworkLink networkLink = new NetworkLink();
     * this.setFeature(networkLink); }
     * 
     * 
     */
    public NetworkLink createAndSetNetworkLink() {
        NetworkLink newValue = new NetworkLink();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link Folder} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * Folder folder = new Folder();
     * this.setFeature(folder); }
     * 
     * 
     */
    public Folder createAndSetFolder() {
        Folder newValue = new Folder();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link Document} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * Document document = new Document();
     * this.setFeature(document); }
     * 
     * 
     */
    public Document createAndSetDocument() {
        Document newValue = new Document();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     * Creates a new instance of {@link Placemark} and set it to feature.
     * <br/>
     * This method is a short version for:
     * {@code
     * Placemark placemark = new Placemark();
     * this.setFeature(placemark); }
     * 
     * 
     */
    public Placemark createAndSetPlacemark() {
        Placemark newValue = new Placemark();
        this.setFeature(newValue);
        return newValue;
    }

    /**
     *
     * 
     * @param kmlSimpleExtension
     *     Objects of the following type are allowed in the list: {@link Object}
     */
    public void setKmlSimpleExtension(final List<Object> kmlSimpleExtension) {
        this.kmlSimpleExtension = kmlSimpleExtension;
    }

    /**
     * add a value to the kmlSimpleExtension property collection
     * 
     * @param kmlSimpleExtension
     *     Objects of the following type are allowed in the list: {@link Object}
     * @return
     *     true (as general contract of Collection.add). 
     */
    public Kml addToKmlSimpleExtension(final Object kmlSimpleExtension) {
        this.getKmlSimpleExtension().add(kmlSimpleExtension);
        return this;
    }

    /**
     *
     * 
     * @param kmlObjectExtension
     *     Objects of the following type are allowed in the list: {@link AbstractObject}
     */
    public void setKmlObjectExtension(final List<AbstractObject> kmlObjectExtension) {
        this.kmlObjectExtension = kmlObjectExtension;
    }

    /**
     * add a value to the kmlObjectExtension property collection
     * 
     * @param kmlObjectExtension
     *     Objects of the following type are allowed in the list: {@link AbstractObject}
     * @return
     *     true (as general contract of Collection.add). 
     */
    public Kml addToKmlObjectExtension(final AbstractObject kmlObjectExtension) {
        this.getKmlObjectExtension().add(kmlObjectExtension);
        return this;
    }

    /**
     * fluent setter
     *
     * 
     * @param networkLinkControl
     *     required parameter
     */
    public Kml withNetworkLinkControl(final NetworkLinkControl networkLinkControl) {
        this.setNetworkLinkControl(networkLinkControl);
        return this;
    }

    /**
     * fluent setter
     *
     * 
     * @param feature
     *     required parameter
     */
    public Kml withFeature(final Feature feature) {
        this.setFeature(feature);
        return this;
    }

    /**
     * fluent setter
     *
     * 
     * @param kmlSimpleExtension
     *     required parameter
     */
    public Kml withKmlSimpleExtension(final List<Object> kmlSimpleExtension) {
        this.setKmlSimpleExtension(kmlSimpleExtension);
        return this;
    }

    /**
     * fluent setter
     *
     * 
     * @param kmlObjectExtension
     *     required parameter
     */
    public Kml withKmlObjectExtension(final List<AbstractObject> kmlObjectExtension) {
        this.setKmlObjectExtension(kmlObjectExtension);
        return this;
    }

    /**
     * fluent setter
     *
     * 
     * @param hint
     *     required parameter
     */
    public Kml withHint(final String hint) {
        this.setHint(hint);
        return this;
    }

    /**
     *
     * 
     */
    private static JAXBContext getJaxbContext()
        throws JAXBException
    {
        if (jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance((Kml.class));
        }
        return jaxbContext;
    }

    private Marshaller createMarshaller()
        throws JAXBException
    {
        if (marshaller == null) {
            JAXBContext context = getJaxbContext();
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            decorateMarshaller(context, marshaller);
        }
        return marshaller;
    }

    /**
     * Allow 3rd parties to decorate the marshaller to add additional functionality.
     *
     * @param context Context that created the marshaller.
     * @param marshaller The marshaller to decorate.
     */
    protected void decorateMarshaller(JAXBContext context, Marshaller marshaller)
    {
        // no-op by default
        //
        // Child classes can use this to add additional properties to the marshaller such as NamespacePrefixMapper implementations
        //
        // Example:
        //    marshaller.setProperty("org.glassfish.jaxb.namespacePrefixMapper", new Kml.NameSpaceBeautyfier());
    }

    /**
     * Internal method
     * 
     */
    private void addKmzFile(Kml kmzFile, ZipOutputStream out, boolean mainfile)
        throws IOException
    {
        String fileName = null;
        if (((kmzFile.getFeature() == null)||(kmzFile.getFeature().getName() == null))||(kmzFile.getFeature().getName().isEmpty())) {
            fileName = (("noFeatureNameSet"+ missingNameCounter ++)+".kml");
        } else {
            fileName = kmzFile.getFeature().getName();
            if (!fileName.endsWith(".kml")) {
                fileName += ".kml";
            }
        }
        if (mainfile) {
            fileName = "doc.kml";
        }
        out.putNextEntry(new ZipEntry(URLEncoder.encode(fileName, StandardCharsets.UTF_8)));
        kmzFile.marshal(out);
        out.closeEntry();
    }

    /**
     * Java to KML
     * The object graph is marshalled to an OutputStream object.
     * The object is not saved as a zipped .kmz file.
     *
     * 
     */
    public boolean marshal(final OutputStream outputstream)
        throws FileNotFoundException
    {
        try {
            marshaller = this.createMarshaller();
            marshaller.marshal(this, outputstream);
            return true;
        } catch (JAXBException _x) {
            _x.printStackTrace();
            return false;
        }
    }

    /**
     * Java to KML
     * The object graph is marshalled to a Writer object.
     * The object is not saved as a zipped .kmz file.
     *
     * 
     */
    public boolean marshal(final Writer writer) {
        try {
            marshaller = this.createMarshaller();
            marshaller.marshal(this, writer);
            return true;
        } catch (JAXBException _x) {
            _x.printStackTrace();
            return false;
        }
    }

    /**
     * Java to KML
     * The object graph is marshalled to a Contenthandler object.
     * Useful if  marshaller cis needed to generate CDATA blocks.
     * @see <a href="https://jaxb.dev.java.net/faq/">...</a>
     * @see <a href="http://code.google.com/p/javaapiforkml/issues/detail?id=7">...</a>
     * The object is not saved as a zipped .kmz file.
     *
     * 
     */
    public boolean marshal(final ContentHandler contenthandler) {
        try {
            marshaller = this.createMarshaller();
            marshaller.marshal(this, contenthandler);
            return true;
        } catch (JAXBException _x) {
            _x.printStackTrace();
            return false;
        }
    }

    /**
     * Java to KML
     * The object graph is printed to the console.
     * (Nothing is saved, nor saved. Just printed.)
     * 
     * 
     */
    public boolean marshal() {
        try {
            marshaller = this.createMarshaller();
            marshaller.marshal(this, System.out);
            return true;
        } catch (JAXBException _x) {
            _x.printStackTrace();
            return false;
        }
    }

    /**
     * Java to KML
     * The object graph is marshalled to a File object.
     * The object is not saved as a zipped .kmz file.
     *
     * 
     */
    public boolean marshal(final File filename)
            throws IOException
    {
        try (OutputStream out = new FileOutputStream(filename)) {
            return this.marshal(out);
        }
    }

    public boolean marshalAsKmz(
        @NotNull
        String name, Kml... additionalFiles)
        throws IOException
    {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(name));
        out.setComment("KMZ-file created with Java API for KML. Visit us: https://github.com/micromata/javaapiforkml");
        this.addKmzFile(this, out, true);
        for (Kml kml: additionalFiles) {
            this.addKmzFile(kml, out, false);
        }
        out.close();
        missingNameCounter = 1;
        return false;
    }

    private static boolean validate(final Unmarshaller unmarshaller) {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File(SCHEMA_LOCATION);
            Schema schema = sf.newSchema(schemaFile);
            unmarshaller.setSchema(schema);
            return true;
        } catch (SAXException _x) {
            _x.printStackTrace();
        }
        return false;
    }

    /**
     * KML to Java
     * KML given as a file object is transformed into a graph of Java objects.
     * The boolean value indicates, whether the File object should be validated 
     * automatically during unmarshalling and be checked if the object graph meets 
     * all constraints defined in OGC's KML schema specification.
     * 
     */
    public static Kml unmarshal(final File file, final boolean validate) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance((Kml.class)).createUnmarshaller();
            if (validate) {
                Kml.validate(unmarshaller);
            }
            InputSource input = new InputSource(new FileReader(file));
            SAXSource saxSource = new SAXSource(new NamespaceFilterXMLReader(validate), input);
            return ((Kml) unmarshaller.unmarshal(saxSource));
        } catch (SAXException _x) {
            _x.printStackTrace();
        } catch (ParserConfigurationException _x) {
            _x.printStackTrace();
        } catch (JAXBException _x) {
            _x.printStackTrace();
        } catch (FileNotFoundException _x) {
            _x.printStackTrace();
        }
        return null;
    }

    /**
     * KML to Java
     * KML given as a file object is transformed into a graph of Java objects.
     * Similar to the method: 
     * unmarshal(final File, final boolean) 
     * with the exception that the File object is not validated (boolean is false). 
     * 
     */
    public static Kml unmarshal(final File file) {
        return Kml.unmarshal(file, false);
    }

    /**
     * KML to Java
     * Similar to the other unmarshal methods 
     * 
     * with the exception that it transforms a String into a graph of Java objects. 
     * 
     * 
     */
    public static Kml unmarshal(final String content) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance((Kml.class)).createUnmarshaller();
            InputSource input = new InputSource(new StringReader(content));
            SAXSource saxSource = new SAXSource(new NamespaceFilterXMLReader(false), input);
            return ((Kml) unmarshaller.unmarshal(saxSource));
        } catch (SAXException _x) {
            _x.printStackTrace();
        } catch (ParserConfigurationException _x) {
            _x.printStackTrace();
        } catch (JAXBException _x) {
            _x.printStackTrace();
        }
        return null;
    }

    /**
     * KML to Java
     * Similar to the other unmarshal methods 
     * 
     * with the exception that it transforms a InputStream into a graph of Java objects. 
     * 
     * 
     */
    public static Kml unmarshal(final InputStream content) {
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance((Kml.class)).createUnmarshaller();
            InputSource input = new InputSource(content);
            SAXSource saxSource = new SAXSource(new NamespaceFilterXMLReader(false), input);
            return ((Kml) unmarshaller.unmarshal(saxSource));
        } catch (SAXException _x) {
            _x.printStackTrace();
        } catch (ParserConfigurationException _x) {
            _x.printStackTrace();
        } catch (JAXBException _x) {
            _x.printStackTrace();
        }
        return null;
    }

    /**
     * KMZ to Java
     * Similar to the other unmarshal methods
     * 
     * with the exception that it transforms a KMZ-file into a graph of Java objects. 
     * 
     * 
     */
    public static Kml[] unmarshalFromKmz(
        @NotNull
        File file)
        throws IOException
    {
        Kml[] EMPTY_KML_ARRAY = (new Kml[0]);
        if (!file.getName().endsWith(".kmz")) {
            return EMPTY_KML_ARRAY;
        }
        ZipFile zip = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zip.entries();
        if (!file.exists()) {
            return EMPTY_KML_ARRAY;
        }
        ArrayList<Kml> kmlfiles = new ArrayList<Kml>();
        while (entries.hasMoreElements()) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            if (entry.getName().contains("__MACOSX")||entry.getName().contains(".DS_STORE")) {
                continue;
            }
            String entryName = URLDecoder.decode(entry.getName(), StandardCharsets.UTF_8);
            if (!entryName.endsWith(".kml")) {
                continue;
            }
            InputStream in = zip.getInputStream(entry);
            Kml unmarshal = Kml.unmarshal(in);
            kmlfiles.add(unmarshal);
        }
        zip.close();
        return kmlfiles.toArray(EMPTY_KML_ARRAY);
    }

    @Override
    public Kml clone() {
        Kml copy;
        try {
            copy = ((Kml) super.clone());
        } catch (CloneNotSupportedException _x) {
            throw new InternalError((_x.toString()));
        }
        copy.networkLinkControl = ((networkLinkControl == null)?null:((NetworkLinkControl) networkLinkControl.clone()));
        copy.feature = ((feature == null)?null:((Feature ) feature.clone()));
        copy.kmlSimpleExtension = new ArrayList<Object>((getKmlSimpleExtension().size()));
        copy.kmlSimpleExtension.addAll(kmlSimpleExtension);
        copy.kmlObjectExtension = new ArrayList<AbstractObject>((getKmlObjectExtension().size()));
        for (AbstractObject iter: kmlObjectExtension) {
            copy.kmlObjectExtension.add(iter.clone());
        }
        return copy;
    }
}
