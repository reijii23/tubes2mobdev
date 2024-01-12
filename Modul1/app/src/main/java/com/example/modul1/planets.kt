package com.example.modul1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.modul1.R
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.SearchView

class planets : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.searchView)
        listView = view.findViewById(R.id.listViewPlanets)

        val planets = arrayOf("Tatooine", "Alderaan", "Endor", /*...*/)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, planets)
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search submission if needed
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search text change
                filterData(newText, planets)
                return true
            }
        })
    }

    private fun filterData(query: String?, allData: Array<String>) {
        val filteredData = allData.filter { it.contains(query.orEmpty(), ignoreCase = true) }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, filteredData)
        listView.adapter = adapter
    }
}
