package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class dragAndDropInstruccion extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            //Se recuperan los parametros del formulario y se suben a sesion 
            String nombreNuevo = (String) request.getParameter("nombreNuevo");//Del registro del nuevo ejercicioNuevo
            session.setAttribute("nombreNuevo", nombreNuevo);
            String instruccionNuevo = (String) request.getParameter("instruccionNuevo");
            session.setAttribute("instruccionNuevo", instruccionNuevo);
            
            String audioInstruccionNuevo = "";/*(String) request.getParameter("audioInstruccionNuevo");*/
            session.setAttribute("audioInstruccionNuevo", audioInstruccionNuevo);
            String imagenNuevo =""; /*(String)request.getParameter("imagenNuevo");*/
            session.setAttribute("imagenNuevo", imagenNuevo);
            String audioImagenNuevo = ""; /*(String) request.getParameter("audioImagenNuevo");*/
            session.setAttribute("audioImagenNuevo", audioImagenNuevo);
            
            String pistaNuevo = (String) request.getParameter("pistaNuevo");
            session.setAttribute("pistaNuevo", pistaNuevo);
            String respuestaCorrectaNuevo = (String) request.getParameter("respuestaCorrectaNuevo");
            session.setAttribute("respuestaCorrectaNuevo", respuestaCorrectaNuevo);
            String respuestaIncorrecta1Nuevo = (String) request.getParameter("respuestaIncorrecta1Nuevo");
            session.setAttribute("respuestaIncorrecta1Nuevo", respuestaIncorrecta1Nuevo);
            String respuestaIncorrecta2Nuevo = (String) request.getParameter("respuestaIncorrecta2Nuevo");
            session.setAttribute("respuestaIncorrecta2Nuevo", respuestaIncorrecta2Nuevo);
            System.out.println("Respuesta Inc2 en dropInstru: " + respuestaIncorrecta2Nuevo);
            //Bandera que permite identificar el paso en el que se encuentra
            session.setAttribute("banderaArchivo", 1);
            
            
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


            //Bloque drag and drop
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Agregar Ejercicio Paso 2/4</title>");
            out.println("<script src='js/dropzone.js'></script>");
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("<link rel='stylesheet' href='css/dropzone.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Agregar Ejercicio Paso 2/4</h1>");
            out.println("<h2>Selecciona un archivo .mp3</h2>");
            //Se sube archivo
            out.println("<form id='dd1' action='uploadFiles' class='dropzone' method='POST' enctype = 'multipart/form-data'>");
            out.println("<input name='file' type='file' style='color:transparent'/>");
            out.println("</form>");
            //Para continuar con la creacion del ejercicio
            out.println("<form action='dragAndDropImagen' method='POST'>");
            out.println("<input type='submit' value='Paso 2/4'/>");
            out.println("</form>");
            
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
            out.println("</body>");
            out.println("</html>");
        }

}