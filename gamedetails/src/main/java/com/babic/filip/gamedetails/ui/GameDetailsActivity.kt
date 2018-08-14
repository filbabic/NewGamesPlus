package com.babic.filip.gamedetails.ui

import android.os.Bundle
import com.babic.filip.core.common.subscribe
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StateViewModel
import com.babic.filip.coreui.imageLoading.loadImage
import com.babic.filip.gamedetails.R
import kotlinx.android.synthetic.main.activity_game_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameDetailsActivity : BaseActivity<GameDetailsViewState>() {

    companion object {
        const val KEY_GAME_ID = "gameId"
    }

    private val detailsViewModel: GameDetailsContract.ViewModel by viewModel<GameDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        detailsViewModel.viewState().subscribe(::onDataChanged)

        savedInstanceState ?: getDetails()
    }

    private fun initUi() {

    }

    private fun getDetails() {
        val gameId = intent.getStringExtra(KEY_GAME_ID) ?: ""

        detailsViewModel.showDetails(gameId)
    }

    private fun onDataChanged(gameDetailsViewState: GameDetailsViewState) = with(gameDetailsViewState.gameDetails) {
        loadImage(cover, gameImage)
    }

    override fun getViewModel(): StateViewModel<GameDetailsViewState, BaseView> = detailsViewModel as StateViewModel<GameDetailsViewState, BaseView>
    override fun getLayout(): Int = R.layout.activity_game_details
    override fun getScope(): String = GAME_DETAILS_SCOPE
}

const val GAME_DETAILS_SCOPE = "Game Details"