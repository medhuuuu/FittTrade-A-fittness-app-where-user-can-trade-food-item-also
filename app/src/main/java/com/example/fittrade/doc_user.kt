package com.example.fittrade

class doc_user{
    var companyName: String?= null
    var phone: String?=null
    var img : String?= null
    var id: String?=null

constructor(){}
    constructor(name: String?, phone: String?, img : String? , uid: String? )
    {
        this.companyName = name
        this.phone = phone
        this.img = img
        this.id = uid
    }
}



