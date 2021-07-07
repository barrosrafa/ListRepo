package com.barros.listrepo

import android.os.Bundle
import com.barros.listrepo.base.BaseActivity
import com.barros.listrepo.databinding.ActivityRepositoriesBinding

class RepositoriesActivity : BaseActivity() {

    private lateinit var binding: ActivityRepositoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRepositoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}