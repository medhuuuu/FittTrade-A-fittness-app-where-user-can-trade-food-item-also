package com.example.fittrade

import android.content.BroadcastReceiver

class message {
    var message : String?= null
    var senderid : String?=null
    var receiverid : String?=null


    constructor(){}

    constructor( message : String?,senderid : String?, receiverid: String?){

        this.message = message
        this.senderid = senderid
        this.receiverid = receiverid

    }
}