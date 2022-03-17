package FIIS.main.Respuestas;
//Seccion para registrar los datos del prospecto
//Generacion de usuario y contraseña a nivel de BD
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class Registro {
    @RequestMapping(value="/Registro", method =GET)
    public String IngresoDatos(@RequestParam String dni,@RequestParam String nombres,@RequestParam String apellidopat,
                               @RequestParam String apellidomat, @RequestParam int telefono,@RequestParam int celular,
                               @RequestParam String correo) throws Exception{
        String claveGen;
        int indexaleatorio,longitudClave=10;
        Random aleatorio=new Random();
        String[] Dicionario={"a","b","c","d","e","f","g","1","2","3","4","5","H","I","L","W","Z","#","$"};
        StringBuilder sb=new StringBuilder();
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432/d1d8j80vn6i3ra",
                "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st=con.createStatement();
        String validar="INSERT INTO persona(dni,nombres,apellido_paterno,apellido_materno,telefono,celular,correo) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(validar);
        pst.setString(1,dni);
        pst.setString(2,nombres);
        pst.setString(3,apellidopat);
        pst.setString(4,apellidomat);
        pst.setInt(5,telefono);
        pst.setInt(6,celular);
        pst.setString(7,correo);
        pst.executeUpdate();
        st.close();
        for (int i=0;i<longitudClave;i++){
            indexaleatorio=aleatorio.nextInt(Dicionario.length);
            sb.append(Dicionario[indexaleatorio]);
        }
        claveGen=sb.toString();
        Statement st2=con.createStatement();
        //Insercion de usuario
        String crearUsuario="INSERT INTO USUARIO(correo,contrasenia,fecha_creacion) VALUES (?,?,CURRENT_DATE)";
        PreparedStatement pst2=con.prepareStatement(crearUsuario);
        pst2.setString(1,correo);
        pst2.setString(2,claveGen);
        pst2.executeUpdate();
        st2.close();
        //Insercion de solicitante
        Statement st3=con.createStatement();
        String crearSolicitante="INSERT INTO SOLICITANTE(id_solicitante) VALUES (?)";
        PreparedStatement pst3=con.prepareStatement(crearSolicitante);
        pst3.setString(1,dni);
        pst3.executeUpdate();
        st3.close();
        con.close();
        return "Usuario:"+correo+"\n"+"Contraseña:"+claveGen;
    }
}
