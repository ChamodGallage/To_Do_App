package com.example.to_do_app

object DataObject {
    var listdata= mutableListOf<CardInfo>()

    fun setData(title: String,priority:String){
        listdata.add(CardInfo(title,priority))
    }

    fun getAllData():List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getdata(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title: String,priority: String)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
    }
}