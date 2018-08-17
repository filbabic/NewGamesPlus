package com.babic.filip.toprated.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babic.filip.coreui.imageLoading.loadImage
import com.babic.filip.toprated.R
import com.babic.filip.toprated.domain.model.TopRatedGame
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val onGameClicked: (TopRatedGame) -> Unit) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val items = mutableListOf<TopRatedGame>()

    fun setData(newItems: List<TopRatedGame>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) = holder.showData(items[position], onGameClicked)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)

        return GameViewHolder(view)
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showData(game: TopRatedGame, onGameClicked: (TopRatedGame) -> Unit) = with(itemView) {
            gameImage.loadImage(game.cover)

            setOnClickListener { onGameClicked(game) }
        }
    }
}