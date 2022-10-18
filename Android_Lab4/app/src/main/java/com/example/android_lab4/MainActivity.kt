package com.example.android_lab4

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sendButton: Button
    private lateinit var sendText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendButton = findViewById(R.id.sendButton)
        sendText = findViewById(R.id.sendName)
    }

    fun sendNameToSecondActivityEvent(view: View) {
        val str = sendText.text.toString()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("message_key", str)
        startActivity(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val contacts: Uri       = data?.data ?: return@registerForActivityResult
            val cols: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

            val rs = contentResolver.query(contacts, cols, null, null, null)

            if(rs?.moveToFirst()!!) {
                Log.i("contacts", rs.getString(0) )
                sendText.setText( rs.getString(0) )
            }
        }
    }

    fun chooseContactEvent(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        resultLauncher.launch(intent)
    }
}