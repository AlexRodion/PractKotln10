package com.example.practkotln10

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity : AppCompatActivity() {
    var colors = arrayOf(0xC5CAE9, 0xB2EBF2, 0xFFF9C4)
    var sheetNumber = 0
    val text: EditText = findViewById(R.id.text)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity1)
        sheetNumber = getIntent().getIntExtra("sheetNumber", 0)
        val next : Button = findViewById(R.id.next)
        next.setOnClickListener(){
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onPause(){
        super.onPause()
        val prefs = getPreferences(Context.MODE_PRIVATE).edit()
        prefs.putString("note1", text.editableText.toString())
        prefs.apply()
    }

    override fun onResume() {
        super.onResume()
        val saveState = getPreferences(Context.MODE_PRIVATE).getString("note1", null)
        if (saveState != null){
            text.setText(saveState)
        }
    }
}