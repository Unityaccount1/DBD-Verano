package FIIS.main.Respuestas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class RespuestaInicial {
    @RequestMapping("/Respuesta")
    public String RespuestaSql() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection("jdbc:postgresql:1464", "usuario", "contraseña");
        Statement st = c.createStatement();
        String C1 = "select aea from table where cadena='humo'";
        ResultSet rs1 = st.executeQuery(C1);
        rs1.close();
        st.close();
        return "hola";
    }

}
