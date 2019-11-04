package com.atweed.topalbums

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*


class AlbumAdapter(
    private val albumList: List<Result>,
    private val listener: (Album) -> Unit
    ): RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false))

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) = holder.bind(albumList[position], listener)

    override fun getItemCount() = albumList.size

    class AlbumHolder(albumView: View): RecyclerView.ViewHolder(albumView) {

        fun bind(album: Result, listener: (Album) -> Unit) = with(itemView) {
            name.text = album.name
            artistName.text = album.artistName
            Picasso.get().load(album.artworkUrl100).into(albumArtwork)
          //  setOnClickListener { listener() }
        }
    }
}