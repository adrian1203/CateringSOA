package service;

import domain.*;
import javafx.geometry.Pos;
import repository.CateringUserRepository;
import repository.OrderRepository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {

    private OrderRepository orderRepository;
    private CateringUserRepository cateringUserRepository;
    private PermanentOrderService permanentOrderService;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        this.cateringUserRepository = new CateringUserRepository();
        this.permanentOrderService = new PermanentOrderService();
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
        return orderRepository.findAllOrder().stream().filter(order -> order.getOrderStatus().equals(Enum.valueOf(OrderStatus.class, status))).collect(Collectors.toList());
    }
    public Order getOrderById(Long id){
       return orderRepository.findOrderOrderById(id);
    }

    public List<Order> getFilteredOrderForUser(Long userId, Date start, Date end ){
        return orderRepository.findAllOrder().stream()
                .filter(o-> o.getOrderDate().after(start) && o.getOrderDate().before(end))
                .filter(o->o.getCateringUser().getId().equals(userId))
                .collect(Collectors.toList());

    }

    public List<Order> getAllOrderForUser(Long userId){
        return orderRepository.findAllOrder().stream()
                .filter(o->o.getCateringUser().getId().equals(userId)).collect(Collectors.toList());
    }




    public Bill generateBill(Long userId, Date start, Date end){
        List<Order> orderList = getFilteredOrderForUser(userId, start, end);
        //List<PermanentOrder> permanentOrderList = permanentOrderService.getFilteredOrderForUser(userId, start, end);

        List<String> tmp = new LinkedList<>();
        Bill bill = new Bill();
        orderList.forEach(elem->{
            elem.getPositionSet().forEach(position -> {
                tmp.add(position.getName());
            });
        });
        bill.setOrderedPosition(tmp);
        bill.setGenertedDate(new Date());
        bill.setStartDate(start);
        bill.setEndDate(end);
        bill.setPrice(countAll(orderList));
        if(bill.getPrice().equals(0F)){
            bill.setAdditionalInfomration("Brak zamówień w wybranych terminie");
        }
        return bill;
    }

    public Float countAll(List<Order> position){

        return (float)position.stream().mapToDouble(Order::getPrice).sum();
    }



}
