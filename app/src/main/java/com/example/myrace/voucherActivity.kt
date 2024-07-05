package com.example.myrace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class voucherActivity : AppCompatActivity() {


    var count = 0
    private lateinit var viewModel: voucherActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        viewModel = ViewModelProvider(this).get(voucherActivityViewModel::class.java)

        val textView = findViewById<TextView>(R.id.tvCount1)
        val button = findViewById<Button>(R.id.btnCount1)


        //textView.text = count.toString()
        //textView.text = viewModel.count.toString()
        viewModel.count.observe(this, Observer {
            textView.text = it.toString()
        })
        button.setOnClickListener{
            //++count
            //textView.text = count.toString()
            viewModel.updateCount()
            //textView.text = viewModel.count.toString()

        }




    }
}