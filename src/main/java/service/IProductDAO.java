package service;

import model.Category;
import model.Product;

import java.util.List;

public interface IProductDAO  extends IGeneraDAO<Product>{
    List<Category> findall();
    List<Category> findListById(int id_product);

}
