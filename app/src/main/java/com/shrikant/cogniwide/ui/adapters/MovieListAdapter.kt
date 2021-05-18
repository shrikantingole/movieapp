package com.shrikant.cogniwide.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.network.shared.domain.exception.movie.MovieResult
import com.network.shared.util.loadImage
import com.shrikant.cogniwide.BuildConfig
import com.shrikant.cogniwide.databinding.ItemMovieBinding

class MovieListAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<MovieResult> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MovieHolder)?.setData(list[position])
    }

    fun setItemList(list: List<MovieResult>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root), RequestListener<Drawable> {

        fun setData(item: MovieResult) {
            with(binding) {
                tvTitle.text = item.title
                item.posterPath?.let {
                    ivPoster.loadImage(BuildConfig.imageUrl.plus(it))
                }
            }
        }

        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

    }

}