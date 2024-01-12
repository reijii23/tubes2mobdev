package com.example.modul1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.modul1.fragments.fragment_char
import com.example.modul1.fragments.planets
import com.example.modul1.fragments.fragment_ship
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var planetbtn: Button
    private lateinit var characterbtn: Button
    private lateinit var shipbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        planetbtn = findViewById(R.id.plannetbtn)
        characterbtn = findViewById(R.id.characterbtn)
        shipbtn = findViewById(R.id.shipbtn)

        planetbtn.setOnClickListener {
            openFragment(planets())
        }

        characterbtn.setOnClickListener {
            openFragment(fragment_char())
        }

        shipbtn.setOnClickListener {
            openFragment(fragment_ship())
        }

        fetchDataFromSWAPI()
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun fetchDataFromSWAPI() {
        val service = RetrofitClient.retrofit.create(SwapiService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = service.getPerson()
                if (response.isSuccessful) {
                    val character = response.body()
                    if (character != null) {
                        // TODO: Handle the character data as needed
                        Log.d("SWAPI Character", "Name: ${character.name}")
                    } else {
                        Log.e("SWAPI Error", "Response body is null")
                    }
                } else {
                    Log.e("SWAPI Error", "Failed to fetch data. Response code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("SWAPI Error", "Exception: ${e.message}")
            }
        }

    }
}
