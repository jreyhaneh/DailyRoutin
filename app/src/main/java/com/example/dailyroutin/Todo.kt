package com.example.dailyroutin

import java.io.Serializable

data class Todo (
    val id:Long? = null,
    val title:String,
    val detail:String,
    val isChecked:Boolean
):Serializable