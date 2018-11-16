package com.jaygengi.gank.ui.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseActivity
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.activity_webview.*

/**
   * @description: WebViewActivity
   * @author JayGengi
   * @date  2018/11/16 0016 下午 1:02
   * @email jaygengiii@gmail.com
   */
class WebViewActivity : BaseActivity(), AdvancedWebView.Listener{
    private var url:String? = ""
    private var title:String? = ""

    private var mWebView: AdvancedWebView? = null
    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initData() {
    }

    override fun start() {
    }


    @SuppressLint("ResourceAsColor")
    override fun initView() {
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("title")
        topbar.setTitle(title).setTextColor(R.color.color_black)
        topbar.addLeftImageButton(R.drawable.ic_left,R.id.left).setOnClickListener {
            finish()
        }
        mWebView = findViewById<View>(R.id.webview) as AdvancedWebView
        mWebView!!.setListener(this, this)
        setSettings()
        mWebView!!.loadUrl(url)
    }
    private fun setSettings(){
        mWebView!!.settings.setSupportZoom(true)
    }
    override fun onDestroy() {
        mWebView!!.onDestroy()
        super.onDestroy()
    }

    override fun onPageFinished(url: String?) {
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(url: String?, suggestedFilename: String?, mimeType: String?, contentLength: Long, contentDisposition: String?, userAgent: String?) {
    }

    override fun onExternalPageRequest(url: String?) {
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
    }
}