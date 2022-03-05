package FIIS.main.Respuestas;
//Seccion para registrar los datos del prospecto
//Generacion de usuario y contraseña a nivel de BD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class Registro {
    @RequestMapping(value="/Registro",method=GET)
    public String IngresoDatos(@RequestParam int id,@RequestParam int id5,@RequestParam int id4,@RequestParam int id3,@RequestParam int id2) throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection con=DriverManager.getConnection("jdbc:postgresql://1.1.1.1","ola","ola2");
        Statement st=con.createStatement();
        String validar="INSERT INTO PRODUCTO VALUES (?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(validar);
        pst.setInt(1,id);
        pst.setInt(1,id5);
        pst.setInt(1,id3);
        pst.setInt(1,id4);
        pst.setInt(1,id2);
        pst.executeUpdate();
        st.close();
        con.close();
        return "Ingreso de datos exitoso";
    }
}
