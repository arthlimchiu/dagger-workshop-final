package com.arthlimchiu.daggerworkshop.userdetails

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arthlimchiu.daggerworkshop.Api
import com.arthlimchiu.daggerworkshop.R
import com.arthlimchiu.daggerworkshop.appComponent
import dagger.android.AndroidInjection
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class UserDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: UserDetailsViewModelFactory

    private lateinit var viewModel: UserDetailsViewModel

    private lateinit var fullName: TextView
    private lateinit var numOfRepos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        fullName = findViewById(R.id.full_name)
        numOfRepos = findViewById(R.id.num_of_repos)

        viewModel = ViewModelProviders.of(this, factory).get(UserDetailsViewModel::class.java)

        viewModel.user.observe(this, Observer { user ->
            fullName.text = user.name
            numOfRepos.text = "Public Repos: ${user.repos}"
        })

        val username = intent.getStringExtra("username")
        viewModel.searchUser(username)
    }
}
