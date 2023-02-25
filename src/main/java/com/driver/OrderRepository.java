package com.driver;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
@Repository
public class OrderRepository {
    HashMap<String,Order> orderMap;
    HashMap<String,DeliveryPartner>partnerMap;
    HashMap<String,List<String>>partnerOrderMap;
    public OrderRepository(){
        this.orderMap=new HashMap<String,Order>();
        this.partnerMap=new HashMap<String,DeliveryPartner>();
        this.partnerOrderMap=new HashMap<String, List<String>>();
    }
    public void saveOrder(Order order){
        orderMap.put(order.getId(),order);
    }
    public void savePartner(DeliveryPartner partner){
        partnerMap.put(partner.getId(), partner);
    }
    public void saveOrderPartner(String orderID,String partnerID){
        if(orderMap.containsKey(orderID)&&partnerMap.containsKey(partnerID)){
            orderMap.put(orderID,orderMap.get(orderID));
            partnerMap.put(partnerID,partnerMap.get(partnerID));
            List<String>orderlist=new ArrayList<>();
            if(partnerOrderMap.containsKey(partnerID)){
                orderlist=partnerOrderMap.get(partnerID);
            }
            orderlist.add(orderID);
            partnerOrderMap.put(partnerID,orderlist);
        }
    }
    public Order findOrder(String orderID){
        return orderMap.get(orderID);
    }
    public DeliveryPartner findPartner(String partnerID){
        return partnerMap.get(partnerID);
    }
    public Integer findOrderNumber(String partnerID){
       //int count=0;
       List<String>countOrders=new ArrayList<>();
       countOrders=partnerOrderMap.get(partnerID);
       if(countOrders==null){
           return 0;
       }
       return countOrders.size();
    }
    public List<Order> orders(String partnerID){
        List<Order>order=new ArrayList<>();
        List<String>orderID=partnerOrderMap.get(partnerID);
        if(orderID!=null){
            for(String oId:orderID){
              order.add(orderMap.get(oId));
            }
        }
        return order;
    }
    public List<Order> allOrders(){
        return new ArrayList<>(orderMap.values());
    }
    public Integer countUnAssignedOrder(){
        int count=0;
        for(Order order:orderMap.values()){
            if(order.getId()==null){
               count++;
            }
        }
        return count;
    }
    public Integer countUnDeliveredOrder(String time,String partnerID){
       int count=0;
       List<Order>order=new ArrayList<>();
       order=orders(partnerID);
       if(order!=null){
           for(Order orders:order){
               if(Integer.toString(orders.getDeliveryTime()).compareTo(time) > 0){
                   count++;
               }
           }
       }
       return count;
    }
    public String getLastDeliveryTime(String partnerID){
        List<Order> orders =orders(partnerID);
        if (orders != null && !orders.isEmpty()) {
            Order lastOrder = orders.get(orders.size() - 1);
            return Integer.toString(lastOrder.getDeliveryTime()) ;
        }
        return null;
    }
    public void deletePartner(String partnerID){
        partnerMap.remove(partnerID);
        partnerOrderMap.remove(partnerID);
    }
    public void deleteOrder(String orderID){
        orderMap.remove(orderID);
        for (List<String> orderIds : partnerOrderMap.values()) {
            orderIds.remove(orderID);
        }
    }

}
