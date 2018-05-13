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
            String nombre = (String)request.getParameter("nombre");
            String usuario = (String)request.getParameter("usuario");
            String contrasena = (String)request.getParameter("contrasena");
            String tipoAtt = (String)request.getParameter("tipo");
            if(tipoAtt.equals("Administrador")){
                tipoAtt = "1";
            }
            else if(tipoAtt.equals("Profesor")){
                tipoAtt = "2";
            }
            else if(tipoAtt.equals("Alumno")){
                tipoAtt = "3";
            }
            
            String tipoSession = (String)request.getParameter("tipoAtt");
            System.out.println("addUser.java"+tipoSession);
            session.setAttribute("tipo", tipoSession);
            PrintWriter out = response.getWriter();
            System.out.println(nombre);
            System.out.println(usuario);
            System.out.println(contrasena);
            System.out.println(tipoAtt);
            //*****************************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*****************************//
            try{
                SAXBuilder builder = new SAXBuilder();//contructor sax
                File BD = new File("C:\\Users\\Giselle\\Documents\\GitHub\\ProyectoKinder\\proyectoKinder\\web\\BD.xml");//ruta de base de datos
                Document doc = builder.build(BD);//documentos para contruir base de datos
                Element raiz = doc.getRootElement();//raiz de los datos
                Element usuarioP = new Element("USUARIO");
                Element nombreU = new Element("nombre");
                Element usuarioU = new Element("usuario");
                Element contrasenaU = new Element("contrasena");
                
                
                List lista = raiz.getChildren();//llena lista con los datos de la base de datos
                String id = "";
                int id2;
                    Element e = (Element)lista.get(lista.size()-1);
                    id = e.getAttributeValue("id");
                    id2 = Integer.parseInt(id);
                    id = ""+(id2+1);//para ultimo id
                
                usuarioP.setAttribute("id",id);
                usuarioP.setAttribute("tipo", tipoAtt);
                nombreU.setText(nombre);
                contrasenaU.setText(contrasena);
                usuarioU.setText(usuario);
                
                usuarioP.addContent(nombreU);
                usuarioP.addContent(usuarioU);
                usuarioP.addContent(contrasenaU);
                
                raiz.addContent(usuarioP);
                
                XMLOutputter xmlo =new XMLOutputter();
                //validar que si escriba bien el archivo
                try (FileWriter fw = new FileWriter("C:\\Users\\Giselle\\Documents\\GitHub\\ProyectoKinder\\proyectoKinder\\web\\BD.xml")){
                    xmlo.setFormat(Format.getPrettyFormat());
                    xmlo.output(doc, fw);
                    fw.flush();
                }
                response.sendRedirect("administrarUsuario");
                
           
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
            
            
    }

