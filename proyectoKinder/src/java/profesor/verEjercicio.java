package profesor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

//public es el modificador de clase
public class verEjercicio extends HttpServlet {

    @Override
    //protected, solo los miembros de la clase los puede ocupar 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            //objeto request obtiene informacion de la peticion del cliente
            //objeto response que encapsula pero va del servidor al cliente, al revés de request
            //throws es porque podría arrojar una excepcion, como entrada y salida de datos o ServeletException
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Recuperamos la sesion
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        String contrasena = (String) session.getAttribute("contrasena");
        String tipoAtt = (String) session.getAttribute("tipo");
        String idUsuario = (String) session.getAttribute("idUsuario");
        System.out.println("ID DE USUARIO! " + idUsuario);

        PrintWriter out = response.getWriter();

        //info del profesor
//            System.out.println("menuProfesor");
//            System.out.println("prof= "+usuario);
//            System.out.println("prof= "+contrasena);
//            System.out.println("prof= "+tipoAtt);
        //********Valida Tipo Usuario****************//
        if (!tipoAtt.equals("2")) {
            response.sendRedirect("login.html");
        }
        //*******************************************//
        String id = (String) request.getParameter("id");//id del ejercicio a modificar
        
        String nombreM = null,instruccionM = null, audioInstruccionM = null,imagenM = null, 
               audioImagenM = null,pistaM = null,respuestaCorrectaM = null, respuestaIncorrecta1M = null,
                    respuestaIncorrecta2M = null;
        try {
            //Contruye un documento JDOM usando SAX, para procesar xml
            SAXBuilder builder = new SAXBuilder();
            //Para obtener la ruta absoluta del proyecto
            String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
            rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
            rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
            rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
            File BD = new File(rutaAbsoluta);
            //Para cargar el documento xml
            Document doc = builder.build(BD);//documentos para contruir base de datos
            //Se obtiene el elemento raiz del xml
            Element raiz = doc.getRootElement();
            //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
            List lista = raiz.getChildren("EJERCICIO");

            for (int i = 0; i < lista.size(); i++) {//Por cada elemento 
                //Se procesa un elemento de la lista
                Element element = (Element) lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                //encontrar el elemento con el id capturado
                Attribute idElement = element.getAttribute("id");
                if (idElement.getValue().matches(id)) {//se ha encontrado ejercicio con id a modificar
                    //Obtiene los elementos que contiene el elemento actual

                    List lista2 = element.getChildren();//pasa los elementos a lista2
                    Element nombre = (Element) lista2.get(0);
                    Element instruccion = (Element) lista2.get(1);
                    Element audioInstruccion = (Element) lista2.get(2);
                    Element imagen = (Element) lista2.get(3);
                    Element audioImagen = (Element) lista2.get(4);
                    Element pista = (Element) lista2.get(5);
                    Element respuestaCorrecta = (Element) lista2.get(6);
                    Element respuestaIncorrecta1 = (Element) lista2.get(7);
                    Element respuestaIncorrecta2 = (Element) lista2.get(8);

                    nombreM = nombre.getText();
                    instruccionM = instruccion.getText();
                    audioInstruccionM = audioInstruccion.getText();
                    imagenM = imagen.getText();
                    audioImagenM = audioImagen.getText();
                    pistaM = pista.getText();
                    respuestaCorrectaM = respuestaCorrecta.getText();
                    respuestaIncorrecta1M = respuestaIncorrecta1.getText();
                    respuestaIncorrecta2M = respuestaIncorrecta2.getText();
                }
            }
        } catch (JDOMException e) {
        }

        out.println("<!DOCTYPE html>");//es un texto enviado por el canal de comunicacion 
        out.println("<html>");//nodo raiz, ahi comienza
        out.println("<head>");//comienza el encabezado 
        out.println("<title>Servlet Servlet1</title>"); //Aqui dentro van las hojas de estilo y funcionalidad (javascript)
        out.println("</head>");//termina el encabezado
        out.println("<body>");
        out.println("<h1>" + nombreM + "</h1>");//contenido que se va a desplegar dentro de la pagina web
        out.println("<h1>" + instruccionM + "</h1>");
        out.println("<h1>" + audioInstruccionM + "</h1>");
        out.println("<h1>" + imagenM + "</h1>");
        out.println("<h1>" + audioImagenM + "</h1>");
        out.println("<h1>" + pistaM + "</h1>");
        out.println("<h1>" + respuestaCorrectaM + "</h1>");
        out.println("<h1>" + respuestaIncorrecta1M + "</h1>");
        out.println("<h1>" + respuestaIncorrecta2M + "</h1>");
        out.println("</body>");
        out.println("</html>");//nodo raiz, aqui termina

    }
}
