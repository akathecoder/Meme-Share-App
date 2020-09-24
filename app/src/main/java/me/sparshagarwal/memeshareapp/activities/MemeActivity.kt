package me.sparshagarwal.memeshareapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import com.agrawalsuneet.dotsloader.loaders.LazyLoader
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_meme.*
import me.sparshagarwal.memeshareapp.R
import me.sparshagarwal.memeshareapp.utils.MySingleton

class MemeActivity : AppCompatActivity() {

    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var currentMemeUri : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme)

        /** Action Bar Setup */
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        backBtn.setOnClickListener {
            finish()
        }

        /** Share Button Setup */
        shareBtn.setOnClickListener {
//            Toast.makeText(this, "Share Button Clicked", Toast.LENGTH_SHORT).show()

            val shareIntent = Intent(android.content.Intent.ACTION_SEND)
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, currentMemeUri)
//            context.startActivities(arrayOf(Intent.createChooser(shareIntent, "Check this Awesome Meme . . .")))
            startActivity(Intent.createChooser(shareIntent, "Check this Awesome Meme . . ."))
        }

        /** Next Button Setup */
        nextBtn.setOnClickListener {
            loadingBar.visibility = View.VISIBLE
            loadMeme()
        }

        /** Setup Lazy Loader */
        var lazyLoader = LazyLoader(
            this, 15, 5,
            ContextCompat.getColor(this, R.color.loader_selected),
            ContextCompat.getColor(this, R.color.loader_selected),
            ContextCompat.getColor(this, R.color.loader_selected)
        )
            .apply {
                animDuration = 500
                firstDelayDuration = 100
                secondDelayDuration = 200
                interpolator = DecelerateInterpolator()
            }
        loadingBar.addView(lazyLoader)
        loadMeme()

        /** Implement Gesture Detection */


    }

    public fun loadMeme() {
        // Instantiate the RequestQueue.
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
//                Toast.makeText(this, "Meme", Toast.LENGTH_SHORT).show()
//                Log.i("Meme", response.toString())
                loadingBar.visibility = View.GONE

                Log.i("Meme", response.getString("url"))

                Glide.with(this)
                    .load(response.getString("url"))
                    .into(iv_meme)

                currentMemeUri = Uri.parse(url)
            },
            { error ->
                Toast.makeText(this, "Error, Please Retry", Toast.LENGTH_SHORT).show()
                Glide.with(this)
                    .clear(iv_meme)

            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }


}