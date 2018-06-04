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
import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class eliminarEjercicio extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            
            PrintWriter out = response.getWriter();
            
            //info del profesor
//            System.out.println("menuProfesor");
//            System.out.println("prof= "+usuario);
//            System.out.println("prof= "+contrasena);
//            System.out.println("prof= "+tipoAtt);
            
            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("2")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            
            //info del ejercicio a eliminar
            String id = (String)request.getParameter("id");//id del ejercicio a modificar
            session.setAttribute("id", id);
            System.out.println("id eliminarEjercicio= "+id);
            
            try{
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
//                List lista = raiz.getChildren();
                List lista = raiz.getChildren("EJERCICIO");
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<lista.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la lista
                    Element element = (Element)lista.get(i);//guarda los datos de la lista en un arreglo de elementos
                    //encontrar el elemento con el id capturado
                    Attribute idElement = element.getAttribute("id");
                    if(idElement.getValue().matches(id)){//se ha encontrado usuario con id a modificar
                        lista.remove(i);
                        //Se crea serializador xml (para guardar en el xml)
                        XMLOutputter xmlo =new XMLOutputter();
                        //validar que si escriba bien el archivo, guardar los cambios al archivo
        //                try (FileWriter fw = new FileWriter(rutaAbsoluta+"\\BD.xml")){
                        try (FileWriter fw = new FileWriter(rutaAbsoluta)){
                            xmlo.setFormat(Format.getPrettyFormat());//Formato de salida al xml
                            xmlo.output(doc, fw);//se escribe en el archivo
                            fw.flush();
                        }
                        response.sendRedirect("adminEjercicios");
                    }
                    
                }
                out.println("</body>");
                out.println("</html>");
        }catch(JDOMException e){}
                        
    }

}