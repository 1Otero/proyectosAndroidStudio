package com.example.listproducts.entitys;

public class Product {

    private int id;

    private String name;

    private int cantidad;

    private int precio;

    public Product(){}

    public Product(int id, String name, int cantidad, int precio){
         this.id= id;
         this.name= name;
         this.cantidad= cantidad;
         this.precio= precio;
    }

    public void setId(int id){
        this.id= id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name= name;
    }

    public String getName(){
        return this.name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}

