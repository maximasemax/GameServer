package DataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class RecordToBD {

    static final String DbUrl = "jdbc:postgresql://localhost:5432/HistoryRound";
    static final String USER = "postgres";
    static final String PASS = "postgres";

    public void recordResultToDB(String userName, String botName, int userHp, int botHp
            , String userItem, String botItem, int damageToUser, int damageToBot) throws IOException, SQLException {
        //TODO Использовать контейнер для входных данных в методе
        tryStartDriver();
        Optional<Connection> connection = connectToDataBase();
        if (connection.isPresent()) {
            Statement statement = connection.get().createStatement();
            //TODO Почитать prepareStatement
            int rows = statement.executeUpdate("INSERT INTO history" +
                    " VALUES (" + "'" + userName + "'" + ", " + "'" + botName + "'" + ", " + "'" + damageToBot + "'" + ", "
                    + "'" + damageToUser + "'" + ", " + "'" + userItem + "'" + ", " + "'" + botItem + "'" + ");");
            System.out.println(rows);
            System.out.println("Данные отправились");
        } else {
            throw new IOException("Shit Happens");
        }
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
