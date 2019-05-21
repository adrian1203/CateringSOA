package service;

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
    public void createPosition(String name, String description, Float price, Long categoryId){
        Position position= new Position();
        position.setDescription(description);
        position.setName(name);
        position.setPrice(price);
        position.setCategory(categoryRepository.findCategoryById(categoryId));
        positionRepository.createPosition(position);
    }


}
