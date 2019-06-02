package service;

import domain.Category;
import domain.Order;
import domain.Position;
import repository.CategoryRepository;
import repository.OrderRepository;
import repository.PositionRepository;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PositionService {

    private PositionRepository positionRepository;
    private CategoryRepository categoryRepository;
    private OrderRepository orderRepository;
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


    public List<Position> findAllApprovedPosition(){
        return positionRepository.findAllPosition().stream().filter(e-> e.getToApproved()!=true).collect(Collectors.toList());

    }

    public void createPosition(String name, String description, Float price, Long categoryId, Boolean toApproved) {
        logger.info("Wchodzę tu kurwa ");
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


    public List<Position> getPossitionToApproved(){
        return positionRepository.findAllPosition().stream().filter(e-> e.getToApproved().equals(true)).collect(Collectors.toList());

    }
    public void approvePossition(Long id){
        Position position = positionRepository.findPositionById(id);
        position.setToApproved(false);
        this.positionRepository.updatePosition(position);
    }

    public void setDayPosition(Long id){
        Position position = positionRepository.findPositionById(id);
        position.setDayPosition(true);
        positionRepository.updatePosition(position);
    }


    public List<Position> getTopPosition(){

        Map mapPosition = new HashMap<Position,Long>();

        List<Order> orderList = orderRepository.findAllOrder();
        if(orderList!=null){
            orderList.forEach(order->{
                Set<Position> positionList = order.getPositionSet();
                if(positionList!=null){
                    positionList.forEach(position->{
                        if(mapPosition.get(position)!=null){
                            mapPosition.put(position, (Long)mapPosition.get(position)+1);
                        }
                    });
                }

            });
        }

        return null;
    }


}
