package com.example.myrace

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginAfterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_after)

        val editFullName : TextView = findViewById(R.id.full_name)
        val editVcode : TextView = findViewById(R.id.vcode)
        val btnSave : Button = findViewById(R.id.btn_savee)

        val sharedPref = applicationContext?.getSharedPreferences("my-app", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        //data
        btnSave.setOnClickListener{
            if(editFullName.length() > 0 && editVcode.length() > 0) {
                editor?.putString("full_name", editFullName.text.toString())
                editor?.putInt("vcode", editVcode.text.toString().toInt())
                editor?.apply()

                finish()
            } else {
                Toast.makeText(applicationContext,"save data successfully", Toast.LENGTH_SHORT).show()
            }
        }


    }
}