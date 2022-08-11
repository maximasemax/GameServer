package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class RecordToBD {

    static final String DbUrl = "jdbc:postgresql://localhost:5432/HistoryRound";
    static final String USER = "postgres";
    static final String PASS = "postgres";

    public void recordResultToDB(String nameUser, String nameBot, int userHp, int botHp
            , String itemUser, String itemBot, int damageToUser, int damageToBot) throws SQLException {
        tryStartDriver();
        Optional<Connection> connection = connectToDataBase();
        Statement statement = connection.get().createStatement();
        int rows = statement.executeUpdate("INSERT INTO history" +
                " VALUES (" + "'" + nameUser + "'" + ", " + "'" + nameBot + "'"  + ", " + "'" + damageToBot + "'" + ", "
                + "'" + damageToUser + "'" + ", " + "'" + itemUser + "'" + ", " + "'" + itemUser + "'" + ");");
        System.out.println(rows);
        System.out.println("Данные отправились");
    }

    private void tryStartDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
    }

    private Optional<Connection> connectToDataBase() {
        try {
            Optional<Connection> connectionOptional = Optional.ofNullable(DriverManager
                    .getConnection(DbUrl, USER, PASS));

            System.out.println("You successfully connected to database now");
            return connectionOptional;
        } catch (SQLException e) {
            System.out.println("Failed to make connection to database");
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
