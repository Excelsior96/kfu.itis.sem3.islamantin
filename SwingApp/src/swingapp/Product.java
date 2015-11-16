/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingapp;

/**
 *
 * @author Ислам
 */
public class Product {
    String name;
    Category category;
    float weight;
    float price;
    
    public Product(String name, Category category, float weight, float price ){
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.price = price;
    }
    
    public String getName(){
        return name;
    }
    
    public Category getCategory(){
        return category;
    }
    
    public float getWeight(){
        return weight;
    }
    
    public float getPrice(){
        return price;
    }
}
