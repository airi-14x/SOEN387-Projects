/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcdemoproject;

import java.sql.*;

/**
 *
 * @author Airi
 */
public class JDBCDemoProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "12345678";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?serverTimezone=UTC", user, pass);
            // 2. Create a statement
            myStmt = myConn.createStatement();
            // 3. Execute SQL query
            myRs = myStmt.executeQuery("select * from employees");
            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", "
                        + myRs.getString("first_name"));
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }

    }

}
