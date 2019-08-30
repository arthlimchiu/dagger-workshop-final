package com.arthlimchiu.daggerworkshop.repos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arthlimchiu.daggerworkshop.Api
import com.arthlimchiu.daggerworkshop.R
import com.arthlimchiu.daggerworkshop.appComponent
import com.arthlimchiu.daggerworkshop.userdetails.UserRepository
import dagger.android.AndroidInjection
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ReposActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ReposViewModelFactory

    @Inject
    lateinit var userRepository: UserRepository

    private lateinit var viewModel: ReposViewModel

    private lateinit var repos: RecyclerView
    private lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        repos = findViewById(R.id.repos)
        repos.layoutManager = LinearLayoutManager(this)
        reposAdapter = ReposAdapter(listOf())
        repos.adapter = reposAdapter

        viewModel = ViewModelProviders.of(this, factory).get(ReposViewModel::class.java)

        viewModel.repos.observe(this, Observer { repositories ->
            reposAdapter.updateRepos(repositories)
        })

        val username = intent.getStringExtra("username")
        viewModel.getRepos(username)

        userRepository
            .getUser(
                username,
                {},
                {}
            )
    }
}
