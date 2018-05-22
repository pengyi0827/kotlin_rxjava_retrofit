package android.com.test

import android.app.Application
import android.com.test.net.HttpRequest
import android.content.Context

/**
 * Created by Administrator on 2018/5/21.
 */
class MyApplication :Application(){
    companion object {
        lateinit var m_con:Context
    }
    override fun onCreate() {
        super.onCreate()
        m_con = this;
        HttpRequest.init()
    }
}