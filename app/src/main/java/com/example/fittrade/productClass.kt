package com.example.fittrade

class productClass {
    var itemName: String?=null
    var price: String?= null
    var companyName: String?= null

    constructor(){}

    constructor(itemName: String?, price: String?, companyName: String?){
        this.itemName= itemName
        this.price = price
        this.companyName= companyName
    }

}