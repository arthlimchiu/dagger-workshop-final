package com.arthlimchiu.daggerworkshop.repos

import com.arthlimchiu.daggerworkshop.di.ActivityScope
import com.arthlimchiu.daggerworkshop.userdetails.User
import dagger.Binds
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ReposModule::class
    ]
)
interface ReposSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun user(user: User): User

        fun build(): ReposSubcomponent
    }

    fun inject(activity: ReposActivity)
}