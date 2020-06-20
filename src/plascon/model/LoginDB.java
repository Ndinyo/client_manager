
package plascon.model;

/**
 *
 * @author abi
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDB {
    
    String conString = "jdbc:postgresql://localhost:5432/Plascon";
    String user = "postgres";
    String passWord = "abel";
    
    ResultSet rs = null;
    
    
     public Boolean addUser(String role, String password, String confirmpassword) {

        String sql_stmnt = "INSERT INTO login(role,password,confirmpassword) VALUES ('" + role + "','"  + password + "','" + confirmpassword + "')";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(conString, user, passWord);

            PreparedStatement pst = conn.prepareStatement(sql_stmnt);

            int rowsAffected = pst.executeUpdate();
            conn.close();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
