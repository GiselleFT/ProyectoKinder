package administrador;

import general.validador;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

public class addGroup extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String nombreGrupoNuevo = (String)request.getParameter("nombreGrupoNuevo");//Del registro del nuevo idProfesorNuevo
            String idProfesorNuevo = (String)request.getParameter("idProfesorNuevo");//Del registro del nuevo idProfesorNuevo
            String [] idAlumnos = request.getParameterValues("alumnoSeleccionado");
            
            
            String usuario = (String)session.getAttribute("usuario");//Del administrador
            String tipoAtt = (String)request.getParameter("tipo");//Del administrador
            session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
            PrintWriter out = response.getWriter();
            
            //info del nuevo idProfesorNuevo
//            System.out.println("addUser");
//            System.out.println("nuevo= "+nombreGrupoNuevo);
//            System.out.println("nuevo= "+idProfesorNuevo);
//            System.out.println("nuevo= "+contrasenaNuevo);
//            System.out.println("nuevo= "+tipoNuevo);
            
            //info del administrador
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);

            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
//            
            try{
                //Contruye un documento JDOM usando SAX, para procesar xml
                SAXBuilder builder = new SAXBuilder();
                //Para obtener la ruta absoluta del proyecto XML
                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
                rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
                rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
                rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
                File BD = new File(rutaAbsoluta);
                
                
                //Para cargar el documento xml
                Document doc = builder.build(BD);//documentos para contruir base de datos
                //Se obtiene el elemento raiz del xml
                Element raiz = doc.getRootElement();
                
                //Crea los elementos que conforman a un Usuario con los valores del nuevo registro
                Element grupoP = new Element("GRUPO");
                Element nombreG = new Element("grupo");
                Element idProfesorG = new Element("idProfesor");
                Element nombreProfesorG = new Element("nombreProfesor");
                Element inscritosG = new Element("inscritos");
                
                
                
                
                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
//                List lista2 = raiz.getChildren();//llena lista2 con los datos de la base de datos
                List lista2 = raiz.getChildren("GRUPO");
                String id = "";
                int id2;
                    //Obtiene informacion del último elemento añadido, para asignar ID
                    Element e = (Element)lista2.get(lista2.size()-1);
                    id = e.getAttributeValue("id");
                    id2 = Integer.parseInt(id) + 1;
                    id = ""+id2;//para ultimo id
                
                grupoP.setAttribute("id",id);
                nombreG.setText(nombreGrupoNuevo);//setText lo que va entre etiqueta de apertura y cierre
                idProfesorG.setText(idProfesorNuevo);
                List lista = raiz.getChildren("USUARIO");
                //Para obtener nombre del profesor
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el id capturado
                    Attribute idTipo = element.getAttribute("tipo");
                    Attribute idUsuario = element.getAttribute("id");//IMPORTANTE
                    if(idTipo.getValue().matches("2") && idUsuario.getValue().matches(idProfesorNuevo)){//se ha encontrado usuario Profesor
                       List lista3 = element.getChildren();//pasa los elementos a lista2
                       Element nombreProfG = (Element)lista3.get(0);//Obtiene el nombre
                       nombreProfesorG.setText(nombreProfG.getText());
                    }
                }
                
                inscritosG.setText(idAlumnos.length+"");
                
                
                //Agregar contenido de los elementos a nodo padre (USUARIO)
                grupoP.addContent(nombreG);
                grupoP.addContent(idProfesorG);
                grupoP.addContent(nombreProfesorG);
                grupoP.addContent(inscritosG);
                for (int i = 0; i < idAlumnos.length; i++) {
                    Element idAlumno = new Element("idAlumno");
                    idAlumno.setText(idAlumnos[i]);
                    grupoP.addContent(idAlumno);
                }
                
                //Agregar contenido de USUARIO a raiz
                raiz.addContent(grupoP);
                
                //Se crea serializador xml (para guardar en el xml)
                XMLOutputter xmlo =new XMLOutputter();
                
                //validar que si escriba bien el archivo, guardar los cambios al archivo
//                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
                try (FileWriter fw = new FileWriter(rutaAbsoluta)){
                    xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                    xmlo.output(doc, fw);//se escribe en el archivo
                    fw.flush();
                }
                response.sendRedirect("administrarGrupos?addGrupo=1");
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
    }
            
            
    }
