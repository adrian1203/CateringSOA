package service;

import domain.Order;
import domain.PermanentOrder;
import domain.PermanetOrderDate;
import domain.Position;
import repository.CateringUserRepository;
import repository.PermanentOrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermanentOrderService {

    public PermanentOrderService() {
        permanentOrderRepository = new PermanentOrderRepository();
        cateringUserRepository = new CateringUserRepository();
    }

    private PermanentOrderRepository permanentOrderRepository;
    private CateringUserRepository cateringUserRepository;


    public List<PermanentOrder> getAllOrders(){
        return permanentOrderRepository.findAllPermanentOrder();
    }


    public PermanentOrder getOrderById(Long id){
        return permanentOrderRepository.findPermanentOrderById(id);
    }

    public void updateOrder(PermanentOrder order){
        permanentOrderRepository.updatePermanentOrder(order);
    }

    public void createOrder(String additionalInformation, Set<Position>positionList, Long userID, Set<PermanetOrderDate> permanetOrderDateList){

        PermanentOrder order = new PermanentOrder();
        order.setOrderDate(new Date());
        order.setAdditionalInformation(additionalInformation);
        order.setCateringUser(cateringUserRepository.findUserById(userID));
        order.setDeliverDateSet(permanetOrderDateList);
        order.setPositionSet(positionList);
        permanentOrderRepository.createPermanentOrder(order);
    }
    public void updateStatusPermementOrder(Date deliveryDate, Long orderId){
       //PermanentOrder permanentOrder= permanentOrderRepository.findPermanentOrderById(orderId);
      // permanentOrder.getDeliverDateSet().stream().map().filter()
    }
    public List<PermanentOrder> getFilteredOrderForUser(Long userId, Date start, Date end ){
        return permanentOrderRepository.findAllPermanentOrder().stream()
                .filter(o-> o.getOrderDate().after(start) && o.getOrderDate().before(end))
                .collect(Collectors.toList());

    }
}
