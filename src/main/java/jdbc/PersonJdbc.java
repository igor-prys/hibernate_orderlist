package jdbc;

import pojo.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PersonJdbc {
    private final String URL = "jdbc:mysql://localhost/hibernate_11_04";
    private final String LOGIN = "root";
    private final String PASSWORD = "123456";

    public void createTable() {
        try {
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
                Statement statement = connection.createStatement();
                String query = "create table Persons(" +
                        "id int primary key auto_increment," +
                        "firstname varchar(50) not null," +
                        "lastname varchar(50) nut null);";
                statement.executeUpdate(query);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addPerson() {
        try {
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
                System.out.println("Input firstname");
                Scanner scan = new Scanner(System.in);
                String firstname = scan.next();
                System.out.println("Input lastname");
                String lastname = scan.next();
                String query = "insert into Persons (firstname,lastname) values(?,?);";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, firstname);
                ps.setString(2, lastname);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePerson() {
        try {
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
                System.out.println("Insert id");
                Scanner scan = new Scanner(System.in);
                Long id = scan.nextLong();
                String query = "delete from Persons where id=?;";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setLong(1, id);
                ps.execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<Person> getPerson() {
        try {
            try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
                System.out.println("Insert id");
                Scanner scan = new Scanner(System.in);
                Long id = scan.nextLong();
                String query = "select from Persons where id=?;";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                Person person = new Person();
                person.setId(id);
                person.setFirstname(rs.getString("firstname"));
                person.setLastname(rs.getString("lastname"));
                return Optional.of(person);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
    public List<Person> getAllPerson(){
        List<Person>personList=new ArrayList<>();
        try{
            try(Connection connection=DriverManager.getConnection(URL,LOGIN,PASSWORD)){
                String query="Select * from persons;";
                Statement statement=connection.createStatement();
                ResultSet rs=statement.executeQuery(query);
                while (rs.next()){
                    Person person=new Person();
                    person.setId(rs.getLong("id"));
                    person.setFirstname(rs.getString("firstname"));
                    person.setLastname(rs.getString("lastname"));
                    personList.add(person);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return personList;
    }
}
