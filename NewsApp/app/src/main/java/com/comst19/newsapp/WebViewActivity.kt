package com.comst19.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import com.comst19.newsapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)

        val url = intent.getStringExtra("url")

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true

        if(url.isNullOrEmpty()){
            Toast.makeText(this,"잘못된 URL 입니다.",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            binding.webView.loadUrl(url)
        }

        setContentView(binding.root)
    }
}