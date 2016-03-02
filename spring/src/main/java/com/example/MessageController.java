package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessageController {


    //private int id=0;
    private final String url="jdbc:mysql://localhost:3306/movies";
    private final String username="root";
    private final String password="root";
    private Connection conn;
    private Statement stmt=null;





    @RequestMapping("/greeting")
    public List<Message> greeting(@RequestParam(value = "title", defaultValue = "") String title,
                                  @RequestParam(value = "message", defaultValue = "") String message,
                                  @RequestParam(value = "index", defaultValue = "") String index,
                                  @RequestParam(value = "delete", defaultValue = "") String delete) {


        // create a sql connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        // return list from sql query
        if (!index.equals("")&&!title.equals("")){
            return update(index,title,message);
        }
        else if(!index.equals("")&&!delete.equals("")){
            return remove(index);
        }
        else if (title.equals("")){
            return connect();
        }
        else {
            return insert(title,message);
        }

    }

    @RequestMapping("/count")
    public Count count(){
        // create a sql connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return new Count(connect().size());

    }



    public List<Message> connect(){

        List<Message> listFromSql=new ArrayList<>();


        try {

            String sql;
            sql = "SELECT * FROM movies_list";
            ResultSet rs = stmt.executeQuery(sql);


            while(rs.next()){
                //Retrieve by column name
                long id  = rs.getInt("id");
                String title=rs.getString("name");
                String message=rs.getString("url");
                listFromSql.add(new Message(id,title,message) );

            }
            conn.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


        return listFromSql;
    }

    public List<Message> insert(String title,String message){

        try {

            String sql;
            sql = "INSERT INTO messages (`id`, `title`, `message`) VALUES (NULL, '"+title+"', '"+message+"')";
            stmt.executeUpdate(sql);
            return connect();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return null;

    }

    public List<Message> update(String id,String title,String message){

        try {

            String sql;
            sql = "UPDATE messages SET `title`='"+title+"', `message` = '"+message+"' WHERE `id` ="+id;
            stmt.executeUpdate(sql);
            return connect();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return null;

    }

    public List<Message> remove(String id){

        try {

            String sql;
            sql = "DELETE FROM messages WHERE `id`="+id;
            stmt.executeUpdate(sql);
            return connect();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return null;

    }










}
