package com.example.runtime_permission

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  // THIS VARIABLE WILL IDENTIFIED OUR PERMISSION REQUESTS
  // BELOW VARIABLE WILL STORE USER GRANTED PERMISSION RECORD.
    // MEANS BECAUSE THIS PERMISSION WILL GRANT ONLY FOR ONE TIME.
    // SO IF GRANTED VALUE IS EQUAL TO BELOW VARIABEL VALUE.
    // THEN PERMISSION WILL BE GRANTED.
    // MANIFEST.XML BHI DEKHOOOO PHR COMPLETE SMJH LG JY GI


    /* NOTE!!!
    * Let we want to use this code in another permission like READ_EXTERNAL_STORAGE
    * FIRST OF ALL DECLARE PERMISSION IN ANDROID MANIFEST FOLDER
    * AND AFTER THAT
    * JAHAN JAHAN     Manifest.permission.CAMERA
    * YE CAMERA KI PERMISSION GRANT KI GI HAI WAHAN CAMERA KO REPLACE KR DENA
    * REQUIRED PERMISSION SE TO WO WOHI PERMISSION PAR WORK KRY GA.
    * YE CODE READ_EXTERNAL_STORAGE PR THA MNY ISKO CAMERA PR BHI USE KIA HAI.
    * BAQI TOAST MSGS BHI ACCORDING TO NEED CHANGE KR LENA.
    * Manifest.permission.CAMERA YE LINE 4 BR CODE MELIKHI GI HAI
    * DHYAN SE DEKHNA.
    *
    * */
    private val STORAGE_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonRequest = findViewById<Button>(R.id.button)
        buttonRequest.setOnClickListener {
            //Below if condition check if our permission already granted then what happend.

            if (ContextCompat.checkSelfPermission(this@MainActivity,
                            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "You have already granted this permission!",
                        Toast.LENGTH_SHORT).show()

                // else our permission not granted then what will we do.
            } else {
                requestStoragePermission()
                // this function call if our permission not granted
            }
        }
    }

    private fun requestStoragePermission() {
        // below if condition will be show permission dialog to our user
        // for granted our permission.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {

// below we have created the alert dialoge for granted permission

            AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok") { dialog, which ->
                        ActivityCompat.requestPermissions(this@MainActivity,
                                arrayOf(Manifest.permission.CAMERA), STORAGE_PERMISSION_CODE)
                    }
                    .setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }
                    .create().show()

        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA), STORAGE_PERMISSION_CODE)
        // STORAGE_PERMISSION_CODE is our variable which is decalared uper  uper uper.

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
      // now we check if permission granted code equal to our private
        // varible then we will show permission is granted
        // if not equal then we show not granted.

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
