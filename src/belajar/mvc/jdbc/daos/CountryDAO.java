package belajar.mvc.jdbc.daos;

import belajar.mvc.jdbc.models.Country;
import belajar.mvc.jdbc.models.Region;
import belajar.mvc.jdbc.tools.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {


  public List<Country> getAllCountry(){
    List<Country> countries = new ArrayList<>();

    try (Connection connection = DBConnection.getConnection()){

      Statement statement = connection.createStatement();
      String SQL = "SELECT c.id, c.name, r.name FROM country AS c JOIN region AS r ON (r.id = c.region)";

      ResultSet resultSet = statement.executeQuery(SQL);

      while (resultSet.next()) {
        Country region = new Country(
                resultSet.getString("c.id"),
                resultSet.getString("c.name"),
                new Region(resultSet.getString("r.name")));

        countries.add(region);
      }

      // melakukan close
      resultSet.close();
      statement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }

    return countries;
  }

  public Country findById(String id){

    try (Connection connection = DBConnection.getConnection()){

      String SQL = "SELECT c.id, c.name, r.name FROM country AS c " +
              "JOIN region AS r ON (r.id = c.region) WHERE c.id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()){
        return new Country(
                resultSet.getString("c.id"),
                resultSet.getString("c.name"),
                new Region(resultSet.getString("r.name"))
        );
      }

      resultSet.close();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }

    return null;
  }
  public boolean searchName(String name) {
    try (Connection connection = DBConnection.getConnection()){

      String SQL = "SELECT * FROM country WHERE name = ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()){
        return true;
      }

      resultSet.close();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }

    return false;
  }

  public void create(Country country) {
    try (Connection connection = DBConnection.getConnection()){

      String SQL = "INSERT INTO country (id, name, region) VALUES (?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, country.getId());
      preparedStatement.setString(2, country.getName());
      preparedStatement.setInt(3, country.getRegion().getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }
  }

  public void delete(String id) {
    try (Connection connection = DBConnection.getConnection()){

      String SQL = "DELETE FROM country WHERE id = ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, id);
      preparedStatement.executeUpdate();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }
  }

  public void update(Country country){

    try (Connection connection = DBConnection.getConnection()){

      String SQL = "UPDATE country SET name = ?, region = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, country.getName());
      preparedStatement.setInt(2, country.getRegion().getId());
      preparedStatement.setString(3, country.getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }
  }

  public List<Country> searchByName(String name){

    List<Country> countries = new ArrayList<>();
    try (Connection connection = DBConnection.getConnection()){

      String SQL = "SELECT c.id, c.name, r.name FROM country AS c " +
              "JOIN region AS r ON (r.id = c.region) WHERE c.name LIKE ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, "%" +  name + "%");
      ResultSet resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        countries.add(new Country(
                resultSet.getString("c.id"),
                resultSet.getString("c.name"),
                new Region(resultSet.getString("r.name"))));
      }

      resultSet.close();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }

    return countries;
  }
}
