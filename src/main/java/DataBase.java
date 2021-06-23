import java.sql.*;

public class DataBase {
    static Connection connection;
    Statement statement;
    ResultSet rs;
    public void save(int livello){
        try {
            connect();
            statement.executeUpdate("DELETE FROM data where livello > 0;");
            statement.executeUpdate("INSERT INTO data(livello) VALUES ('" + livello + "') ");
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            connectClose();
        }


    }

    public  int read(){
        int r = 0;
        try {
            connect();
            rs = statement.executeQuery("SELECT * FROM data");
            r = rs.getInt(1);
            System.out.println(r);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connectClose();
        }

        return r;
    }

    public void connect() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Save/savestate.db");
            statement = connection.createStatement();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.executeQuery("SELECT * FROM data");
        } catch (SQLException e){
            System.out.println("RESETTO");
            statement.executeUpdate("DROP TABLE IF EXISTS data");
            statement.executeUpdate("CREATE TABLE data(" + "livello INTEGER)");
            statement.executeUpdate("INSERT INTO data(livello) VALUES ('1') ");
        }

    }
    public void connectClose(){
        try {
            statement.close();
            connection.close();
        } catch(SQLException e){
            throw new RuntimeException();
        }
    }
}
