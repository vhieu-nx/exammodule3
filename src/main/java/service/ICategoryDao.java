package service;

import model.Category;
import model.Product;

import java.util.List;

public interface ICategoryDao extends IGeneraDAO<CategoryDAO>{
    List<Category> findall();
    List<Category> findListById(int id_product);
}
