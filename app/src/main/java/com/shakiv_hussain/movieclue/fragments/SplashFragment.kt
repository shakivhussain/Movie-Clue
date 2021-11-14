package com.shakiv_hussain.movieclue.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shakiv_hussain.movieclue.R
import com.shakiv_hussain.movieclue.databinding.FragmentSplashBinding
import com.shakiv_hussain.movieclue.utils.ConnectionLiveData
import com.shakiv_hussain.movieclue.utils.TempUtil
import kotlinx.coroutines.*

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=FragmentSplashBinding.inflate(inflater)

        TempUtil.hideSystemUI(window = requireActivity().window)

        context?.let { it ->
            ConnectionLiveData(it).observe(viewLifecycleOwner,{isInternet ->
                if (isInternet){
                    binding.notNetworkLayout.visibility=View.GONE
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2500)
                        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    }
                }else{
                    Log.d("TAG","Internet is off")
                    binding.notNetworkLayout.visibility=View.VISIBLE
                }
            })
        }

        return binding.root
    }




}