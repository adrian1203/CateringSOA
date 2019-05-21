package service;

import domain.Category;
import repository.CategoryRepository;

import java.util.List;

public class CategoryService {


    private CategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository = new CategoryRepository();
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAllCategory();
    }

    public void deleteCategory(Long id) {
        this.categoryRepository.deleteCategory(id);
    }

    public void updateCategory(Category category) {
        this.categoryRepository.updateCategory(category);
    }

    public void createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryRepository.createCategory(category);
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findCategoryById(id);
    }

}
