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
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<EmptyState>() {

    private val mainPresenter: MainContract.Presenter by viewModel<MainPresenter>(parameters = { parametersOf(this) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        savedInstanceState ?: mainPresenter.showTopRated()
    }

    private fun initUi() {
        bottomNavigation.onNavigationItemSelected { selectedPage -> changePage(selectedPage) }
        bottomNavigation.onNavigationItemReselected { mainPresenter.refreshPage() }
    }

    private fun changePage(selectedPage: Int) = when (selectedPage) {
        R.id.topRated -> mainPresenter.showTopRated()
        R.id.upcoming -> mainPresenter.showUpcoming()
        R.id.feed -> mainPresenter.showFeed()
        R.id.messages -> mainPresenter.showMessages()
        R.id.profile -> mainPresenter.showMyProfile()
        else -> Unit
    }

    override fun getViewModel(): StatePresenter<EmptyState, BaseView> = mainPresenter as StatePresenter<EmptyState, BaseView>
    override fun getLayout(): Int = R.layout.activity_main
    override fun getScope(): String = MAIN_SCOPE
}

const val MAIN_SCOPE = "Main"