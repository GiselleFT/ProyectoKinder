package administrador;

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
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class addUser extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String nombreNuevo = (String)request.getParameter("nombreNuevo");//Del registro del nuevo usuarioNuevo
            String usuarioNuevo = (String)request.getParameter("usuarioNuevo");//Del registro del nuevo usuarioNuevo
            String contrasenaNuevo = (String)request.getParameter("contrasenaNuevo");//Del registro del nuevo usuarioNuevo
            String tipoNuevo = (String)request.getParameter("tipoNuevo");//Del registro del nuevo usuarioNuevo
            //Cast del tipo
            if(tipoNuevo.equals("Administrador")){
                tipoNuevo = "1";
            }
            else if(tipoNuevo.equals("Profesor")){
                tipoNuevo = "2";
            }
            else if(tipoNuevo.equals("Alumno")){
                tipoNuevo = "3";
            }
            
            
            String usuario = (String)session.getAttribute("usuario");//Del administrador
            String contrasena = (String)session.getAttribute("contrasena");//Del administrador
            String tipoAtt = (String)request.getParameter("tipo");//Del administrador
            session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
            PrintWriter out = response.getWriter();
            
            //info del nuevo usuarioNuevo
//            System.out.println("addUser");
//            System.out.println("nuevo= "+nombreNuevo);
//            System.out.println("nuevo= "+usuarioNuevo);
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
            
            try{
                //Contruye un documento JDOM usando SAX, para procesar xml
                SAXBuilder builder = new SAXBuilder();
                //Para obtener la ruta absoluta del proyecto
//                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
//                rutaAbsoluta = rutaAbsoluta.replaceAll("'\'", "'\\'");
//                System.out.println("RUTA ABSOLUTA= "+rutaAbsoluta);
                //Ruta absoluta del archivo BD.xml
//                File BD = new File("C:\\Users\\Giselle\\Documents\\GitHub\\ProyectoKinder\\proyectoKinder\\web\\BD.xml");
//                System.out.println("addUser ruta:" + rutaAbsoluta);
//                File BD = new File(rutaAbsoluta+"\\BD.xml");
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
                //Crea los elementos que conforman a un Usuario con los valores del nuevo registro
                Element usuarioP = new Element("USUARIO");
                Element nombreU = new Element("nombre");
                Element usuarioU = new Element("usuario");
                Element contrasenaU = new Element("contrasena");
                
                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
//                List lista = raiz.getChildren();//llena lista con los datos de la base de datos
                List lista = raiz.getChildren("USUARIO");
                String id = "";
                int id2;
                    //Obtiene informacion del último elemento añadido, para asignar ID
                    Element e = (Element)lista.get(lista.size()-1);
                    id = e.getAttributeValue("id");
                    id2 = Integer.parseInt(id) + 1;
                    id = ""+id2;//para ultimo id
                
                usuarioP.setAttribute("id",id);
                usuarioP.setAttribute("tipo", tipoNuevo);
                nombreU.setText(nombreNuevo);//setText lo que va entre etiqueta de apertura y cierre
                contrasenaU.setText(contrasenaNuevo);
                usuarioU.setText(usuarioNuevo);
                
                //Agregar contenido de los elementos a nodo padre (USUARIO)
                usuarioP.addContent(nombreU);
                usuarioP.addContent(usuarioU);
                usuarioP.addContent(contrasenaU);
                
                //Agregar contenido de USUARIO a raiz
                raiz.addContent(usuarioP);
                
                //Se crea serializador xml (para guardar en el xml)
                XMLOutputter xmlo =new XMLOutputter();
                
                //validar que si escriba bien el archivo, guardar los cambios al archivo
//                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
                try (FileWriter fw = new FileWriter(rutaAbsoluta)){
                    xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                    xmlo.output(doc, fw);//se escribe en el archivo
                    fw.flush();
                }
                response.sendRedirect("administrarUsuario?addUsuario=1");
                
                
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
            
            
    }

