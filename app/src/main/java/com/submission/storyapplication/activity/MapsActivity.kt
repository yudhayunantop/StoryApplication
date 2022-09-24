package com.submission.storyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.submission.storyapplication.R
import com.submission.storyapplication.api.ApiRetrofit
import com.submission.storyapplication.databinding.ActivityMapsBinding
import com.submission.storyapplication.models.AllStoriesModel
import com.submission.storyapplication.models.dataLocation
import com.submission.storyapplication.preferences.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val api by lazy { ApiRetrofit().endpoint}
//    var dataLocations = List <AllStoriesModel.stories>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        getAllStoriesLocation()
        val dicodingSpace = LatLng(-6.8957643, 107.6338462)
        mMap.addMarker(
            MarkerOptions()
                .position(dicodingSpace)
                .title("Dicoding Space")
                .snippet("Batik Kumeli No.50")
        )
    }

    private fun getAllStoriesLocation(){
        api.get_all_stories(token="Bearer ${Preferences.getToken()}")
            .enqueue(object : Callback<AllStoriesModel> {
                override fun onResponse(
                    call: Call<AllStoriesModel>,
                    response: Response<AllStoriesModel>
                ) {
                    if (response.isSuccessful){
                        val submit = response.body()
//                        dataLocations= submit!!.listStory!!
                    }else{
                        Toast.makeText(applicationContext, "Failed fetch data!!!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AllStoriesModel>, t: Throwable) {
                    t.message
                    Toast.makeText(applicationContext, "No Connection!!!", Toast.LENGTH_SHORT).show()
                }

            })
    }
}