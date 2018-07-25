package com.babic.filip.main.ui.topRated

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.babic.filip.core.base.BaseFragment
import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.core.common.subscribe
import com.babic.filip.main.R
import com.babic.filip.main.domain.model.Game
import com.babic.filip.main.ui.RefreshablePage
import com.babic.filip.main.ui.topRated.list.GameAdapter
import kotlinx.android.synthetic.main.fragment_top_rated_games.*
import org.koin.android.viewmodel.ext.android.viewModel

class TopRatedGamesFragment : BaseFragment<GamesViewState>(), RefreshablePage {

    private val topRatedViewModel: TopRatedGamesContract.ViewModel by viewModel<TopRatedGamesViewModel>()
    private val gamesAdapter by lazy { GameAdapter(::onGameClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        topRatedViewModel.viewState().subscribe(::onDataChanged)
        topRatedViewModel.getTopRatedGames()
    }

    private fun initUi() {
        pullToRefresh.setOnRefreshListener { topRatedViewModel.refresh() }

        gamesList.apply {
            itemAnimator = DefaultItemAnimator()
            adapter = gamesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun onDataChanged(gamesViewState: GamesViewState) {

    }

    override fun refresh() {
        //todo scroll to top, or refresh if it's already scrolled

        if (!gamesList.canScrollVertically(-1)) {
            topRatedViewModel.refresh()
        } else {
            gamesList.smoothScrollToPosition(0)
        }
    }

    private fun onGameClicked(game: Game) = topRatedViewModel.showDetails(game)

    override fun getViewModel(): StateViewModel<GamesViewState, BaseView> = topRatedViewModel as StateViewModel<GamesViewState, BaseView>

    override fun getLayout(): Int = R.layout.fragment_top_rated_games
    override fun getScope(): String = TOP_RATED_GAMES_SCOPE
}

private const val TOP_RATED_GAMES_SCOPE = "Top Rated"