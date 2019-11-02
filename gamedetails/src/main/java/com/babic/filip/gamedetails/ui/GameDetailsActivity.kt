package com.babic.filip.gamedetails.ui

import android.os.Bundle
import com.babic.filip.coreui.base.BaseActivity
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.coreui.imageLoading.loadImage
import com.babic.filip.gamedetails.R
import kotlinx.android.synthetic.main.activity_game_details.*

class GameDetailsActivity : BaseActivity<GameDetailsViewState>() {

    companion object {
        const val KEY_GAME_ID = "gameId"
    }

    private val presenter: GameDetailsContract.Presenter by lazy { scope.get<GameDetailsPresenter>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        savedInstanceState ?: getDetails()
    }

    private fun initUi() {

    }

    private fun getDetails() {
        val gameId = intent.getStringExtra(KEY_GAME_ID) ?: ""

        presenter.showDetails(gameId)
    }

    override fun onViewStateChanged(viewState: GameDetailsViewState) {
        loadImage(viewState.cover, gameImage)
    }

    override fun getPresenter(): StatePresenter<GameDetailsViewState, BaseView> = presenter as StatePresenter<GameDetailsViewState, BaseView>
    override fun getLayout(): Int = R.layout.activity_game_details
    override fun getScope(): String = GAME_DETAILS_SCOPE
}

const val GAME_DETAILS_SCOPE = "Game Details"