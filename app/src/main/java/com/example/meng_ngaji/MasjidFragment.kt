package com.example.meng_ngaji

import android.Manifest
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.meng_ngaji.nearby.ModelResults
import com.example.meng_ngaji.viewModel.MasjidViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar

class MasjidFragment : Fragment(), OnMapReadyCallback {

    //Initialize 2
    private var mGoogleMap: GoogleMap? = null
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var permissionToRequest = mutableListOf<String>()
    private var isLocationPermissionOk = false
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private lateinit var currentLocation: Location
    private var currentMarker: Marker? = null
    private var raduis = 1500
    private var progressDialog: ProgressDialog? = null
    private lateinit var masjidViewModel: MasjidViewModel
    private var strLocation = String()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_masjid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
                isLocationPermissionOk =
                    permission[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                            && permission[Manifest.permission.ACCESS_FINE_LOCATION] == true

                if (isLocationPermissionOk)
                    setUpGoogleMap()
                else
                    Snackbar.make(view, "Location permission denied", Snackbar.LENGTH_LONG)
                        .show()
            }

        val mapFragment =
            (childFragmentManager.findFragmentById(R.id.GooleMap) as SupportMapFragment?)
        mapFragment?.getMapAsync(this)



    }

    override fun onMapReady(googleMap: GoogleMap) {

        mGoogleMap = googleMap

        when {
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                isLocationPermissionOk = true
                setUpGoogleMap()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                AlertDialog.Builder(requireContext())
                    .setTitle("Location permission")
                    .setMessage("Near me required location permission to access your location")
                    .setPositiveButton("Ok") { _, _ ->
                        requestLocation()
                    }.create().show()
            }

            else -> {
                requestLocation()
            }
        }
        mGoogleMap?.mapType = GoogleMap.MAP_TYPE_HYBRID

        //viewmodels
        setViewModel()
    }

    private fun setViewModel() {
        progressDialog?.show()
        masjidViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(MasjidViewModel::class.java)
        masjidViewModel.setMarkerLocation(strLocation)
        masjidViewModel.getMarkerLocation()
            .observe(requireActivity(), { modelResults: ArrayList<ModelResults> ->
                if (modelResults.size != 0) {

                    //get multiple marker
                    getMarker(modelResults)
                    progressDialog?.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Oops, Cannot get mosque location", Toast.LENGTH_SHORT).show()
                    progressDialog?.dismiss()
                }
                progressDialog?.dismiss()
            })
    }

    private fun getMarker(modelResultsArrayList: ArrayList<ModelResults>) {
        for (i in modelResultsArrayList.indices) {

            //set LatLong from APi
            val latLngMarker = LatLng(
                modelResultsArrayList[i]
                    .modelGeometry
                    .modelLocation
                    .lat, modelResultsArrayList[i]
                    .modelGeometry
                    .modelLocation
                    .lat
            )

            //get LatLong to Merker
            mGoogleMap?.addMarker(
                MarkerOptions()
                    .position(latLngMarker)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title(modelResultsArrayList[i].name)
            )

            //show Marker
            val latLngResult = LatLng(
                modelResultsArrayList[0].modelGeometry
                    .modelLocation
                    .lat, modelResultsArrayList[0]
                    .modelGeometry
                    .modelLocation
                    .lng
            )

            //set position marker
            mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(latLngResult))
            mGoogleMap?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        latLngResult.latitude,
                        latLngResult.longitude
                    ), 14f
                )
            )
            mGoogleMap?.uiSettings?.setAllGesturesEnabled(true)

        }
    }

    private fun requestLocation() {
        permissionToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        permissionLauncher.launch(permissionToRequest.toTypedArray())
    }

    private fun setUpGoogleMap() {

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            isLocationPermissionOk = false
            return
        }
        mGoogleMap?.isMyLocationEnabled = true
        mGoogleMap?.uiSettings?.isTiltGesturesEnabled = true
        mGoogleMap?.uiSettings?.isZoomControlsEnabled = true

        setUpLocationUpdate()
    }

    private fun setUpLocationUpdate() {

        locationRequest = LocationRequest.create()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                for (location in locationResult.locations) {
                    Log.d("TAG", "onLocationResult: ${location.latitude} ${location.longitude}")
                }
            }
        }

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        starLocationUpdate()
    }

    private fun starLocationUpdate() {

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
           isLocationPermissionOk = false
            return
        }
        fusedLocationProviderClient?.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Location update start", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        getCurrentLocation()
    }

    private fun getCurrentLocation() {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            isLocationPermissionOk = false
            return

        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {

            currentLocation = it
            moveCameraToLocation(currentLocation)
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()
        }
    }

    private fun moveCameraToLocation(location: Location) {
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
            LatLng(
                location.latitude,
                location.longitude
            ), 17f
        )

        val markerOptions = MarkerOptions()
            .position(LatLng(location.latitude, location.longitude))
            .title("Current Location")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))

        currentMarker?.remove()
        currentMarker = mGoogleMap?.addMarker(markerOptions)
        currentMarker?.tag = 703
        mGoogleMap?.animateCamera(cameraUpdate)

    }

    private fun stopLocationUpdate() {
        fusedLocationProviderClient?.removeLocationUpdates(locationCallback)
        Log.d("TAG", "stopLocationUpdate: Location update stop")
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdate()
    }

    override fun onResume() {
        super.onResume()
        if (fusedLocationProviderClient != null) {
            starLocationUpdate()
            currentMarker?.remove()
        }
    }


}