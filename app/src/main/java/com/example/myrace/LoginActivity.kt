package com.example.myrace

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    private lateinit var textFullName: TextView
    private lateinit var textVcode: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button
    private lateinit var btnNext: Button

    private var sharedPref: SharedPreferences?= null

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnNext = findViewById(R.id.btn_next)
        btnEdit = findViewById(R.id.btn_edit)
        btnDelete = findViewById(R.id.btn_delete)
        textFullName = findViewById(R.id.full_name)
        textVcode = findViewById(R.id.vcode)

        sharedPref = applicationContext?.getSharedPreferences("my-app",Context.MODE_PRIVATE)

        //Click Edit Button goto Edit Page
        btnEdit.setOnClickListener{
            val intent = Intent(applicationContext, LoginAfterActivity::class.java)
            startActivity(intent)
        }

        //Click Next Button goto GActivity Page
        btnNext.setOnClickListener{
            val intent = Intent(applicationContext, gActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener{
            val editor = sharedPref?.edit()
            editor?.remove("full_name")
            editor?.remove("vcode")
            editor?.apply()

            onResume()
        }
    }

    override fun onResume() {
        super.onResume()

        val full_name = sharedPref?.getString("full_name","-")
        val vcode = sharedPref?.getInt("vcode",0)

        textFullName.text = full_name
        textVcode.text = vcode.toString()
    }
}
