package com.jaygengi.gank.mvp.model.bean

 /**
   * @description: 获取最新一天的干货
   * @author JayGengi
   * @date  2018/11/14 0014 下午 4:33
   * @email jaygengiii@gmail.com
   */
class ToDayEntity {

    var isError: Boolean = false
    var results: ResultsBean? = null
    var category: List<String>? = null

    class ResultsBean {
        var Android: List<CategoryBean>? = null
        var App: List<CategoryBean>? = null
        var iOS: List<CategoryBean>? = null
        var 休息视频: List<CategoryBean>? = null
        var 前端: List<CategoryBean>? = null
        var 拓展资源: List<CategoryBean>? = null
        var 瞎推荐: List<CategoryBean>? = null
        var 福利: List<CategoryBean>? = null
        class CategoryBean {
            /**
             * _id : 5bc49bb99d2122791c972ca9
             * createdAt : 2018-10-15T13:52:57.103Z
             * desc : 新版youtube视频效果
             * images : ["https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8dcpt0g308w0fse83","https://ww1.sinaimg.cn/large/0073sXn7gy1fwyf8gpdc4g308w0fsnpg"]
             * publishedAt : 2018-11-06T00:00:00.0Z
             * source : web
             * type : Android
             * url : https://github.com/moyokoo/YoutubeVideoSample
             * used : true
             * who : miaoyj
             */
            var _id: String? = null
            var createdAt: String? = null
            var desc: String? = null
            var publishedAt: String? = null
            var source: String? = null
            var type: String? = null
            var url: String? = null
            var isUsed: Boolean = false
            var who: String? = null
            var images: List<String>? = null
        }

    }
}
