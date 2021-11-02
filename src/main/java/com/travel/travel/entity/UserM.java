package com.travel.travel.entity;

import lombok.Data;

import java.util.List;
@Data
public class UserM {
    private List<String> like;
    private List<Shopping_cart> shoppingCarts;

    public List<Shopping_cart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<Shopping_cart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    private double seem;//相似度

    public List<String> getLike() {
        return like;
    }

    public void setLike(List<String> like) {
        this.like = like;
    }

    public double getSeem() {
        return seem;
    }

    public void setSeem(double seem) {
        this.seem = seem;
    }
}
