package com.example.favqs.di

import com.example.favqs.data.remote.QuoteApiService
import com.example.favqs.data.remote.repository.AuthRepositoryImp
import com.example.favqs.data.remote.repository.QuotesRepositoryImp
import com.example.favqs.domain.repository.AuthRepository
import com.example.favqs.domain.repository.QuotesRepository
import com.example.favqs.util.API_KEY
import com.example.favqs.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteApiService(interceptor: Interceptor): QuoteApiService{
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client( OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(quoteApiService: QuoteApiService):AuthRepository{
        return AuthRepositoryImp(quoteApiService)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(quoteApiService: QuoteApiService) : QuotesRepository{
        return QuotesRepositoryImp(quoteApiService)
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Authorization", "Token token=$API_KEY")
            chain.proceed(request.build())
        }
    }
}