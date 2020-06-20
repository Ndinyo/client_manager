
package plascon.model;

/**
 *
 * @author abi
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ClientDB {
    
    String conString = "jdbc:postgresql://localhost:5432/Plascon";
    String user = "postgres";
    String passWord = "abel";
    
    ResultSet rs = null;
    
    public Boolean addClient(String name, String telephone, String location, String idno, String date, String comments) {

        String sql_stmnt = "INSERT INTO clients(name,telephone,location,id_no,date,comments) VALUES ('" + name + "','" + telephone +"','"  + location + "','" + idno + "','" + date + "','" + comments +"')";

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
    
    public DefaultTableModel getClients() {
        //Add columns to TableModel
        DefaultTableModel dm = new DefaultTableModel();

        dm.addColumn("Client id");
        dm.addColumn("Name");
        dm.addColumn("Telephone");
        dm.addColumn("Location");
        dm.addColumn("ID NO");
        dm.addColumn("Date");
        dm.addColumn("Comments");

        //Select
        String sql = "SELECT * FROM clients";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(conString, user, passWord);

            //Prepared statement
           
            PreparedStatement st = conn.prepareStatement(sql);

            //st.execute();
            
            ResultSet rt = st.executeQuery();

            while (rt.next()) {
                String id = rt.getString(1);
                String name = rt.getString(2);
                String telephone = rt.getString(3);
                String location = rt.getString(4);
                String idno = rt.getString(5);
                String date = rt.getString(6);
                String comments = rt.getString(7);
               
                dm.addRow(new String[]{id,name,telephone,location,idno,date,comments});
             
            }
            return dm;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Boolean delete(String id) {
        //SQL

        String sql = "DELETE FROM clients WHERE c_id ='" + id + "'";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(conString, user, passWord);

            PreparedStatement st = conn.prepareStatement(sql);

            //EXecute
            st.execute();

            conn.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
      public Boolean update(String id, String name, String telephone, String location, String idno, String date, String comments) {
        String sql = "UPDATE clients SET name = '" + name + "',telephone='" + telephone + "',location='" + location + "',id_no='" + idno + "',date='" + date + "',comments='" + comments + "' WHERE c_id ='" + id + "'";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(conString, user, passWord);

            //Statement
            PreparedStatement st = conn.prepareStatement(sql);

            st.execute();

            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
