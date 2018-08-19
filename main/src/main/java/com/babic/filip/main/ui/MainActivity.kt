package com.babic.filip.main.ui

import android.os.Bundle
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.EmptyState
import com.babic.filip.coreui.base.StateViewModel
import com.babic.filip.coreui.common.onNavigationItemReselected
import com.babic.filip.coreui.common.onNavigationItemSelected
import com.babic.filip.main.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<EmptyState>() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>(parameters = { parametersOf(this) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        savedInstanceState ?: mainViewModel.showTopRated()
    }

    private fun initUi() {
        bottomNavigation.onNavigationItemSelected { selectedPage -> changePage(selectedPage) }
        bottomNavigation.onNavigationItemReselected { mainViewModel.refreshPage() }
    }

    private fun changePage(selectedPage: Int) = when (selectedPage) {
        R.id.topRated -> mainViewModel.showTopRated()
        R.id.upcoming -> mainViewModel.showUpcoming()
        R.id.feed -> mainViewModel.showFeed()
        R.id.messages -> mainViewModel.showMessages()
        R.id.profile -> mainViewModel.showMyProfile()
        else -> Unit
    }

    override fun getViewModel(): StateViewModel<EmptyState, BaseView> = mainViewModel as StateViewModel<EmptyState, BaseView>
    override fun getLayout(): Int = R.layout.activity_main
    override fun getScope(): String = MAIN_SCOPE
}

const val MAIN_SCOPE = "Main"