package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class agregarEjercicio extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String tipoAtt = (String)session.getAttribute("tipo");
            String idUsuario = (String)session.getAttribute("idUsuario");
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
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Agregar Ejercicio</title>");
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Agregar Ejercicio</h1>");
            
            out.println("<form action='addExercise' method='get'>");
            out.println("<h6>Nombre:</h6> <input id='nombre' type='text' name='nombreNuevo' required/><br />");
            out.println("<h6>Instruccion:</h6> <input id='instruccion' type='text' name='instruccionNuevo' required/><br />");
            out.println("<h6>Audio Instruccion:</h6> <input id='audioInstruccion' type='text' name='audioInstruccionNuevo' required/><br />");
            out.println("<h6>Imagen:</h6> <input id='imagen' type='text' name='imagenNuevo' required/><br />");
            out.println("<h6>Audio Imagen:</h6> <input id='audioImagen' type='text' name='audioImagenNuevo' required/><br />");
            out.println("<h6>Pista:</h6> <input id='pista' type='text' name='pistaNuevo' required/><br />");
            out.println("<h6>Respuesta correcta:</h6> <input id='respuestaCorrecta' type='text' name='respuestaCorrectaNuevo' required/><br />");
            out.println("<h6>Respuesta incorrecta 1:</h6> <input id='respuestaIncorrecta1' type='text' name='respuestaIncorrecta1Nuevo' required/><br />");
            out.println("<h6>Respuesta incorrecta 2:</h6> <input id='respuestaIncorrecta2' type='text' name='respuestaIncorrecta2Nuevo' required/><br />");
            
            out.println("<br />");
            out.println("<br />");
            out.println("<input type='submit' value='Agregar ejercicio'>");
            out.println("</form>");
            
            
            out.println("<br />");
            out.println("<br />");
            //Agregar Usuario
            out.println("<form action='menuProfesor' method='get'>");
            out.println("<input type='submit' value='Menu Profesor'>");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
    }

}
