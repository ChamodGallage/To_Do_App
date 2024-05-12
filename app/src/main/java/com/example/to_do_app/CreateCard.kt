package com.example.to_do_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_card.create_priority
import kotlinx.android.synthetic.main.activity_create_card.create_title
import kotlinx.android.synthetic.main.activity_create_card.save_button
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database=Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()
        save_button.setOnClickListener{
            if(create_title.text.toString().trim{it<=' '}.isNotEmpty()
                &&create_priority.text.toString().trim{it<=' '}.isNotEmpty())
            {
                var title=create_title.getText().toString()
                var priority=create_priority.getText().toString()
                DataObject.setData(title, priority)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority))
                    log.i("harsh",database.dao().getTasks().toString())
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}