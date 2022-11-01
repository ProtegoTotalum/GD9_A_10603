package com.grayfien.gd9_proximity_a_10603

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //on below line we are creating
    //a variable for our text view
    lateinit var sensorStatusTV: TextView
    //on below line we are creating
    //a variable for our proximity sensor
    lateinit var proximitySensor: Sensor
    //on below line we are creating
    //a variable for sensor manager
    lateinit var sensorManager: SensorManager

    var proximitySensorEventListener: SensorEventListener? = object : SensorEventListener{
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // method to check accuracy changed in sensor
        }

        override fun onSensorChanged(event: SensorEvent) {
            //check if the sensor type is proximity sensor
            if (event.sensor.type == Sensor.TYPE_PROXIMITY){
                if (event.values[0] ==0f){
                    sensorStatusTV.text="<<<Near>>>"
                }else{
                    sensorStatusTV.text="<<<Away>>>"
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //on below line we are initializing our all variables
        sensorStatusTV = findViewById(R.id.idTVSensorStatus)

        //on below line we are initializing our sensor manager
        sensorManager = getSystemService(Context.SENSOR_SERVICE)as SensorManager

        //on below line we are initializing our proximity sensor available
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        //on below line we are checking if the proximity sensor is null
        if (proximitySensor == null){
            //on below line we are displaying toast if no sensor is available
            Toast.makeText(this, "No proximity sensor found in device...",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            //on below we are registering
            //our sensor with sensor manager
            sensorManager.registerListener(
                proximitySensorEventListener,
                proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

}