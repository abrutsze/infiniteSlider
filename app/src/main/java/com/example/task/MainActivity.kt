package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task.fragment.home.HomeFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = supportFragmentManager.beginTransaction()
        fragment.add(R.id.rootFragment, HomeFragment())
        fragment.commit();
    }
}