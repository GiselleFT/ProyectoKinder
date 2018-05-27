package administrador;

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

public class modificarGrupo extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            
            PrintWriter out = response.getWriter();
            
            //info del administrador
//            System.out.println("agregarUsuario");
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);
            

            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            
            //info del usuario a modificar
            String idGrupo = (String)request.getParameter("idGrupo");//id del grupo a modificar
            session.setAttribute("idGrupo", idGrupo);
            System.out.println("id modificarGrupo= "+idGrupo);
            
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
                List listaGrupos = raiz.getChildren("GRUPO");
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Modificar Grupo</title>");
                out.println("<link rel='stylesheet' href='css/estilos.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Modificar Grupo</h1>");
                
                //Para recorrer el arbol de nodos
                for(int i=0;i<listaGrupos.size();i++){//Por cada elemento 
                    //Se procesa un elemento de la listaGrupos
                    Element elementGrupo = (Element)listaGrupos.get(i);
                    //encontrar el elemento con el idGrupo capturado
                    Attribute idElementGrupo = elementGrupo.getAttribute("id");
                    if(idElementGrupo.getValue().matches(idGrupo)){//se ha encontrado el grupo a modificar
                        //Obtiene los elementos que contiene el elemento actual
                        List infoGrupo = elementGrupo.getChildren();//pasa los elementos a infoGrupo
                        //Obtiene valores de los elementos
                        Element nombreGrupo = (Element)infoGrupo.get(0);
                        Element idProfesor = (Element)infoGrupo.get(1);
                        Element nombreProfesor = (Element)infoGrupo.get(2);
                        Element inscritos = (Element)infoGrupo.get(3);
                        
                        String nombreM = nombreGrupo.getText();
                        String idProfesorM = idProfesor.getText();
                        String nombreProfesorM = nombreProfesor.getText();
                        String inscritosM = inscritos.getText();
                        
//                        System.out.println("Info recuperada:");
//                        System.out.println("Nombre: "+nombreM);
//                        System.out.println("idProfesor: "+idProfesorM);
//                        System.out.println("Nombre profesor: "+nombreProfesorM);
//                        System.out.println("inscritos: "+inscritosM);
                        
                        out.println("<form action='modifyGroup' method='get'>");
                        out.println("<h6>Nombre grupo:</h6> <input id='nombre' type='text' value='"+nombreM+"' name='nombreGrupoNuevo' required/><br />");
                        List listaUsuarios = raiz.getChildren("USUARIO");
                        out.println("<h6>Profesor:</h6> <select id='idProfesorNuevo' name='idProfesorNuevo'>");//Combobox    
                        out.println("<option value='"+idProfesorM+"'>"+nombreProfesorM+"</option>");
                        for(int j=0;j<listaUsuarios.size();j++){//Por cada elemento
                            Element elementProfesor = (Element)listaUsuarios.get(j);
                            Attribute idTipo = elementProfesor.getAttribute("tipo");
                            Attribute idUsuario = elementProfesor.getAttribute("id");//IMPORTANTE
//                            System.out.println("idTipo= "+ idTipo.getValue());
//                            System.out.println("idUsuario= "+ idUsuario.getValue());
                            if(idTipo.getValue().matches("2") && !idUsuario.getValue().matches(idProfesorM)){
                                List infoProfesor = elementProfesor.getChildren();
                                Element nomProfesor = (Element)infoProfesor.get(0);//Obtiene el nombre
                                out.println("<option value='"+idUsuario.getValue()+"'>"+nomProfesor.getText()+"</option>");
                            }  
                        }
                        out.println("</select>");
                        
                        out.println("<br />");
                        out.println("<h6>Alumnos Inscritos:</h6>");//Combobox 
                        out.println("<br />");
                        boolean existeAlumno = false;
                        for(int k=0;k<listaUsuarios.size();k++){//Por cada elemento
                            Element elementAlumnos = (Element)listaUsuarios.get(k);//guarda los datos de la listaGrupos en un arreglo de elementos
                            Attribute idTipo = elementAlumnos.getAttribute("tipo");
                            Attribute idUsuario = elementAlumnos.getAttribute("id");//IMPORTANTE
                            System.out.println("idTipo= "+ idTipo.getValue());
                            System.out.println("idUsuario= "+ idUsuario.getValue());
                            if(idTipo.getValue().matches("3")){//Si los usuarios son alumnos
                                for (int j = 0; j < Integer.parseInt(inscritos.getValue()); j++) {//Checo si ya existen en el grupo
                                    Element idAlumno = (Element)infoGrupo.get(j+4);//id de alumnos que pertenecen al grupo
                                    List infoAlumno = elementAlumnos.getChildren();//info que contiene un usuario
                                    Element nombreAlumno = (Element)infoAlumno.get(0);//Obtiene el nombre
                                    //Si ya existe el alumno en el grupo aparecera con una palomita
                                    if(idUsuario.getValue().matches(idAlumno.getText())){
                                        out.println("<input type='checkbox' id='alumnoSeleccionado' name='alumnoSeleccionado' value='"+idAlumno.getText()+"' checked>"+nombreAlumno.getText()+"<br>");
                                        existeAlumno = true;
                                        break;
                                    }
                                } 
                            }
                            //Si no existe el alumno y es de tipo Alumno, aparecera sin palomita
                            if(!existeAlumno && idTipo.getValue().matches("3")){
//                                System.out.println("Entre aqui!!!");
                                    List elAlumno = elementAlumnos.getChildren();//pasa los elementos a infoGrupo
                                    Element nombreAl = (Element)elAlumno.get(0);
                                    out.println("<input type='checkbox' id='alumnoSeleccionado' name='alumnoSeleccionado' value='"+idUsuario.getValue()+"'>"+nombreAl.getText()+"<br>");
                            }
                            else if(existeAlumno){
                                //se regresa a false para comparar con el proximo alumno
                                existeAlumno = false;
                            }
                        }
                        
                        out.println("<input type='hidden' name='tipo' value="+tipoAtt+">");//Del administrador
                        out.println("<br />");
                        out.println("<br />");
                        out.println("<input type='submit' value='Modificar grupo'>");
                        out.println("</form>");
                        
                    }
                    
                }
                
                out.println("<br />");
                out.println("<br />");
                //Agregar Usuario
                out.println("<form action='menuAdministrador' method='get'>");
                out.println("<input type='submit' value='Menu Administrador'>");
                out.println("</form>");
                
                out.println("</body>");
                out.println("</html>");
        }catch(JDOMException e){}
                        
    }

}