package android.com.test.net

import android.com.test.AppConfig
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2018/5/22.
 */
object HttpRequest {
    lateinit var api: HttpService

    fun init() {
        val httpBuilder = OkHttpClient.Builder()

        if (AppConfig.isDebug) {//添加http请求信息
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(interceptor)
        }

        //设置连接超时的时间30秒
        val client = httpBuilder.readTimeout(AppConfig.connetTime, TimeUnit.SECONDS)
                .connectTimeout(AppConfig.connetTime, TimeUnit.SECONDS).writeTimeout(AppConfig.connetTime, TimeUnit.SECONDS) //设置超时
                .build()

        val builder = Retrofit.Builder()
                .baseUrl(AppConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(client)

        api = builder.build().create(HttpService::class.java)
    }


    fun getLogin(userid: String): Observable<ResponseBody> {
        return thread(api!!.login(userid))
    }


    /**
     * 后台网络,前台UI线程
     */
    private fun <T> thread(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 错误空拦截
     */
    private fun <T> onErrorEmpty(observable: Observable<T>): Observable<T> {
        return observable.onErrorResumeNext(Observable.empty<Any>() as Observable<out T>)
    }
}