package com.example.to_do_app
@Database(entities = [Entity::class],version=1)
abstract class myDatabase : RoomDatabase(){
    abstract fun dao():DAO

}