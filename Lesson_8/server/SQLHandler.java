import java.sql.*;

public class SQLHandler {
    
    private static Connection connection;
    private static Statement stmt;
    private static final String CONN_STRING = System.getProperty("user.dir") + "\\src\\hw8\\server\\database.db";
    
    public static void connect()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + CONN_STRING);
            System.out.println("Connect");
            stmt = connection.createStatement();
        }
        catch(Exception ex)
        {
            ex.getStackTrace();
            System.out.println("Havnt connection");
        }
    }
    
    
    public static String getNickByLogPass(String login, String password)
    {
        try {

            ResultSet rs = stmt.executeQuery("SELECT nickname FROM users WHERE login='" + login + "' AND password='" + password + "';");
        
            if(rs.next())
            {
               return rs.getString("nickname");
            }
        
        
        } catch (SQLException ex) {
            ex.getStackTrace();
        }

       return null;
    }
    
    
    public static void disconnect()
    {
        try
        {
            connection.close();
        }
        catch(Exception ex)
        {
            ex.getStackTrace();
        }
    }//disconnect()
}//public class SQLHandler