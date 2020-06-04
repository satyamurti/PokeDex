package com.mrspd.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.LocationsListAdapter
import com.mrspd.pokedex.adapters.models.modellocation.LocationResponse
import com.mrspd.pokedex.ui.MainActivity
import com.mrspd.pokedex.viewmodel.PokeViewModel
import kotlinx.android.synthetic.main.fragment_pokedex.*

class LocationFragment : Fragment(R.layout.fragment_locations) {

    private lateinit var viewModel: PokeViewModel
    private var listAdapter = LocationsListAdapter(arrayListOf())
    private var counter = 1

    private val locationObserver = androidx.lifecycle.Observer<LocationResponse> {
        if (it != null) {

            listAdapter.updateNameList(it)
        }
    }

    private val loadingObserver = androidx.lifecycle.Observer<Boolean> { isLoading ->
        if (isLoading) {
//            loading_progressbar.visibility = View.VISIBLE
//            listError_textView.visibility = View.GONE

        } else {
//            loading_progressbar.visibility = View.GONE
        }
    }
    private val listErrorObserver = androidx.lifecycle.Observer<Boolean> { isError ->
        if (isError) {
//            listError_textView.visibility = View.VISIBLE
//            pokedex_recyclerView.visibility = View.GONE
        } else {
//            listError_textView.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

//        viewModel.loading.observe(this,loadingObserver )
//        viewModel.loadingError.observe(this, listErrorObserver)
        viewModel.locations.observe(this, locationObserver)
        viewModel.refresh3(counter)

        //configure recyclerview
        recyclerView.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 1)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(context, "Page ended ,next loading", Toast.LENGTH_LONG).show()
                    counter += 19
                    viewModel.refresh3(counter)
                }
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })
    }

}
