package service;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product(name,price,quantity,color,description,idcate)VALUES(?,?,?,?,?,?)";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where idproduct =?";
    private static final String SELECT_PRODUCT_BY_NAME = "select * from product where name like ?";
    private static final String SELECT_ALL_PRODUCTS = "select *from product";
    private static final String DELETE_PRODUCT_SQL = "delete from product where idproduct = ?";
    private static final String UPDATE_PRODUCT_SQL = "update product set name= ?, price =?, quantity = ?, color =? , description =? ,idcate = ? where idproduct = ?;";
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
    public void insert(Product product) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory_id());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public Product selectProductByID(int id) {
        Product product = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("idcate");
                product = new Product(id, name, price, quantity, color, description, category_id);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> selectProductByName(String inputSearch) {
        String search = "%" + inputSearch + "%";
        List<Product> products = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, search);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idproduct");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("idcate");

                products.add(new Product(id, name, price, quantity, color, description, category_id));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idproduct");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int category_id = rs.getInt("idcate");
                products.add(new Product(id, name, price, quantity, color, description, category_id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean delete(int id) {
        boolean checkDelete = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);
            preparedStatement.setInt(1, id);
            checkDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return checkDelete;
    }

    @Override
    public boolean update(Product product) {
        boolean checkUpdate = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory_id());
            preparedStatement.setInt(7, product.getId());
            checkUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return checkUpdate;
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
