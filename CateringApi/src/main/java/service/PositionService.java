package service;

import domain.Category;
import domain.Position;
import repository.CategoryRepository;
import repository.PositionRepository;

import java.util.List;

public class PositionService {

    private PositionRepository positionRepository;
    private CategoryRepository categoryRepository;

    public PositionService() {
        this.positionRepository = new PositionRepository();
    }

    public List<Position> findPositionByCategoryId(Long id) {
        return null;
    }

    public List<Position> findAllPosition(){
        return positionRepository.findAllPosition();
    }

    public void createPosition(String name, String description, Float price, Long categoryId) {
        Position position = new Position();
        position.setDescription(description);
        position.setName(name);
        position.setPrice(price);
        Category c = categoryRepository.findCategoryById(categoryId);
        System.out.println("WARTOSC CATEGORY: " + (c==null?"NULL":"NOT NULL"));
        position.setCategory(c);
        positionRepository.createPosition(position);
    }

    public void deletePosition(Long id) {
        this.positionRepository.deletePosition(id);
    }

    public void updatePosition(Position position) {
        this.positionRepository.updatePosition(position);
    }


}
