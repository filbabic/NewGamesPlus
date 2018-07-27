package com.babic.filip.main.ui.topRated.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.babic.filip.core.imageLoading.loadImage
import com.babic.filip.main.R
import com.babic.filip.main.domain.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val onGameClicked: (Game) -> Unit) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val items = mutableListOf<Game>()

    fun setData(newItems: List<Game>) {
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

        fun showData(game: Game, onGameClicked: (Game) -> Unit) = with(itemView) {
            gameImage.loadImage(game.cover.url)

            setOnClickListener { onGameClicked(game) }
        }
    }
}