package com.example.motivation_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nextBtn.setOnClickListener {
            loadQuote()
        }

        loadQuote()
    }

    private fun loadQuote() {
        showLoader()
        App.quoteService?.getNextQuote()?.enqueue(object : Callback<Quote?> {
            override fun onResponse(call: Call<Quote?>, response: Response<Quote?>) {
                if (response.isSuccessful) {
                    showText(response.body()!!)
                } else {
                    showError("Error with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Quote?>, t: Throwable) {
                showError("Error: ${t.localizedMessage}")
            }
        })
    }

    private fun showText(quote: Quote) {
        binding.quoteText.visibility = View.VISIBLE
        binding.quoteAuthor.visibility = View.VISIBLE
        binding.loader.visibility = View.INVISIBLE

        binding.quoteText.text = quote.quoteText
        binding.quoteAuthor.text = quote.quoteAuthor
    }

    private fun showLoader() {
        binding.quoteText.visibility = View.INVISIBLE
        binding.quoteAuthor.visibility = View.INVISIBLE
        binding.loader.visibility = View.VISIBLE
    }

    private fun showError(error: String) {
        binding.quoteText.visibility = View.VISIBLE
        binding.loader.visibility = View.INVISIBLE

        binding.quoteText.text = error
    }
}