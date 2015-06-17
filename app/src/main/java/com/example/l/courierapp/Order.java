package com.example.l.courierapp;

/**
 * Created by L on 2015/6/17.
 */
public class Order {

    public String id,address,time,phonenum,remark;
    public float price;

    public void Add(String i,String a,String t,String p,String r,float pr)
    {
        id = i;
        address = a;
        time = t;
        phonenum = p;
        remark = r;
        price = pr;
    }
}
