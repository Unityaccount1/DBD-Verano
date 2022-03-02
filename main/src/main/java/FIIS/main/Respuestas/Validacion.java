package FIIS.main.Respuestas;
//Seccion para validar un determinado al usuario
public class Validacion {
    public String ValidaUsuario() throws Exception {
        String valida = "select usuario,pwd from usuario";
        return "Inicio.html";
    }
    public String CredencialAfiliador(){

        return "valido";
    }
    public String CredencialAdmin(){
        return "valido";
    }
}
