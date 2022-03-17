package FIIS.main.Respuestas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

//Seccion para almacenar la informacion del prospecto segun el apartado:RUC,AGENTE,FISCAL,COMERCIO,ADICIONAL,CONTACTO
//Actualizar estado de solicitud
//Manejar numero de intentos en el ingreso de RUC
@RestController
public class Requisitos {
    @RequestMapping("/Requisitos")
    public String RegistrarRequisitos(@RequestParam String ruc, //validacion de RUC
                                      @RequestParam String razonsocial,
                                      @RequestParam String condicion,
                                      @RequestParam String estadocont,
                                      @RequestParam String departamentoF,
                                      @RequestParam String provinciaF,
                                      @RequestParam String distritoF,
                                      @RequestParam String direccionF,
                                      @RequestParam String departamentoC,
                                      @RequestParam String provinciaC,
                                      @RequestParam String distritoC,
                                      @RequestParam String direccionC
                                  /*    @RequestParam String url_dni,
                                      @RequestParam String url_licencia,
                                      @RequestParam String url_ficha,
                                      @RequestParam String url_vigencia,
                                      @RequestParam String url_constancia,
                                      @RequestParam String url_contrato*/
                                   /* @RequestParam int opcion*/) throws Exception {

        String estado="pendiente";
        Class.forName("org.postgresql.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:postgresql://34.230.110.100:5432/d1d8j80vn6i3ra",
                "mfyhcsjzbxwmvg", "7617d9ab6fef644e27a569932e09ed3d7e9df713e28997b4a127bc070aaa265f");
        Statement st1=conexion.createStatement();
        String InsertaDatos="INSERT INTO empresa values (?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pst=conexion.prepareStatement(InsertaDatos);
        pst.setString(1,ruc);
        pst.setString(2, razonsocial);
        pst.setString(3,condicion);
        pst.setString(4, estadocont);
        pst.setString(5,departamentoF);
        pst.setString(6, provinciaF);
        pst.setString(7,distritoF);
        pst.setString(8,direccionF);
        pst.setString(9,departamentoC);
        pst.setString(10, provinciaC);
        pst.setString(11,distritoC);
        pst.setString(12,direccionC);
        pst.executeUpdate();
        st1.close();
        //Fin de insercion de empresa
        Statement st2=conexion.createStatement();
        String CreaSolicitud="INSERT INTO SOLICITUD(estado,fecha_inicio) VALUES (?,CURRENT_DATE)";
        PreparedStatement pst2=conexion.prepareStatement(CreaSolicitud);
        pst2.setString(1,estado);
        pst2.executeUpdate();
        st2.close();
        //Fin de insercion de solicitud
        String[] tipo_documento={"dni","licencia","ficha_ruc","vigencia_poder","constancia","contrato"};
  //      String[] url_documento={url_dni,url_licencia,url_ficha,url_vigencia,url_constancia,url_contrato};
        Statement st3=conexion.createStatement();
        for (int i=0;i<6;i++) {
            String CreaDoc = "INSERT INTO DOCUMENTO(tipo_documento,fecha_carga) VALUES (?,CURRENT_DATE)";
            PreparedStatement pst3 = conexion.prepareStatement(CreaDoc);
            pst3.setString(1, tipo_documento[i]);
      //      pst3.setString(4, url_documento[i]);
            pst3.executeUpdate();
        }
        st3.close();
        //Fin de insercion de documentos
      /*  Statement st4=conexion.createStatement();
        String IngSolicitante="INSERT INTO SOLICITANTE(ruc) VALUES (?)";
        PreparedStatement pst4=conexion.prepareStatement(IngSolicitante);
        pst4.setString(1,ruc);
        pst4.executeUpdate();
        st4.close();*/
        conexion.close();
        return "Documentos ingresados correctamente. Por favor espere a que el afiliador revise su solicitud.";
    }
}
