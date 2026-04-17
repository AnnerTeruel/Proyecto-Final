package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.MenuPrincipal;

public class Conexion {
    public static Connection connect()
    {
        Connection conn = null;

        String server = "localhost";
        String database = "tienda";
        String user = "sa";
        String password = "admin";

        String url = "jdbc:sqlserver://" + server + ":1433;"
                + "databaseName=" + database + ";"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=10;"; // A los 10 segundos se saldra y enviara error si no entra

        try
        {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa con sa");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error de conexion: " + e.getMessage());
        }

        return conn;
    }
    
    public static void main(String args[]){
        Conexion.connect();
    }
    
}
