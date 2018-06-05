package profesor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class addExercise extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nombreNuevo = (String) request.getParameter("nombreNuevo");//Del registro del nuevo ejercicioNuevo
        String instruccionNuevo = (String) request.getParameter("instruccionNuevo");
        String audioInstruccionNuevo = (String) request.getParameter("audioInstruccionNuevo");
        String imagenNuevo =""; /*(String)request.getParameter("imagenNuevo");*/
        String audioImagenNuevo = (String) request.getParameter("audioImagenNuevo");
        String pistaNuevo = (String) request.getParameter("pistaNuevo");
        String respuestaCorrectaNuevo = (String) request.getParameter("respuestaCorrectaNuevo");
        String respuestaIncorrecta1Nuevo = (String) request.getParameter("respuestaIncorrecta1Nuevo");
        String respuestaIncorrecta2Nuevo = (String) request.getParameter("respuestaIncorrecta2Nuevo");

        String usuario = (String) session.getAttribute("usuario");//Del administrador
        String contrasena = (String) session.getAttribute("contrasena");//Del administrador
        String tipoAtt = (String) session.getAttribute("tipo");
        //session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
        String idUsuario = (String) session.getAttribute("idUsuario");
        PrintWriter out = response.getWriter();

        //info del profesor
//            System.out.println("menuProfesor");
//            System.out.println("prof= "+usuario);
//            System.out.println("prof= "+contrasena);
        System.out.println("prof= " + tipoAtt);

        //********Valida Tipo Usuario****************//
        if (!tipoAtt.equals("2")) {
            response.sendRedirect("login.html");
        }
        //*******************************************//

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        String ruta = request.getSession().getServletContext().getRealPath("/");
        ruta = ruta.replace("\\", "/");
        ruta = ruta.replaceAll("/build", "");
        ruta = ruta.concat("/archivos/");
        System.out.println(ruta);
        System.out.println(new File(ruta));
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(ruta, item.getName());
                    imagenNuevo=item.getName();
                    item.write(file);
                    System.out.println("uploaded");
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            writer.close();
        }

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
            //Crea los elementos que conforman a un Ejercicio con los valores del nuevo registro
            Element ejercicioP = new Element("EJERCICIO");
            Element nombreE = new Element("nombre");
            Element instruccionE = new Element("instruccion");
            Element audioInstruccionE = new Element("audioInstruccion");
            Element imagenE = new Element("imagen");
            Element audioImagenE = new Element("audioImagen");
            Element pistaE = new Element("pista");
            Element respuestaCorrectaE = new Element("respuestaCorrecta");
            Element respuestaIncorrecta1E = new Element("respuestaIncorrecta");
            Element respuestaIncorrecta2E = new Element("respuestaIncorrecta");

            //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
            List lista = raiz.getChildren("EJERCICIO");
            String id = "";
            int id2;
            //Obtiene informacion del último elemento añadido, para asignar ID
            Element e = (Element) lista.get(lista.size() - 1);
            id = e.getAttributeValue("id");
            id2 = Integer.parseInt(id) + 1;
            id = "" + id2;//para ultimo id

            ejercicioP.setAttribute("id", id);
            ejercicioP.setAttribute("idProfesor", idUsuario);

            nombreE.setText(nombreNuevo);//setText lo que va entre etiqueta de apertura y cierre
            instruccionE.setText(instruccionNuevo);
            audioInstruccionE.setText(audioInstruccionNuevo);
            imagenE.setText(imagenNuevo);
            audioImagenE.setText(audioImagenNuevo);
            pistaE.setText(pistaNuevo);
            respuestaCorrectaE.setText(respuestaCorrectaNuevo);
            respuestaIncorrecta1E.setText(respuestaIncorrecta1Nuevo);
            respuestaIncorrecta2E.setText(respuestaIncorrecta2Nuevo);

            //Agregar contenido de los elementos a nodo padre (EJERCICIO)
            ejercicioP.addContent(nombreE);
            ejercicioP.addContent(instruccionE);
            ejercicioP.addContent(audioInstruccionE);
            ejercicioP.addContent(imagenE);
            ejercicioP.addContent(audioImagenE);
            ejercicioP.addContent(pistaE);
            ejercicioP.addContent(respuestaCorrectaE);
            ejercicioP.addContent(respuestaIncorrecta1E);
            ejercicioP.addContent(respuestaIncorrecta2E);

            //Agregar contenido de USUARIO a raiz
            raiz.addContent(ejercicioP);

            //Se crea serializador xml (para guardar en el xml)
            XMLOutputter xmlo = new XMLOutputter();

            //validar que si escriba bien el archivo, guardar los cambios al archivo
//                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
            try (FileWriter fw = new FileWriter(rutaAbsoluta)) {
                xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                xmlo.output(doc, fw);//se escribe en el archivo
                fw.flush();
            }
            response.sendRedirect("adminEjercicios");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
