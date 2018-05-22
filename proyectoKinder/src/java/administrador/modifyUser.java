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

public class modifyUser extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String nombreNuevo = request.getParameter("nombreNuevo");//Del registro del nuevo usuarioNuevo
            String usuarioNuevo = request.getParameter("usuarioNuevo");//Del registro del nuevo usuarioNuevo
            String contrasenaNuevo = request.getParameter("contrasenaNuevo");//Del registro del nuevo usuarioNuevo
            String tipoNuevo = request.getParameter("tipoNuevo");//Del registro del nuevo usuarioNuevo
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
            
            //info del usuario a modificar
            String id = (String)session.getAttribute("id");//id del usuario a modificar
            System.out.println("id modifyUser= "+id);
            
            
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
                String rutaAbsoluta = request.getSession().getServletContext().getRealPath("/");
                rutaAbsoluta = rutaAbsoluta.replace("\\", "/");
                rutaAbsoluta = rutaAbsoluta.replaceAll("/build", "");
                rutaAbsoluta = rutaAbsoluta.concat("BD.xml");
                File BD = new File(rutaAbsoluta);
//                System.out.println("addUser ruta:" + rutaAbsoluta);
//                File BD = new File(rutaAbsoluta+"\\BD.xml");
                
                //Para cargar el documento xml
                Document doc = builder.build(BD);//documentos para contruir base de datos
                //Se obtiene el elemento raiz del xml
                Element raiz = doc.getRootElement();
                //Lista de nodos almacenados, lo que esta contenido entre las etiquetas de raiz
                List lista = raiz.getChildren();
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el id capturado
                    Attribute idElement = element.getAttribute("id");
                    if(idElement.getValue().matches(id)){//se ha encontrado usuario con id a modificar
                        //Obtiene los elementos que contiene el elemento actual
                        List lista2 = element.getChildren();//pasa los elementos a lista2
                        //Nombre
                        Element nombre2 = (Element)lista2.get(0);
                        //Se crea usuario2 para comparar con usuario
                        Element usuario2 = (Element)lista2.get(1);
                        //se crea contrasena2 para comprar con contrasena
                        Element contrasena2 = (Element)lista2.get(2);
                        //Atributo tipo
                        Attribute tipo2 = element.getAttribute("tipo");
                        
                        
                        nombre2.setText(nombreNuevo);
                        usuario2.setText(usuarioNuevo);
                        contrasena2.setText(contrasenaNuevo);
                        tipo2.setValue(tipoNuevo);
                        
                        //Se crea serializador xml (para guardar en el xml)
                        XMLOutputter xmlo =new XMLOutputter();

                        //validar que si escriba bien el archivo, guardar los cambios al archivo
        //                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
                        try (FileWriter fw = new FileWriter(rutaAbsoluta)){
                            xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                            xmlo.output(doc, fw);//se escribe en el archivo
                            fw.flush();
                        }
                        response.sendRedirect("administrarUsuario");
                    }
                    
                } 
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
            
            
    }

