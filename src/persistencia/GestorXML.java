package persistencia;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.ParadaG;
import model.ParadaV;
import model.PuntInformacio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import principal.GestorMercatsException;
import model.Mercat;

/**
 *
 * @author fta
 */
public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private Mercat mercat;

    public Document getDoc() {
        return doc;
    }

    public Mercat getMercat() {
        return mercat;
    }

    @Override
    public void desarMercat(String nomFitxer, Mercat mercat) throws GestorMercatsException {
        construeixModel(mercat);
        desarModel(nomFitxer);
    }

    @Override
    public void carregarMercat(String nomFitxer) throws GestorMercatsException {
        carregarFitxer(nomFitxer);
        llegirFitxerMercat();
    }

    /*Paràmetres: Mercat a partir de la qual volem construir el model
     *
     *Acció: 
     * - Llegir els atributs de l'objecte mercat passat per paràmetre per construir 
     *   un model (document XML) sobre el Document doc (atribut de GestorXML).
     *
     * - L'arrel del document XML és "mercat". Aquesta arrel, l'heu d'afegir a doc. Un 
     *   cop fet això, heu de recórrer l'ArrayList locals del mercat passat per
     *   paràmetre i per a cada local, afegir un fill a doc. Cada fill tindrà
     *   com atributs els atributs de l'objecte (codi, nom, tipus, etc.)
     *
     * - Si heu de gestionar alguna excepció relacionada amb la construcció del model,
     *   heu de llençar una excepció de tipus GestorMercatsException amb codi 
     *   "GestorXML.model".
     *
     *Retorn: cap
     */
    public void construeixModel(Mercat mercat) throws GestorMercatsException {

        //Es construeix el document model
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Element fill;

        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new GestorMercatsException("GestorXML.model");
        }

        this.doc = (Document) builder.newDocument();

        //Element arrel
        Element arrel = doc.createElement("mercat");
        arrel.setAttribute("codi", String.valueOf(mercat.getCodi()));
        arrel.setAttribute("nom", mercat.getNom());
        arrel.setAttribute("adreca", mercat.getAdreca());
        doc.appendChild(arrel);

        for (int i = 0; i < mercat.getLocals().size(); i++) {

            if (mercat.getLocals().get(i) instanceof PuntInformacio) {

                fill = doc.createElement("puntInformacio");

                fill.setAttribute("codi", ((PuntInformacio) mercat.getLocals().get(i)).getCodi());
                fill.setAttribute("nomResponsable", ((PuntInformacio) mercat.getLocals().get(i)).getNomResponsable());
                fill.setAttribute("anyActual", String.valueOf(((PuntInformacio) mercat.getLocals().get(i)).getAnyActual()));
                fill.setAttribute("quantitatPersones", String.valueOf(((PuntInformacio) mercat.getLocals().get(i)).getQuantitatPersones()));

                arrel.appendChild(fill);

            } else if (mercat.getLocals().get(i) instanceof ParadaV) {

                fill = doc.createElement("paradaV");

                fill.setAttribute("codi", ((ParadaV) mercat.getLocals().get(i)).getCodi());
                fill.setAttribute("nom", ((ParadaV) mercat.getLocals().get(i)).getNom());
                fill.setAttribute("tipus", ((ParadaV) mercat.getLocals().get(i)).getTipus());

                arrel.appendChild(fill);

            } else if (mercat.getLocals().get(i) instanceof ParadaG) {

                fill = doc.createElement("paradaG");

                fill.setAttribute("codi", ((ParadaG) mercat.getLocals().get(i)).getCodi());
                fill.setAttribute("nom", ((ParadaG) mercat.getLocals().get(i)).getNom());
                fill.setAttribute("tipus", ((ParadaG) mercat.getLocals().get(i)).getTipus());
                fill.setAttribute("teTaula", String.valueOf(((ParadaG) mercat.getLocals().get(i)).isTeTaula()));

                arrel.appendChild(fill);

            }
        }
    }

    public void desarModel(String nomFitxer) throws GestorMercatsException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            throw new GestorMercatsException("GestorXML.desar");
        }
    }

    public void carregarFitxer(String nomFitxer) throws GestorMercatsException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            throw new GestorMercatsException("GestorXML.carrega");
        }
    }

    /*Paràmetres: cap
     *
     *Acció: 
     * - Llegir el fitxer del vostre sistema i carregar-lo sobre l'atribut doc, per
     *   assignar valors als atributs del mercat i la resta d'objectes que formen les
     *   locals del mercat.
     *    
     * - Primer heu de crear l'objecte de la classe Mercat a partir de l'arrel del document XML
     *   per després recórrer el doc i per cada fill, afegir un objecte a l'atribut locals 
     *   del mercat mitjançant el mètode escaient de la classe Mercat. Recordeu que com l'arrel conté
     *   els atributs codi, nom i adreça del mercat, al fer Element arrel = doc.getDocumentElement(); 
     *   ja podeu crear l'objecte de la classe.
     *
     *Retorn: cap
     */
    private void llegirFitxerMercat() throws GestorMercatsException {

        String local, codi, nom, tipus;
        int anyActual,quantitatPersones;
        boolean teTaules;

        Element arrel = doc.getDocumentElement();

        //Es crea l'objecte de la classe Mercat
        mercat = new Mercat(Integer.parseInt(arrel.getAttribute("codi")), arrel.getAttribute("nom"), arrel.getAttribute("adreca"));

        //Recorregut de nodes fill d'un element       
        NodeList llistaFills = arrel.getChildNodes();

        for (int i = 0; i < llistaFills.getLength(); i++) {

            Node fill = llistaFills.item(i);

            if (fill.getNodeType() == Node.ELEMENT_NODE) {

                local = fill.getNodeName();

                switch (local) {

                    case "puntInformacio":

                        codi = ((Element) fill).getAttribute("codi");
                        nom = ((Element) fill).getAttribute("nom");
                        anyActual = Integer.parseInt(((Element) fill).getAttribute("anyActual"));
                        quantitatPersones = Integer.parseInt(((Element) fill).getAttribute("quantitatPersones"));

                        mercat.addPuntInformacio(new PuntInformacio(codi, nom, anyActual, quantitatPersones));

                        break;

                    case "paradaV":

                        codi = ((Element) fill).getAttribute("codi");
                        nom = ((Element) fill).getAttribute("nom");
                        tipus = ((Element) fill).getAttribute("tipus");

                        mercat.addParadaV(new ParadaV(codi, nom, tipus));

                        break;

                    default: //ParadaG

                        codi = ((Element) fill).getAttribute("codi");
                        nom = ((Element) fill).getAttribute("nom");
                        tipus = ((Element) fill).getAttribute("tipus");
                        teTaules = Boolean.parseBoolean(((Element) fill).getAttribute("teTaules"));

                        mercat.addParadaG(new ParadaG(codi, nom, tipus, teTaules));

                        break;

                }

            }
        }
    }
}
