package com.arushi.ai

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            loadUrl("https://your-web-app-url.com")
            addJavascriptInterface(AndroidBridge(), "AndroidBridge")
        }
        setContentView(webView)
    }
    inner class AndroidBridge {
        @JavascriptInterface
        fun openWhatsApp() {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/")))
            } catch (e: Exception) { e.printStackTrace() }
        }
        @JavascriptInterface
        fun makeCall(phoneNumber: String) {
            try {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}

