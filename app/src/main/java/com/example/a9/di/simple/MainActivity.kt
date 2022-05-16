package com.example.a9.di.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a9.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    @Named("USER_NAME")
    lateinit var userName:String

    @Inject
    //@SiteName
    @Named("SITE_NAME")
    lateinit var siteName: String

    @Inject
    @Named("APP_INFO")
    lateinit var appInfo : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            textView.text = userName
            textView2.text = siteName
            textView3.text = appInfo
        }
    }
}