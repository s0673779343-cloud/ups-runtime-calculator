package ua.calculator.ups

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintManager
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = android.graphics.Color.rgb(13, 18, 20)
        window.navigationBarColor = android.graphics.Color.rgb(13, 18, 20)
        window.decorView.systemUiVisibility = 0

        webView = WebView(this)
        webView.setBackgroundColor(android.graphics.Color.rgb(13, 18, 20))
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = false
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Route the page's Print / PDF button to Android's native print service.
                webView.evaluateJavascript(
                    "window.print = function(){ AndroidPrintBridge.printPage(); };",
                    null
                )
            }
        }
        webView.addJavascriptInterface(PrintBridge(this), "AndroidPrintBridge")
        setContentView(webView)
        webView.loadUrl("file:///android_asset/index.html")
    }

    inner class PrintBridge(private val activity: Activity) {
        @JavascriptInterface
        fun printPage() {
            activity.runOnUiThread {
                try {
                    val printManager = getSystemService(Context.PRINT_SERVICE) as PrintManager
                    val adapter = webView.createPrintDocumentAdapter("Калькулятор ДБЖ")
                    printManager.print(
                        "Калькулятор ДБЖ",
                        adapter,
                        PrintAttributes.Builder().build()
                    )
                } catch (e: Exception) {
                    Toast.makeText(
                        activity,
                        "Не вдалося відкрити друк/PDF на цьому пристрої",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
