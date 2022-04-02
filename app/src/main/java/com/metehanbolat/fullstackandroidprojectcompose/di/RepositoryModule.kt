package com.metehanbolat.fullstackandroidprojectcompose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.metehanbolat.fullstackandroidprojectcompose.data.repository.DataStoreOperationsImpl
import com.metehanbolat.fullstackandroidprojectcompose.data.repository.RepositoryImpl
import com.metehanbolat.fullstackandroidprojectcompose.domain.repository.DataStoreOperations
import com.metehanbolat.fullstackandroidprojectcompose.domain.repository.Repository
import com.metehanbolat.fullstackandroidprojectcompose.util.Constants.PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_NAME) }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(dataStore: DataStore<Preferences>): DataStoreOperations {
        return DataStoreOperationsImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideRepository(dataStoreOperations: DataStoreOperations): Repository {
        return RepositoryImpl(dataStoreOperations = dataStoreOperations)
    }
}