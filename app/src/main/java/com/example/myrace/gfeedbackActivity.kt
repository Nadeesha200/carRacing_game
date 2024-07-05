package com.example.myrace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class gfeedbackActivity : AppCompatActivity() {

    //Click feedback button and Go To feedback page
    private lateinit var AddBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gfeedback)

        //Click feedback button and Go To feedback page
        AddBtn = findViewById(R.id.aBtn)


        //Click feedback button and Go To feedback page
        AddBtn.setOnClickListener{
            val intent = Intent(applicationContext, voucherActivity::class.java)
            startActivity(intent)
        }

    }
}