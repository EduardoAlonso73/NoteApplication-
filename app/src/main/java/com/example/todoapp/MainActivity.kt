package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavHostFragment()
        setupBarDrawerToggle()


    }

    private fun initNavHostFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    private fun setupBarDrawerToggle() {
        binding.apply {
            toggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(false)

            binding.navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.firstItem -> {Toast.makeText(this@MainActivity, "First Item Clicked", Toast.LENGTH_SHORT).show()}
                    R.id.secondtItem -> {Toast.makeText(this@MainActivity, "Second Item Clicked", Toast.LENGTH_SHORT).show()}
                    R.id.thirdItem -> {Toast.makeText(this@MainActivity, "third Item Clicked", Toast.LENGTH_SHORT).show()}
                }
                true
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {  return true}
        return super.onOptionsItemSelected(item)
    }
}