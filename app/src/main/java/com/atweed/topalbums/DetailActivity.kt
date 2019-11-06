package com.atweed.topalbums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.album_detail_layout.*



class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_detail_layout)


        val strReleaseDate = intent.getStringExtra("releaseDate")
        val strRating = intent.getStringExtra("rating")
        val strGenre = intent.getStringExtra("genre")
        val strArtistLink = intent.getStringExtra("artistLink")
        val strAlbumLink = intent.getStringExtra("albumLink")

        details_releaseDate.text = strReleaseDate
        details_rating.text = strRating
        // get sub-string with just the genre type
        val extractedGenre = strGenre.substringAfter("name=").substringBefore(',')
        details_genre.text = extractedGenre.toString()
        details_artistLink.text = strArtistLink
        details_albumLink.text = strAlbumLink
    }
}