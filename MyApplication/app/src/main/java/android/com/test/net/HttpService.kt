package android.com.test.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Administrator on 2018/5/22.
 */
interface HttpService {

    @FormUrlEncoded
    @POST("index.php?r=api/login")
    fun login(@Field("c") userId: String): Observable<ResponseBody>

}