package com.example.quoteapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MyViewModel(val context: Context): ViewModel() {
 private  var quoteList : Array<Quote> = emptyArray()
 private var index = 0
    init {
        quoteList =loadQuoteFromAssets() // the list is initialized from the function return
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        //input file from assets of the context
        val inputStream = context.assets.open("quote.json")
        // return the size of the file
        val size = inputStream.available()
        //defining buffer of size equal to file, to store the quote. so that it can be accessed easily
        val buffer = ByteArray(size)
        // reading the imported json file  and storing into buffer of equal size
        inputStream.read(buffer)
        inputStream.close()//closing the inputStream
        //encoding the file stored in buffer by charset and converting to the String.
        //json is javascript format of the file
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()//creating the gson variable of Gson
        return gson.fromJson(json,Array<Quote>::class.java)// creating the array from json string.simply
        //typecasting of the json string in the array of the Quote. the array of the quote is returned from this function
    }
    fun getQuote()=quoteList[index]
    fun nextQuote()=quoteList[++index]
    fun previousQuote()=quoteList[--index]

}