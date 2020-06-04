package com.mrspd.pokedex.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.db.PokemonDatabase
import com.mrspd.pokedex.repository.PokemonRepository
import com.mrspd.pokedex.viewmodel.PokeViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    lateinit var viewModel: PokeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val newsRepository = PokemonRepository(PokemonDatabase(this))
        val viewModelProviderFactory = PokeViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(PokeViewModel::class.java)


        igmenu.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)
    }

    //this is to add the back button in the fragments
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawer_layout)
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_pokedex -> {
                var navOptions = NavOptions.Builder().setPopUpTo(R.id.app_nav, true).build()
                if (isValidDestination(R.id.nav_pokedex)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.pokedexFragment, null, navOptions)
                }
            }
            R.id.nav_types -> {
                if (isValidDestination(R.id.typesFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.typesFragment)
                }

            }
            R.id.nav_regions -> {
                if (isValidDestination(R.id.regionFragment2)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.regionFragment2)
                }

            }
            R.id.nav_items -> {
                if (isValidDestination(R.id.nav_items)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.itemsFragment)
                }
            }
            R.id.nav_locations -> {
                if (isValidDestination(R.id.nav_locations)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.locationFragment)
                }
            }
            R.id.nav_favorite -> {
                if (isValidDestination(R.id.nav_favorite)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.favoritePokemonFragments)
                }
            }
        }
        p0.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_pokedex -> {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    return true
                } else
                    return false
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun isValidDestination(destination: Int): Boolean {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment)
            .currentDestination!!.id
    }

}
