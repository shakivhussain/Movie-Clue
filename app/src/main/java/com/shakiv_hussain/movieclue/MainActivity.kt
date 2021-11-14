package com.shakiv_hussain.movieclue


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shakiv_hussain.movieclue.databinding.ActivityMainBinding
import com.shakiv_hussain.movieclue.utils.ConnectionLiveData
import com.shakiv_hussain.movieclue.utils.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this,MainViewModelFactory()).get(MainViewModel::class.java)

        ConnectionLiveData(this).observe(this,{
            if (it){
                binding.notNetworkLayout.visibility= View.GONE
                viewModel.fetchAllMoviesFromApi()
            }else{
                binding.notNetworkLayout.visibility=View.VISIBLE
            }
        })

    }

}