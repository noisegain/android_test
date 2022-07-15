package com.sirius.test_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirius.test_app.adapters.ItemsAdapter
import com.sirius.test_app.databinding.ActivityMainBinding
import com.sirius.test_app.model.RcItem
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val data = DataModel()
    private val itemsAdapter = ItemsAdapter(lifecycleScope)

    private val items = listOf(
        RcItem.Description(data.description),
        //RcItem.Tags(data.tags),
        RcItem.RatingsHeader(data.rating, data.gradeCnt)
//        RcItem.Review(data.reviews)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            with(rcView) {
                layoutManager = LinearLayoutManager(context)
                adapter = itemsAdapter
            }
        }
        itemsAdapter.items = items
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}