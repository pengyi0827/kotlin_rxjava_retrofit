package android.com.test

import android.support.v7.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable


/**
 * Created by Administrator on 2018/5/21.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    var ACTIVITIES = ArrayList<Activity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ACTIVITIES.add(this);
        setContentView(getLayoutID())
        init()

    }


    /**
     * Rx生命周期
     */
    fun <T> rxDestroy(observable: Observable<T>): Observable<T> {
        return observable.compose(this.bindToLifecycle())
    }

    override fun onDestroy() {
        super.onDestroy()
        ACTIVITIES.remove(this);
    }

    /**
     * 退出所有页面
     */
    protected fun exit() {
        for (act in ACTIVITIES) {
            act.finish()
        }
    }


    /**
     * 绑定控件之前必须初始化布局
     *
     * @return 布局ID
     */
    protected abstract fun getLayoutID(): Int

    /**
     * 初始化完成，替代[.onCreate]
     */
    protected abstract fun init()
}