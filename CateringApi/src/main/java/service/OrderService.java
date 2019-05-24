package service;

import domain.Order;
import domain.OrderStatus;
import domain.Position;
import repository.CateringUserRepository;
import repository.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {

    private OrderRepository orderRepository;
    private CateringUserRepository cateringUserRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.cateringUserRepository = new CateringUserRepository();
    }

    public void createOrder(Date deliverDate, String additionalInformation, Set<Position> positionList, Long userID){

        Order order = new Order();
        order.setDeliverDate(deliverDate);
        order.setOrderDate(new Date());
        order.setAdditionalInformation(additionalInformation);
        order.setPrice(positionList.stream().map(Position::getPrice).reduce(0f,(o1,o2)->o1+o2));
        order.setCateringUser(cateringUserRepository.findUserById(userID));
        order.setPositionSet(positionList);

        orderRepository.createOrder(order);
    }

    public void updateOrder(Order order){
        orderRepository.updateOrder(order);
    }

    public void changeOrderStatus(String status, Long orderId){

        Order order =orderRepository.findOrderOrderById(orderId);
        order.setOrderStatus(Enum.valueOf(OrderStatus.class,status));
        orderRepository.updateOrder(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAllOrder();
    }

    public List<Order> getOrderByStatus(String status){
        return orderRepository.findAllOrder().stream().filter(order -> order.getOrderStatus()==Enum.valueOf(OrderStatus.class, status)).collect(Collectors.toList());
    }
    public Order getOrderById(Long id){
       return orderRepository.findOrderOrderById(id);
    }

    public List<Order> getFilteredOrderForUser(Long userId, Date start, Date end ){
        return orderRepository.findAllOrder().stream()
                .filter(o-> o.getOrderDate().after(start) && o.getOrderDate().before(end))
                .collect(Collectors.toList());

    }



}
