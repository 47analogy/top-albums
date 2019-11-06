package com.atweed.topalbums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.album_detail_layout.*



class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_detail_layout)

        // TO-DO: MAKE THIS DRY
        val strReleaseDate: String = intent.getStringExtra("releaseDate")
        val strRating: String = intent.getStringExtra("rating")
        val strGenre: String = intent.getStringExtra("genre")
        val strArtistLink: String = intent.getStringExtra("artistLink")
        val strAlbumLink: String = intent.getStringExtra("albumLink")


        details_releaseDate.text = strReleaseDate
        details_rating.text = strRating
        // get sub-string with just the genre type
        val extractedGenre = strGenre.substringAfter("name=").substringBefore(',')
        details_genre.text = extractedGenre.toString()
        details_artistLink.text = strArtistLink
        details_albumLink.text = strAlbumLink

        //details_releaseDate.setText(strReleaseDate) ?? Java way
    }
}