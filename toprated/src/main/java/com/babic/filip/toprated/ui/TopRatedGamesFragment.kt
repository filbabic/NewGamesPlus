package com.babic.filip.toprated.ui

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.babic.filip.core.common.subscribe
import com.babic.filip.coreui.base.BaseFragment
import com.babic.filip.coreui.base.BaseView
import com.babic.filip.coreui.base.RefreshablePage
import com.babic.filip.coreui.base.StateViewModel
import com.babic.filip.coreui.listener.LazyLoadingListener
import com.babic.filip.toprated.R
import com.babic.filip.toprated.domain.model.TopRatedGame
import com.babic.filip.toprated.list.GameAdapter
import kotlinx.android.synthetic.main.fragment_top_rated_games.*
import org.koin.android.viewmodel.ext.android.viewModel

class TopRatedGamesFragment : BaseFragment<GamesViewState>(), RefreshablePage {

    private val topRatedViewModel: TopRatedGamesContract.ViewModel by viewModel<TopRatedGamesViewModel>()
    private val gamesAdapter by lazy { GameAdapter(::onGameClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        topRatedViewModel.viewState().subscribe(::onDataChanged)

        savedInstanceState ?: topRatedViewModel.getTopRatedGames()
    }

    private fun initUi() {
        pullToRefresh.setOnRefreshListener { topRatedViewModel.refresh() }

        gamesList.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = gamesAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(LazyLoadingListener(topRatedViewModel::getTopRatedGames))
        }
    }

    private fun onDataChanged(gamesViewState: GamesViewState) {
        gamesAdapter.setData(gamesViewState.games)
        pullToRefresh.isRefreshing = gamesViewState.isLoading
    }

    override fun refresh() {
        val directionTop = -1

        if (!gamesList.canScrollVertically(directionTop)) {
            topRatedViewModel.refresh()
        } else {
            gamesList.smoothScrollToPosition(0)
        }
    }

    private fun onGameClicked(game: TopRatedGame) = topRatedViewModel.showDetails(game)

    override fun getViewModel(): StateViewModel<GamesViewState, BaseView> = topRatedViewModel as StateViewModel<GamesViewState, BaseView>

    override fun getLayout(): Int = R.layout.fragment_top_rated_games
    override fun getScope(): String = TOP_RATED_GAMES_SCOPE
}

const val TOP_RATED_GAMES_SCOPE = "Top Rated"