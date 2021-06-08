package service;

import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDao{
//    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product(name,price,quantity,color,description,idcate)VALUES(?,?,?,?,?,?)";
//    private static final String SELECT_PRODUCT_BY_ID = "select * from product where idproduct =?";
//    private static final String SELECT_PRODUCT_BY_NAME = "select * from product where name like ?";
//    private static final String SELECT_ALL_PRODUCTS = "select *from product";
//    private static final String DELETE_PRODUCT_SQL = "delete from product where idproduct = ?";
//    private static final String UPDATE_PRODUCT_SQL = "update product set name= ?, price =?, quantity = ?, color =? , description =? ,idcate = ? where idproduct = ?;";
    private String url = "jdbc:mysql://localhost:3306/examplemodule3";
    private String username = "root";
    private String password = "123456";
    private Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;

    }
    @Override
    public void insert(CategoryDAO categoryDAO) {

    }

    @Override
    public CategoryDAO selectProductByID(int id) {
       return null;
    }

    @Override
    public List<CategoryDAO> selectProductByName(String inputSearch) {
        return null;
    }

    @Override
    public List<CategoryDAO> selectAllProduct() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(CategoryDAO categoryDAO) {
        return false;
    }

    @Override
    public List<Category> findall() {
        List<Category> categories = new ArrayList<>();
        Connection connection =getConnection();
        String sql = "select * from category";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("idcate");
                String name = resultSet.getString("name");
                Category category = new Category(id,name);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> findListById(int id_product) {
        Connection connection =getConnection();
        List<Category> categories = new ArrayList<>();
        String sql = "select name from category join product p on category.idcate = p.idcate\n" +
                "where idproduct = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_product);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Category category = new Category(name);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categories;
    }
}
