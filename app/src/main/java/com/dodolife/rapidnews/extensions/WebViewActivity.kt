package com.dodolife.rapidnews.extensions

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.navigation.navArgs
import com.dodolife.rapidnews.R
import com.dodolife.rapidnews.modules.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity(R.layout.activity_web_view) {

    private val args: WebViewActivityArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeRefresh.setOnRefreshListener {
            webView.let {
                it.settings.apply {
                    javaScriptEnabled = true
                    allowFileAccess = true
                }
                it.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                it.webViewClient = InsideViewClient(progressBar)

                it.loadUrl(args.url)
            }
        }
        webView.let {
            it.settings.apply {
                javaScriptEnabled = true
                allowFileAccess = true
            }
            it.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            it.webViewClient = InsideViewClient(progressBar)

            it.loadUrl(args.url)
        }
    }

    private inner class InsideViewClient (private val progressBar: ProgressBar): WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url ?:"")
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            swipeRefresh.isRefreshing = true
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            swipeRefresh.isRefreshing = false
            progressBar.visibility = View.GONE
        }
    }
}
