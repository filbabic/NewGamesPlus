package com.babic.filip.main.ui.topRated

import com.babic.filip.core.base.BaseView
import com.babic.filip.core.base.StateViewModel

interface TopRatedGamesContract {

    interface View : BaseView

    interface ViewModel : StateViewModel<GamesViewState, View>
}