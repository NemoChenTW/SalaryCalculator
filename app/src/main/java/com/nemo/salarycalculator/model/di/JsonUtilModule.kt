package com.nemo.salarycalculator.model.di

import com.nemo.salarycalculator.model.repository.util.GsonToKObj
import com.nemo.salarycalculator.model.repository.util.JsonUtil
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class JsonUtilModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindGsonToKObj(gsonToKObj: GsonToKObj): JsonUtil
}
