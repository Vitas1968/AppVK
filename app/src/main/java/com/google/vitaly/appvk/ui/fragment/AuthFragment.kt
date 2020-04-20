package com.google.vitaly.appvk.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.vitaly.appvk.R
import com.google.vitaly.appvk.mvp.presenter.AuthPresenter
import com.google.vitaly.appvk.mvp.view.AuthView
import com.google.vitaly.appvk.ui.App
import com.google.vitaly.appvk.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class AuthFragment : MvpAppCompatFragment(), AuthView, BackButtonListener {

    companion object {
        private const val PICK_IMAGE_REQUEST_ID = 1
        private const val redirectUrl="https://oauth.vk.com/blank.html"
        fun newInstance() = AuthFragment()
    }

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter() =AuthPresenter().apply {
        App.instance.userComponent.injectAuthPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun init() {
    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun requestAccessToken(request: String){
        web_view.webViewClient=WebClient()
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(request)

        getRequestAccessToken()
    }
    private fun getRequestAccessToken(){
       val url= web_view.url

        val tmp="tmp"
    }
    override fun backClicked() = presenter.backClicked()

      inner class WebClient: WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ) : Boolean{
            val url=request?.let {it.url.toString() }
            //url?.let{ if(it.startsWith(redirectUrl)) presenter.getAccesToken(it) }
            url?.takeIf{ it.startsWith(redirectUrl) }?.run {
                presenter.getAccesToken(this)
            }
            return super.shouldOverrideUrlLoading(view, request)

        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {

            super.onPageFinished(view, url)

        }
    }


}
