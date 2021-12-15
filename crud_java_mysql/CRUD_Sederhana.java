/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crud_java_mysql;

//Import
import java.sql.*;

/* bisa di import sekaligus
dengan import java.sql.*;
*/

/**
 *
 * @author kazuma
 */
public class CRUD_Sederhana {

    //Parameter JDBC untuk koneksi ke Database
    static final String JBDC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Toko";
    static final String USER = "root";
    static final String PASS = "";

    //Alat pengelola Database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {
        /*melakukan koneksi ke database
        harus dengan try/catch*/

        try{
            //Registrasi Driver
            Class.forName(JBDC_DRIVER);

            //Buat koneksi Database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Buat objek statement
            stmt = conn.createStatement();

            //Query ke Database
            String sql = "SELECT * FROM Buku_Catatan";

            //Eksekusi Query, kemudian simpan hasil di Obj ResultSet
            rs = stmt.executeQuery(sql);

            //Tampilkan hasil Query
            while(rs.next()){
                System.out.println("Restoran Euy");
                System.out.println("---------------------------------");
                System.out.println("ID Menu : " + rs.getInt("ID_Menu"));
                System.out.println("Menu : " +rs.getString("Menu"));
                System.out.println("Harga : " +rs.getInt("Harga"));
            }

            stmt.close();
            conn.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        
    }
    
}
