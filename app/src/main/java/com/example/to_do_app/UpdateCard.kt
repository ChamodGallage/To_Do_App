package com.example.to_do_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_update_card.create_priority
import kotlinx.android.synthetic.main.activity_update_card.create_title
import kotlinx.android.synthetic.main.activity_update_card.delete_button
import kotlinx.android.synthetic.main.activity_update_card.update_button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R. layout.activity_update_card)
        database=Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()
        val pos=intent.getIntExtra("id",-1)
        if(pos!=-1)
        {
            val title=DataObject.getdata(pos).title
            val priority=DataObject.getdata(pos).priority
            create_title.setText(title)
            create_priority.setText(priority)

            delete_button.setOnClickListener{
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(pos+1,create_title.text.toString(),create_priority.text.toString())
                    )
                }
                myIntent()
            }

            update_button.setOnClickListener{
                DataObject.updateData(
                    pos,
                    create_title.text.toString(),
                    create_priority.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos+1,create_title.text.toString(),
                            create_priority.text.toString()
                        )
                    )
                }
                myIntent()
            }
        }
    }
    fun myIntent(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
}