package com.capstoneproject.csd190.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.capstoneproject.csd190.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.capstoneproject.csd190.model.Provence
import com.capstoneproject.csd190.viewModel.MapViewModel

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private val mapViewModel: MapViewModel by lazy {
        ViewModelProvider(this)[MapViewModel::class.java]
    }

    private lateinit var maps: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        mapViewModel.getData().observe(this, { data ->
            data.forEach { createMarker(it) }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_maps, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.satelliteMaps -> {
            maps.mapType = GoogleMap.MAP_TYPE_SATELLITE

            true
        }

        R.id.terrainMaps -> {
            maps.mapType = GoogleMap.MAP_TYPE_TERRAIN

            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun createMarker(prov: Provence) {
        val lokasi = LatLng(prov.lokasi.lat, prov.lokasi.lon)

        val snippet = getString(R.string.amount_treated, prov.dirawat)

        maps.addMarker(
            MarkerOptions()
                .position(lokasi)
                .title(prov.key)
                .snippet(snippet)
        )
    }

    override fun onMapReady(gMap: GoogleMap) {
        maps = gMap

        mapViewModel.requestData()

        val lokasi = LatLng(-6.920432082789247, 107.60370834146391)
        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 7f))
        enableMyLocation()
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            maps.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                enableMyLocation()
            }
        }
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}