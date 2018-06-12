package general;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.SAXException;

public class validador {
    
    String errorConforme = "";
    String errorValido = "";

    /*Constructor*/
    public validador() {
    }
    
    /*Getters*/

    public String getErrorConforme() {
        return errorConforme;
    }

    public String getErrorValido() {
        return errorValido;
    }
    
    
    /* Método que comprueba la conformidad con XML
        Indica si un xml está bien formado
        sURL la ruta donde se encuentra el archivo xml
    */
    public void checkConforme(String sURL)
    throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();//Simple Appi XML
        builder.build(sURL);
    }
    
    /* Método que comprueba la validez con el DTD o esquema externo
        sURL la ruta donde se encuentra el archivo xml
        esquemaURL la ruta donde se encuentra el archivo xsd
        Indica si esta bien conformado y esta regido por las reglas del DTD o de su esquema
    */
    public void checkValido(String sURL, String esquemaURL)
    throws JDOMException, IOException, SAXException {
        //URL esquemaFile = new URL(esquemaURL);//archivo esquema xsd
        /*  El objecto Source contiene la informacion necesaria para actuar como
            fuente de entrada
            StreamSource actúa como un poseedor para una transformación de fuente
            de un flujo de XML, recibe como parámetro un archivo
        */
        Source esquemaFile = new StreamSource(new File(esquemaURL));//archivo esquemaUsuario.xsd
        Source xmlFile = new StreamSource(new File(sURL));//archivo bd.xml
        /*  SchemaFactory crea objetos de tipo esquema, funciona como un compilador de esquemas
            Lee representaciones de esquemas externas y las prepara para la validacion
            La constante que se define en SchemaFactory es http://www.w3.org/2001/XMLSchema 
            que define a los XSD's    
        */    
            SchemaFactory sF = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sF.newSchema(esquemaFile);
        /*  Se crea un validador para dicho esquema */
            Validator validator = schema.newValidator();
        /*  Se valida el archivo xml con su esquema correspondiente */
            validator.validate(xmlFile);
    }
    
    /*  Método que permite checar si un xml está bien formado y es válido 
        de acuerdo a su respectivo esquema
        nombreArchivo = archivo xml
        nombreEsquema = archivo xsd
    */
    public boolean checkAll(String nombreArchivo, String nombreEsquema, PrintWriter out)
    throws JDOMException, IOException, SAXException {
        boolean esValido=true,esBienFormado=true;
        if (nombreArchivo.length()==0){
                out.println("Utilizacion: java JDOMValidador URL");
                out.println("<br />");//salto de linea
        }
        else{  
            try{
                try {
                // Documento conforme??
                checkConforme(nombreArchivo);
                }
                // Indicacion de un fichero mal formado
                catch (JDOMException e){
                    esBienFormado=false;
                    out.println(nombreArchivo + " NO esta bien formado");
                    out.println("<br />");//salto de linea
                    out.println(e.getMessage());
                    out.println("<br />");//salto de linea
                    System.out.println(nombreArchivo + " NO esta bien formado");
                    System.out.println(e.getMessage());
                    errorConforme = nombreArchivo + " NO esta bien formado" + " Excepcion: " + e.getMessage();
                }
                if(esBienFormado){
                // Si el fichero esta bien formado no saltará ninguna excepcion
                out.println(nombreArchivo + " Esta bien formado");
                out.println("<br />");//salto de linea
                errorConforme = nombreArchivo + " Esta bien formado";
                }
                try {
                    // Documento Válido??
                    checkValido(nombreArchivo, nombreEsquema);
                }
                // Indicacion de un fichero no válido
                catch (JDOMException e){
                    esValido=false;
                    out.println(nombreArchivo + " NO es valido");
                    out.println("<br />");//salto de linea
                    out.println(e.getMessage());
                    out.println("<br />");//salto de linea
                    System.out.println(nombreArchivo + " NO es valido");
                    System.out.println(e.getMessage());
                    errorValido = nombreArchivo + " NO es valido" + " Excepcion: " + e.getMessage();
                } catch (SAXException ex) {
//                    Logger.getLogger(Servlet1.class.getName()).log(Level.SEVERE, null, ex);
                    esValido=false;
                    out.println(nombreArchivo + " NO es valido");
                    out.println("<br />");//salto de linea
                    out.println(ex.getMessage());
                    out.println("<br />");//salto de linea
                    System.out.println(nombreArchivo + " NO es valido");
                    System.out.println(ex.getMessage());
                    errorValido = nombreArchivo + " NO es valido" + " Excepcion: " + ex.getMessage();
                }
                if(esValido){
                // Si el fichero es válido no saltará ninguna excepcion
                out.println(nombreArchivo+ " Es valido");
                out.println("<br />");//salto de linea
                errorValido = nombreArchivo + " Es valido";
                }
            }
            // Indicación de que el fichero no es accesible
            catch (IOException e){
                out.println("No se puede chequear " + nombreArchivo);
                out.println("porque: " + e.getMessage());
                out.println("<br />");//salto de linea

                System.out.println(nombreArchivo + " NO se puede chequear");
                System.out.println("porque: "+e.getMessage());
            }
        }
    return esValido && esBienFormado;
    }
}
