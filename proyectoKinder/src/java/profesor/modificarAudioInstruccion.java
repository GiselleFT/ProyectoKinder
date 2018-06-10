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

public class modificarAudioInstruccion extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            //Se recuperan los parametros del formulario y se suben a sesion 
//            String audioInstruccionM = (String)request.getParameter("audioInstruccionM");//Del registro del nuevo ejercicioNuevo
//            session.setAttribute("audioInstruccionM", audioInstruccionM);
            
            String usuario = (String) session.getAttribute("usuario");
            String tipoAtt = (String) session.getAttribute("tipo");
            String idUsuario = (String) session.getAttribute("idUsuario");
            String idEjercicio = (String) session.getAttribute("id");
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


            //Bloque drag and drop
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Modificar Audio Instruccion</title>");
            out.println("<script src='js/dropzone.js'></script>");
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("<link rel='stylesheet' href='css/dropzone.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Modificar Audio Instruccion</h1>");
            out.println("<h2>Selecciona un archivo .mp3</h2>");
            //Se sube archivo
            out.println("<form id='dd1' action='uploadFiles' class='dropzone' method='POST' enctype = 'multipart/form-data'>");
            out.println("<input type='hidden' name='banderaArchivo' value='1'>");
            out.println("<input name='file' type='file' style='color:transparent'/>");
            out.println("</form>");
            
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

            //Para recorrer el arbol de nodos
            for (int i = 0; i < lista.size(); i++) {//Por cada elemento 
                //Se procesa un elemento de la lista
                Element element = (Element) lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                //encontrar el elemento con el id capturado
                Attribute idElement = element.getAttribute("id");
                if (idElement.getValue().matches(idEjercicio)) {//se ha encontrado ejercicio con id a modificar
                    //Obtiene los elementos que contiene el elemento actual
                    List lista2 = element.getChildren();//pasa los elementos a lista2
                    Element audioInstruccion = (Element) lista2.get(2);
                    String audioInstruccionNuevo = (String) session.getAttribute("audioInstruccionNuevo");
                    System.out.println("MODIFICAR AUDIO OJO AQUI: " + audioInstruccionNuevo);
                    audioInstruccion.setText(audioInstruccionNuevo);
                }
            }
            /*me quede aqui CHECAR ESTO POR QUE NO GUARDA NADA EN AUDIOINSTRUCCIONNUEVO*/

            out.println("<br />");
            out.println("<br />");

            out.println("<form action='menuProfesor' method='get'>");
            out.println("<input type='submit' value='Menu Profesor'>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        } catch (JDOMException e) {
        }
            
            
            
            
            //Para restringir tipos de archivos
            out.println("<script>\n" +
"            Dropzone.options.dd1 = {\n" +
"                maxFiles: 1,\n" +
"                addRemoveLinks: true,\n" +
"                acceptedFiles: '.mp3',\n" +
"                dictDefaultMessage: 'Arrastra archivo .mp3 en este drop',\n" +
"                init: function() {\n" +
"                    var self = this;\n" +
"                    self.options.addRemoveLinks = true;\n" +
"                    self.options.dictRemoveFile = 'Delete';\n" +
"                    this.on('complete', function (file) {\n" +
"                        setTimeout(3000);\n" +
"                        swal('Añadido correctamente','Da click en el boton','success').then((value) => {                                    \n" +
"                            setTimeout(1000);\n" +
"                            this.removeFile(file); \n" +
"                        });\n" +
"                    });\n" +
"                }    \n" +
"            };\n" +
"        </script>");
            
            
            
            
            out.println("<br />");
            out.println("<br />");
                
            out.println("<form action='modificarEjercicio' method='post'>");
            out.println("<input type='submit' value='Regresar'>");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
        }

}