package com.driver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.HashMap;
@Service
public class OrderService {
@Autowired
    OrderRepository orderrepository;
    public void add_Order(Order order){
        orderrepository.saveOrder(order);
    }
    public void add_partner(String partnerId){
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        orderrepository.savePartner(partner);
    }
    public void add_orderPartner(String orderId,String partnerId){
       orderrepository.saveOrderPartner(orderId,partnerId);
    }
    public Order getOrder(String orderId){
        return orderrepository.findOrder(orderId);
    }
    public DeliveryPartner getPartner(String deliveryId){
        return orderrepository.findPartner(deliveryId);
    }
    public Integer getOrderNumber(String partnerId){
        return orderrepository.findOrderNumber(partnerId);
    }
    public List<Order> orders(String partnerId){
        return orderrepository.orders(partnerId);
    }
    public List<Order> ordersAll(){
      return orderrepository.allOrders();
    }
    public Integer countUnAssigned(){
        return orderrepository.countUnAssignedOrder();
    }
    public Integer countUndelivered(String time,String partnerId){
        return orderrepository.countUnDeliveredOrder(time,partnerId);
    }
    public String getLastDelivery(String partnerId){
        return orderrepository.getLastDeliveryTime(partnerId);
    }
    public void deletePartner(String partnerId){
        orderrepository.deletePartner(partnerId);
    }
    public void deleteOrder(String orderId){
        orderrepository.deleteOrder(orderId);
    }
}
