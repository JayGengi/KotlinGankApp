package com.jaygengi.gank.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

 /**
   * @description: Api 接口
   * @author JayGengi
   * @date  2018/11/13 0013 下午 2:01
   * @email jaygengiii@gmail.com
   */

interface ApiService{

    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getFirstHomeData(@Query("num") num:Int): Observable<Any>

}