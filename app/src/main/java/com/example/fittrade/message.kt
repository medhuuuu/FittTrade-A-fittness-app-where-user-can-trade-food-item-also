package com.example.fittrade

class message {
    var message : String?= null
    var senderid : String?=null

    constructor(){}

    constructor( message : String?,senderid : String? ){

        this.message = message
        this.senderid = senderid
    }
}