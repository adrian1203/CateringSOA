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


    public List<Category> translateCategory(String language, List<Category> listCategory) {
        if (language.equals("en-us")) {
            listCategory.stream().forEach(elem -> {
                elem.setName(elem.getName() + " English");
                elem.setDescription(elem.getDescription() + " English");
            });
        }
        if (language.equals("de")) {
            listCategory.stream().forEach(elem -> {
                elem.setName(elem.getName() + " German");
                elem.setDescription(elem.getDescription() + " German");
            });
        }
        return listCategory;

    }

    public Category translateCategory(String language, Category category) {
        if (language.equals("en-us")) {
            category.setName(category.getName() + " English");
            category.setName(category.getName() + " English");
        }
        if (language.equals("de")) {
            category.setName(category.getName() + " German");
            category.setDescription(category.getDescription() + " English");
        }
        return category;

    }

}
