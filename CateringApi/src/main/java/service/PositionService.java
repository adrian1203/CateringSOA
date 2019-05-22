package service;

import domain.Category;
import domain.Position;
import repository.CategoryRepository;
import repository.PositionRepository;

import java.util.List;
import java.util.logging.Logger;

public class PositionService {

    private PositionRepository positionRepository;
    private CategoryRepository categoryRepository;
    Logger logger = Logger.getLogger(PositionService.class.getName());


    public PositionService() {
        this.positionRepository = new PositionRepository();
        this.categoryRepository = new CategoryRepository();
    }

    public List<Position> findPositionByCategoryId(Long id) {
        return null;
    }

    public List<Position> findAllPosition(){
        return positionRepository.findAllPosition();
    }

    public void createPosition(String name, String description, Float price, Long categoryId, Boolean toApproved) {
        Position position = new Position();
        position.setDescription(description);
        position.setName(name);
        position.setPrice(price);
        position.setToApproved(toApproved);
        Category category = categoryRepository.findCategoryById(categoryId);
        position.setCategory(category);
        positionRepository.createPosition(position);
    }

    public Position getPositionById(Long id){
        return positionRepository.findPositionById(id);
    }

    public void deletePosition(Long id) {
        this.positionRepository.deletePosition(id);
    }

    public void updatePosition(Position position) {
        this.positionRepository.updatePosition(position);
    }


}
