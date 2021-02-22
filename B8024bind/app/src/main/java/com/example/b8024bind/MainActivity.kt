package com.example.b8024bind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.example.b8024bind.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 31.5f)
        binding.textView.setText("olleH")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}