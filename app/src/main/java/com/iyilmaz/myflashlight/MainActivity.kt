package com.iyilmaz.myflashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    val camMan by lazy { getSystemService(Context.CAMERA_SERVICE) as CameraManager }
    var camIDWithFlash: String = "0"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val camList = camMan.cameraIdList
        camList.forEach {
            val characteristics = camMan.getCameraCharacteristics(it)
            val doesCamHasFlash: Boolean? = characteristics.get(FLASH_INFO_AVAILABLE)
            if (camIDWithFlash == "0" && doesCamHasFlash ==true) {
                camIDWithFlash = it
            }
        }
        camMan.setTorchMode(camIDWithFlash, true)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPause() {
        super.onPause()
        camMan.setTorchMode(camIDWithFlash,false)
    }


}