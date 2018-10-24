package com.babic.filip.main.ui

import android.os.Bundle
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.EmptyState
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.coreui.common.onNavigationItemReselected
import com.babic.filip.coreui.common.onNavigationItemSelected
import com.babic.filip.main.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<EmptyState>() {

    private val presenter: MainContract.Presenter by inject<MainPresenter>(parameters = { parametersOf(this) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        savedInstanceState ?: presenter.showTopRated()
    }

    private fun initUi() {
        bottomNavigation.onNavigationItemSelected { selectedPage -> changePage(selectedPage) }
        bottomNavigation.onNavigationItemReselected { presenter.refreshPage() }
    }

    private fun changePage(selectedPage: Int) = when (selectedPage) {
        R.id.topRated -> presenter.showTopRated()
        R.id.upcoming -> presenter.showUpcoming()
        R.id.feed -> presenter.showFeed()
        R.id.messages -> presenter.showMessages()
        R.id.profile -> presenter.showMyProfile()
        else -> Unit
    }

    override fun getPresenter(): StatePresenter<EmptyState, BaseView> = presenter as StatePresenter<EmptyState, BaseView>
    override fun getLayout(): Int = R.layout.activity_main
    override fun getScope(): String = MAIN_SCOPE
}

const val MAIN_SCOPE = "Main"