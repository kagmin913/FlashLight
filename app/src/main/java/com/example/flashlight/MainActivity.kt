package com.example.flashlight

import android.content.Context
import android.graphics.Camera
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var powerState : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: ImageButton=findViewById(R.id.imageButton)
        btn.setOnClickListener{
            if(powerState){
                powerState=false
                btn.setImageResource(R.drawable.power_off)
                controlFlash(powerState)
            }else{
                powerState=true
                btn.setImageResource(R.drawable.power_on)
                controlFlash(powerState)
            }
        }
    }

    fun controlFlash(mode: Boolean)
    {
        var cameraM = getSystemService(Context.CAMERA_SERVICE)as CameraManager
        val cameraListId = cameraM.cameraIdList[0]

        try{
            cameraM.setTorchMode(cameraListId,mode)
        }catch (e:Exception){
            val toast = Toast.makeText(this,"Camera Flash Error",Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}