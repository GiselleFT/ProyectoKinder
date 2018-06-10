//ServicioAutocompletado.java
package general;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ServicioAutocompletado {

    //Obteniendo la lista de palabras
    public ArrayList obtListaPalabras() {   
        ArrayList palabras = new ArrayList();
        try {
//            //Para obtener la ruta absoluta del proyecto
//                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
//                rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
//                rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
//                rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
//                File BD = new File(rutaAbsoluta);
            URL url = new URL("http://localhost:8083/proyectoKinder/BD.xml");
            SAXBuilder builder = new SAXBuilder();
            //File archivoXML = new File(url);
            Document documento = builder.build(url);
            Element raiz = documento.getRootElement();
            List lista = raiz.getChildren("GRUPO");
            for (int i = 0; i < lista.size(); i++) {
                Element elemento = (Element) lista.get(i);
                //Obtiene los elementos que contiene el elemento actual
                List lista2 = elemento.getChildren();//pasa los elementos a lista2
                //Se recupera idGrupo de grupo
                Attribute idGrupo = elemento.getAttribute("id");
                //Se recupera grupo
                Element grupo = (Element)lista2.get(0);
                String cadena = grupo.getTextTrim();
                palabras.add(cadena);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabras;
    }
}