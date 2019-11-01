package com.babic.filip.toprated.ui

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.babic.filip.coreui.base.BaseFragment
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.RefreshablePage
import com.babic.filip.coreui.base.StatePresenter
import com.babic.filip.coreui.listener.LazyLoadingListener
import com.babic.filip.toprated.R
import com.babic.filip.toprated.domain.model.TopRatedGame
import com.babic.filip.toprated.list.GameAdapter
import kotlinx.android.synthetic.main.fragment_top_rated_games.*
import org.koin.android.ext.android.inject

class TopRatedGamesFragment : BaseFragment<GamesViewState>(), RefreshablePage {

    private val presenter: TopRatedGamesContract.Presenter by inject<TopRatedGamesPresenter>()
    private val gamesAdapter by lazy { GameAdapter(::onGameClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        savedInstanceState ?: presenter.getTopRatedGames()
    }

    private fun initUi() {
        pullToRefresh.setOnRefreshListener { presenter.refresh() }

        gamesList.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = gamesAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(LazyLoadingListener(presenter::getTopRatedGames))
        }
    }

    override fun onViewStateChanged(viewState: GamesViewState) {
        gamesAdapter.setData(viewState.games)
        pullToRefresh.isRefreshing = viewState.isLoading
    }

    override fun refresh() {
        val directionTop = -1

        if (!gamesList.canScrollVertically(directionTop)) {
            presenter.refresh()
        } else {
            gamesList.smoothScrollToPosition(0)
        }
    }

    private fun onGameClicked(game: TopRatedGame) = presenter.showDetails(game)

    override fun getPresenter(): StatePresenter<GamesViewState, BaseView> = presenter as StatePresenter<GamesViewState, BaseView>

    override fun getLayout(): Int = R.layout.fragment_top_rated_games
    override fun getScope(): String = TOP_RATED_GAMES_SCOPE
}

const val TOP_RATED_GAMES_SCOPE = "Top Rated"