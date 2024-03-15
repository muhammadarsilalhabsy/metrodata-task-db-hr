package belajar.mvc.jdbc.daos;

import belajar.mvc.jdbc.models.Region;
import belajar.mvc.jdbc.tools.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {

  public List<Region> getAll(){

    List<Region> regions = new ArrayList<>();

    try (Connection connection = DBConnection.getConnection()){

      Statement statement = connection.createStatement();
      String SQL = "SELECT * FROM region";

      ResultSet resultSet = statement.executeQuery(SQL);

      while (resultSet.next()) {
        Region region = new Region(
                resultSet.getInt("id"),
                resultSet.getString("name"));

        regions.add(region);
      }

      // melakukan close
      resultSet.close();
      statement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }

    return regions;
  }

  public void create(Region region){

    try (Connection connection = DBConnection.getConnection()){

      String SQL = "INSERT INTO region (name) VALUES (?)";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, region.getName());
      preparedStatement.executeUpdate();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }
  }

  public Region findById(int id){

    try (Connection connection = DBConnection.getConnection()){

      String SQL = "SELECT * FROM region WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()){
        return new Region(
                Integer.parseInt(resultSet.getString("id")),
                resultSet.getString("name"));
      }

      resultSet.close();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }

    return null;
  }

  public List<Region> searchByName(String name){

    List<Region> regions = new ArrayList<>();
    try (Connection connection = DBConnection.getConnection()){

      String SQL = "SELECT * FROM region WHERE name LIKE ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, "%" +  name + "%");
      ResultSet resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        regions.add(new Region(
                Integer.parseInt(resultSet.getString("id")),
                resultSet.getString("name")));
      }

      resultSet.close();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }

    return regions;
  }

  public boolean searchName(String name){

    try (Connection connection = DBConnection.getConnection()){

      String SQL = "SELECT * FROM region WHERE name = ? ";
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

  public void delete(int id){

    try (Connection connection = DBConnection.getConnection()){

      String SQL = "DELETE FROM region WHERE id = ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }
  }

  public void update(Region region){

    try (Connection connection = DBConnection.getConnection()){

      String SQL = "UPDATE region SET name = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);

      preparedStatement.setString(1, region.getName());
      preparedStatement.setInt(2, region.getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();

    }catch (SQLException e){
      System.err.println("Error :" + e.getMessage());
    }
  }
}
