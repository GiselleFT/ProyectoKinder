function agregarUsuario(){
    var nombre = document.getElementById("nombre").value; 
    var usuario = document.getElementById("usuario").value; 
    var contrasena = document.getElementById("contrasena").value; 
    var tipo = document.getElementById("tipo").value; 
    
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","addUser?nombre="+nombre+"&usuario="+usuario+"&contrasena="+contrasena+"&tipo="+tipo, true);
    xmlhttp.send();
}