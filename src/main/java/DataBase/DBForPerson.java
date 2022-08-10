package DataBase;

import model.impl.Item;
import model.impl.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBForPerson {
    static final String DbUrl = "jdbc:postgresql://localhost:5432/Person";
    static final String USER = "postgres";
    static final String PASS = "postgres";

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

    public List<Person> getPersonsFromDataBase() throws SQLException {
        tryStartDriver();
        Optional<Connection> connection = connectToDataBase();
        if (connection.isPresent()) {
            return doGetIPersons(connection.get());
        }
        return new ArrayList<>();
    }

    public List<Person> doGetIPersons(Connection connection){
        List<Person> personList = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM persons");
            while (resultSet.next()) {
                Person person = new Person();
                System.out.println("ID: " + resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setHp(resultSet.getInt("hp"));
                person.setAttackSkill(resultSet.getInt("damage_skill"));
                person.setDefenceSkill(resultSet.getInt("defence_skill"));
                personList.add(person);
            }
        } catch (SQLException e) {
            System.out.println("Holly shit");
            ;
        }
        return personList;
    }

}
