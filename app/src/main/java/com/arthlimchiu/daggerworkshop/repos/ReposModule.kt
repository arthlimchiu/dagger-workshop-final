package com.arthlimchiu.daggerworkshop.repos

import com.arthlimchiu.daggerworkshop.Api
import com.arthlimchiu.daggerworkshop.di.ActivityScope
import com.arthlimchiu.daggerworkshop.userdetails.UserRepository
import com.arthlimchiu.daggerworkshop.userdetails.UserRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ReposModule {

    @Provides
    @ActivityScope
    fun providesReposRepository(api: Api): ReposRepository {
        return ReposRepositoryImpl(api)
    }

    @Provides
    @ActivityScope
    fun providesUserRepository(api: Api): UserRepository {
        return UserRepositoryImpl(api)
    }
}