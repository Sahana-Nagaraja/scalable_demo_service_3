package com.bits.wilp.demo_service_3.notifier;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

    @Id
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("itemName")
    public String itemName;
    @JsonProperty("customerId")
    public Integer customerId;
    @JsonProperty("price")
    public Integer price;

    public Order(Integer id, String itemName, Integer customerId, Integer price) {
        this.id = id;
        this.itemName = itemName;
        this.customerId = customerId;
        this.price = price;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", itemName='" + itemName + '\'' + ", customerId='" + customerId + '\'' + ", price=" + price + '}';
    }
}