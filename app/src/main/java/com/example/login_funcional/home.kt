package com.example.login_funcional

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL



class home : AppCompatActivity() {

    private lateinit var pokemonListView: ListView
    private lateinit var pokemonAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        pokemonListView = findViewById(R.id.pokemonListView)
        pokemonAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        pokemonListView.adapter = pokemonAdapter

        FetchPokemonTask().execute()

    }


    private inner class FetchPokemonTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void): String {
            val url = URL("https://pokeapi.co/api/v2/pokemon")
            val connection = url.openConnection() as HttpURLConnection

            try {
                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
                val stringBuilder = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
                bufferedReader.close()
                return stringBuilder.toString()
            } finally {
                connection.disconnect()
            }
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            val pokemonList = ArrayList<String>()

            val jsonObject = JSONObject(result)
            val resultsArray = jsonObject.getJSONArray("results")
            for (i in 0 until resultsArray.length()) {
                val pokemonObject = resultsArray.getJSONObject(i)
                val pokemonName = pokemonObject.getString("name")
                pokemonList.add(pokemonName)
            }

            pokemonAdapter.addAll(pokemonList)
        }
    }
}


