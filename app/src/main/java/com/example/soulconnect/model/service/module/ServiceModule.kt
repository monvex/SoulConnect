package com.example.soulconnect.model.service.module

import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.StorageService
import com.example.soulconnect.model.service.impl.AccountServiceImpl
import com.example.soulconnect.model.service.impl.LogServiceImpl
import com.example.soulconnect.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService
    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}