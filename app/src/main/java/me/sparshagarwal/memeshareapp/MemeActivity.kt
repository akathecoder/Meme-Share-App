package me.sparshagarwal.memeshareapp

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
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_meme.*

class MemeActivity : AppCompatActivity() {

    private lateinit var mDetector: GestureDetectorCompat


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


    }

    private fun loadMeme() {
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