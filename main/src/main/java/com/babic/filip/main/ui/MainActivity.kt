package com.babic.filip.main.ui

import android.os.Bundle
import com.babic.filip.core.base.BaseActivity
import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.core.common.onNavigationItemReselected
import com.babic.filip.core.common.onNavigationItemSelected
import com.babic.filip.core.common.subscribe
import com.babic.filip.main.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewState>() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        mainViewModel.viewState().subscribe(::onDataChanged)

        savedInstanceState ?: mainViewModel.showTopRated()
    }

    private fun onDataChanged(mainViewState: MainViewState) {
        bottomNavigation.selectedItemId = mainViewState.activeItemId
    }

    private fun initUi() {
        bottomNavigation.onNavigationItemSelected { selectedPage -> changePage(selectedPage) }
        bottomNavigation.onNavigationItemReselected { mainViewModel.refreshPage() }
    }

    private fun changePage(selectedPage: Int) {
        mainViewModel.setCurrentPage(selectedPage)

        when (selectedPage) {
            R.id.topRated -> mainViewModel.showTopRated()
            R.id.upcoming -> mainViewModel.showUpcoming()
            R.id.feed -> mainViewModel.showFeed()
            R.id.messages -> mainViewModel.showMessages()
            R.id.profile -> mainViewModel.showMyProfile()
            else -> Unit
        }
    }

    override fun getViewModel(): StateViewModel<MainViewState, BaseView> = mainViewModel as StateViewModel<MainViewState, BaseView>
    override fun getLayout(): Int = R.layout.activity_main
    override fun getScope(): String = MAIN_SCOPE
}

const val MAIN_SCOPE = "Main"