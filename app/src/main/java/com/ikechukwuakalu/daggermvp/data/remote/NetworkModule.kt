package com.ikechukwuakalu.daggermvp.data.remote

import android.content.Context
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@Module
abstract class NetworkModule {

    @Module
    companion object {

       /* @Provides
        @JvmStatic
        fun provideInterceptor() : HttpLo*/

        @Provides
        @JvmStatic
        fun provideCache(context: Context): Cache {
            val cacheFile = File(context.cacheDir, "dmvp")
            cacheFile.mkdirs()
            return Cache(cacheFile, 10 * 1024 * 1024)
        }

        @Provides
        @JvmStatic
        fun provideOkHttp(cache: Cache) : OkHttpClient {
            return OkHttpClient.Builder()
                    .cache(cache)
                    .build()
        }

        @Provides
        @JvmStatic
        fun providePicasso(context: Context, okHttpClient: OkHttpClient) : Picasso {
            return Picasso.Builder(context)
                    .downloader(OkHttpDownloader(context))
                    .build()
        }

        @Provides
        @JvmStatic
        fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl("https://api.github.com/")
                    .build()
        }
    }
}