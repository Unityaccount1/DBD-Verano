package FIIS.main.Respuestas;
//Seccion para validar un determinado al usuario

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class Validacion {
    @RequestMapping("/Validar")
    public String ValidaUsuario(@RequestParam String usuario,@RequestParam String clave,@RequestParam int opcion) throws Exception {
        String usuario2,clave2;
        int contador=0;

        //ArrayList<ObtenerUsuario> arreglousuario=new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        Connection conexion=DriverManager.getConnection("jdbc:postgresql://1.1.1.1","ola","ola2");
        Statement st1=conexion.createStatement();
        switch(opcion) {
            case 1:
                String datovalidar = "select id,clave from usuario";
                ResultSet rs = st1.executeQuery(datovalidar);
                while (contador != 1 || rs.next()) {
                    usuario2 = rs.getString(1);
                    clave2 = rs.getString(2);

                    if (usuario2 == usuario && clave2 == clave) {
                        contador = 1;
                        return "Validacion exitosa";
                    }

                    //ObtenerUsuario atributos;
                    //atributos=ObjetoUsuario(usuario2,clave2);
                    //arreglousuario.add(atributos);
                }
                rs.close();
                st1.close();
            case 2:
                Statement st2 = conexion.createStatement();
                String empleadovalidar = "select id,clave from usuario where nombre='afiliador'";
                ResultSet rs2 = st2.executeQuery(empleadovalidar);
                while (contador != 1 || rs2.next()) {
                    usuario2 = rs2.getString(1);
                    clave2 = rs2.getString(2);
                    if (usuario2 == usuario && clave2 == clave) {
                        contador = 1;
                        return "Validacion exitosa";
                    }

                    //ObtenerUsuario atributos;
                    //atributos=ObjetoUsuario(usuario2,clave2);
                    //arreglousuario.add(atributos);
                }
                rs2.close();
                st2.close();
            case 3:
                Statement st3 = conexion.createStatement();
                String empleadovalidar2 = "select id,clave from usuario where nombre='administrador'";
                ResultSet rs3 = st3.executeQuery(empleadovalidar2);
                while (contador != 1 || rs3.next()) {
                    usuario2 = rs3.getString(1);
                    clave2 = rs3.getString(2);
                    if (usuario2 == usuario && clave2 == clave) {
                        contador = 1;
                        return "Validacion exitosa";
                    }

                    //ObtenerUsuario atributos;
                    //atributos=ObjetoUsuario(usuario2,clave2);
                    //arreglousuario.add(atributos);
                }
                rs3.close();
                st3.close();
        }
        return "Usuario o clave incorrectos";
    }

    //Por implementar validacion del afiliador dando valores para pasar el flujo desde el html recomendable: int=1,2,3


    /*public ObtenerUsuario ObjetoUsuario(String usuario,String clave){
        ObtenerUsuario objuser=new ObtenerUsuario();
        objuser.setUsuario(usuario);
        objuser.setClave(clave);
        return objuser;
    }
    */
}
