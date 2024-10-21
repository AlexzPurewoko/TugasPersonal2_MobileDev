package com.example.tugaspersonal2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaspersonal2.databinding.NewActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: NewActivityMainBinding

    private lateinit var loadingTextView: TextView
    private lateinit var contentRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NewActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_list -> supportFragmentManager.commit {
                    replace<DashboardFragment>(R.id.fragment_container)
                }
                R.id.action_favorite -> supportFragmentManager.commit {
                    replace<FavoriteFragment>(R.id.fragment_container)
                }
            }
            true
        }

        binding.bottomMenu.selectedItemId = R.id.action_list
    }
}