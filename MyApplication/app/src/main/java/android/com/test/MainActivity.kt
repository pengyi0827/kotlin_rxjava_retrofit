package android.com.test

import android.com.test.net.HttpRequest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import java.util.*


class MainActivity : BaseActivity() {

    lateinit var adapter:recyclerViewadapter
    var lists = ArrayList<DataBean>()

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        initData()
        initView()
    }

    fun initView(){
        val m = LinearLayoutManager(this@MainActivity)
        m.orientation = LinearLayoutManager.HORIZONTAL
        recycler_View.layoutManager = m
        adapter = recyclerViewadapter(lists, this@MainActivity)
        recycler_View.adapter = adapter

        btn_okhttp.setOnClickListener {
            rxDestroy(HttpRequest.getLogin("cccccccc")).subscribe(object : Observer<ResponseBody> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(responseBody: ResponseBody) {
                    try {
                        tv_https.setText("服务器返回的结果为：" + responseBody.string().toString())
                    } catch (e: Exception) {

                    }
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
        }
    }

    fun initData(){
        lists.add(DataBean("Smart", "青青原上草，一岁一枯荣"))
        lists.add(DataBean("Smart", "青青原上草，一岁一枯荣"))
        lists.add(DataBean("Smart", "青青原上草，一岁一枯荣"))
        lists.add(DataBean("Smart", "青青原上草，一岁一枯荣"))
        lists.add(DataBean("Smart", "青青原上草，一岁一枯荣"))
        lists.add(DataBean("Smart", "青青原上草，一岁一枯荣"))
    }
}
