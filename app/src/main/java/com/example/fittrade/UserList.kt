package com.example.fittrade

class UserList{
    var userName: String?= null
    var userID: String?=null

    constructor(){}
    constructor(userName: String?, userID: String? )
    {
        this.userName = userName

        this.userID = userID
    }
}