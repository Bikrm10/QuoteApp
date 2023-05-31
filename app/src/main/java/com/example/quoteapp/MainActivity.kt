package com.example.quoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.Array.get

class MainActivity : AppCompatActivity(){
    private lateinit var myViewModel: MyViewModel
    private val quoteText : TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor: TextView
        get()= findViewById(R.id.quoteAuthor)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing the object from MyViewModel through MyViewModelFactory
        //we use application context  not this because , mainViewModel is not destroyed when the activity  is destroyed if wwe pass context
        // application context
        myViewModel = ViewModelProvider(this,MyViewModelFactory(application)).get(MyViewModel::class.java)
        setQuote(myViewModel.getQuote())// getting the quote from the MyviewModel and sending to the setQuote
    }
    fun setQuote(quote:Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,myViewModel.getQuote().toString())
        startActivity(intent)
    }

    fun onNext(view: View) {
        setQuote(myViewModel.nextQuote())
    }
    fun onPrevious(view: View) {
        setQuote(myViewModel.previousQuote())
    }

}