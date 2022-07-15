package com.sirius.test_app

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirius.test_app.adapters.ItemsAdapter
import com.sirius.test_app.databinding.ActivityMainBinding
import com.sirius.test_app.model.RcItem
import com.sirius.test_app.utilities.loadImage
import com.sirius.test_app.utilities.setStars
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val data = DataModel()
    private val itemsAdapter = ItemsAdapter(lifecycleScope)

    private val items = listOf(
        RcItem.Tags(data.tags),
        RcItem.Description(data.description),
        RcItem.RatingsHeader(data.rating, data.gradeCnt),
        RcItem.Reviews(data.reviews)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.root.setBackgroundColor(Color.parseColor("#050B18"))
        binding.back.setOnClickListener {
            showToast("Going back")
        }
        binding.options.setOnClickListener {
            showToast("If you had options\n" +
                    "Then why'd you even stick around\n" +
                    "Long enough for me to find out?")
        }
        with(binding) {
            lifecycleScope.launch {
                loadImage(gameImage, data.image)
                loadImage(gameLogo, data.logo)
            }
            gameTitle.text = data.name
            revCount.text = data.gradeCnt

            with(rcView) {
                layoutManager = LinearLayoutManager(context)
                adapter = itemsAdapter
            }
            actionButton.text = data.action.name
            actionButton.setOnClickListener {
                showToast(data.action.action)
            }
            setStars(listOf(star1, star2, star3, star4, star5), data.rating)
        }
        itemsAdapter.items = items
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}