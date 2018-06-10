package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class dragAndDropImagen extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            session.setAttribute("banderaArchivo", 2);
            
            String usuario = (String) session.getAttribute("usuario");//Del administrador
            String contrasena = (String) session.getAttribute("contrasena");//Del administrador
            String tipoAtt = (String) session.getAttribute("tipo");
            //session.setAttribute("tipo", tipoAtt);//conservar sesion del administrador con su tipo
            String idUsuario = (String) session.getAttribute("idUsuario");
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
            out.println("<title>Agregar Ejercicio Paso 3/4</title>");
            out.println("<script src='js/dropzone.js'></script>");
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("<link rel='stylesheet' href='css/dropzone.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Agregar Ejercicio Paso 3/4</h1>");
            out.println("<h2>Selecciona un archivo .jpeg</h2>");
            //Se sube archivo
            out.println("<form id='dd2' action='uploadFiles' class='dropzone' method='POST' enctype = 'multipart/form-data'>");
            out.println("<input name='file' type='file' style='color:transparent'/>");
            out.println("</form>");
            //Para continuar con la creacion del ejercicio
            out.println("<form action='dragAndDropAudioImagen' method='POST'>");
            out.println("<input type='submit' value='Paso 3/4'/>");
            out.println("</form>");
            
            //Para restringir tipos de archivos
            out.println("<script>\n" +
"            Dropzone.options.dd2 = {\n" +
"                maxFiles: 1,\n" +
"                addRemoveLinks: true,\n" +
"                acceptedFiles: '.jpg',\n" +
"                dictDefaultMessage: 'Arrastra archivo .jpg en este drop',\n" +
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
            
            
            
            out.println("</body>");
            out.println("</html>");
    }
}