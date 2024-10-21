package com.example.tugaspersonal2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.tugaspersonal2.databinding.ActivityDetailEventBinding
import kotlinx.coroutines.launch

class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailEventBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Detail Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        IntentCompat.getParcelableExtra(intent, "detail_event", RequestEventAPI.Event::class.java)?.let { eventDetail ->
            Glide.with(baseContext)
                .load(eventDetail.mediaCover)
                .into(binding.detailMediaCover)

            Glide.with(baseContext)
                .load(eventDetail.imageLogo)
                .into(binding.detailImagePoster)

            binding.detailTitle.text = eventDetail.name
            binding.detailEventCategory.text = "Category: ${eventDetail.name}"
            binding.detailEventOwner.text = "Owner: ${eventDetail.ownerName}"
            binding.detailEventCity.text = "Placement: ${eventDetail.cityName}"
            binding.detailEventQuota.text = "Quota: ${eventDetail.quota}"
            binding.detailCurrentRegistrants.text = "Owner: ${eventDetail.registrants}"
            binding.detailEventDescription.text = Html.fromHtml(eventDetail.description, Html.FROM_HTML_MODE_LEGACY)

            binding.detailOpenBrowser.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(eventDetail.link))
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail, menu!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}