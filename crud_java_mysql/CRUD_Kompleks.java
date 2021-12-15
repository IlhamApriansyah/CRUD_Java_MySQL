/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_java_mysql;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author kazuma
 */
public class CRUD_Kompleks {

    //Parameter JDBC untuk koneksi ke Database
    static final String JBDC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Toko";
    static final String USER = "ilham";
    static final String PASS = "123456";

    //Alat pengelola Database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader  input = new BufferedReader(inputStreamReader);

    public static void main(String []args){

        try{
            //Register Driver
            Class.forName(JBDC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while(!conn.isClosed()){
                TampilanMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    static void TampilanMenu(){
        System.out.println("    MENU    ");
        System.out.println("1. Input data ");
        System.out.println("2. Delete data ");
        System.out.println("3. Edit data");
        System.out.println("4. Show data");
        System.out.println("0. Keluar");
        System.out.println("----------");
        System.out.println(" Pilihan nya :");

        try{
            int pilih = Integer.parseInt(input.readLine());

            switch (pilih) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertdata();
                    break;
                case 2:
                    deletedata();
                    break;
                case 3:
                    updatedata();
                    break;
                case 4:
                    showdata();
                    break;
                default:
                    System.out.println("Pilihan salah!!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static void insertdata(){
        try{
            System.out.println("ID : ");
            String ID = input.readLine().trim();
            System.out.println("Menu : ");
            String Menu = input.readLine().trim();
            System.out.println("Harga : ");
            int Harga = input.read();

            //query buat simpan
            String sql = "INSERT INTO Buku_Catatan (ID, Menu, Harga) VALUE ('%s','%s')";
            sql = String.format(sql, ID, Menu, Harga);
            
            //simpan
            stmt.execute(sql);
            
        } catch (Exception e) {
        }
    }

    static void showdata(){
        String sql = "SELECT * from Buku_Catatan";
        try{
            rs = stmt.executeQuery(sql);
            
            System.out.println("-------------------------------");
            System.out.println("-      Data Rumah makan       -");
            System.out.println("-------------------------------");
            while(rs.next()){
                String ID_Menu = rs.getString("ID");
                String Menu = rs.getString("Menu");
                int Harga = rs.getInt("Harga");
                
                System.out.println(String.format("%d. %s -- (%s)", ID_Menu, Menu, Harga));
            }   
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    static void deletedata(){
            try{

                //ambil input dari user
                System.out.println("ID yang mau dihapus :");
                int ID_Menu = Integer.parseInt(input.readLine());

                //query untuk dihapus
                String sql = String.format("DELETE FROM Buku_Catatan WHERE ID_Menu=%d", ID_Menu);

                //hapus data
                stmt.execute(sql);

                System.out.println("Data telah dihapus.... ");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    
    
    static void updatedata(){
        try{
            //ambil input user
            System.out.println("ID yang mau di update :");
            int ID_Menu = Integer.parseInt(input.readLine());
            System.out.println("Menu :");
            String Menu = input.readLine().trim();
            System.out.println("Harga :");
            int Harga = input.read();
            
            //quey update
            String sql = "UPDATE Buku_Catatan SET Menu='%s', Harga='%s' WHERE ID_Menu='%d'";
            
            //update data buku catatan
            stmt.execute(sql);
           
        } catch(Exception e){
        e.printStackTrace();
    } 
    }
}
