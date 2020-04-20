package com.google.vitaly.appvk.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.vitaly.appvk.R
import com.google.vitaly.appvk.mvp.presenter.MainPresenter
import com.google.vitaly.appvk.mvp.view.MainView
import com.google.vitaly.appvk.ui.App
import com.google.vitaly.appvk.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    val navigator = SupportAppNavigator(this, R.id.container)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }
    @ProvidePresenter
    fun providePresenter() = MainPresenter().apply {
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun init() {

    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backClicked()){
                return
            }
        }
        presenter.backClicked()
    }
}
