package com.jaygengi.gank.api

import com.jaygengi.gank.mvp.model.bean.CategoryEntity
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.net.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

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
    @GET(UrlConstant.BASE_URL+"data/福利/{limit}/{page}")
    fun getGirlsInfo(@Path("limit")limit:Int,
                         @Path("page")page:Int): Observable<GirlsEntity>

     /**
      * 获取福利数据信息
      */
     @GET(UrlConstant.BASE_URL+"today")
     fun getToDayInfo(): Observable<ToDayEntity>

     /**
      * 技术干货分类文章
      */
     @GET(UrlConstant.BASE_URL+"search/query/listview/category/{key}/count/{limit}/page/{page} ")
     fun getCategoryInfo(@Path("key")key:String,
                      @Path("limit")limit:Int,
                      @Path("page")page:Int): Observable<CategoryEntity>

 }