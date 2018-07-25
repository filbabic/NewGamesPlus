package com.babic.filip.main.ui.topRated

import com.babic.filip.core.base.BaseFragment
import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel
import com.babic.filip.main.R
import com.babic.filip.main.ui.RefreshablePage
import org.koin.android.viewmodel.ext.android.viewModel

class TopRatedGamesFragment : BaseFragment<GamesViewState>(), RefreshablePage {

    private val topRatedViewModel: StateViewModel<GamesViewState, TopRatedGamesContract.View> by viewModel<TopRatedGamesViewModel>()


    override fun refresh() {
    }

    override fun getViewModel(): StateViewModel<GamesViewState, BaseView> = topRatedViewModel as StateViewModel<GamesViewState, BaseView>

    override fun getLayout(): Int = R.layout.fragment_top_rated_games
    override fun getScope(): String = TOP_RATED_GAMES_SCOPE
}

private const val TOP_RATED_GAMES_SCOPE = "Top Rated"