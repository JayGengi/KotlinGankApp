package com.jaygengi.gank.mvp.model.bean

class CategoryEntity {


    /**
     * count : 10
     * error : false
     * results : [{"desc":"The Best DevOps Tools on OSX","ganhuo_id":"56cc6d22421aa95caa70793a","publishedAt":"2016-02-26T11:58:00.331000","readability":"","type":"iOS","url":"https://dzone.com/articles/the-best-devops-tools-on-osx","who":"Andrew Liu"},{"desc":"加载网络图片第三方库ssloadURLImage","ganhuo_id":"56cc6d23421aa95caa707951","publishedAt":"2015-10-21T02:57:40.909000","readability":"","type":"iOS","url":"https://github.com/shareJOBS/ssloadURLImage","who":"Andrew Liu"},{"desc":"强制修改 iOS 状态栏颜色","ganhuo_id":"56cc6d23421aa95caa70795b","publishedAt":"2015-10-22T02:06:07.739000","readability":"","type":"iOS","url":"http://www.jianshu.com/p/9d6b6f790493","who":"Andrew Liu"},{"desc":"Thoughts on Functional Programming in Swift","ganhuo_id":"56cc6d23421aa95caa7079a4","publishedAt":"2015-11-16T03:55:17.189000","readability":"","type":"iOS","url":"http://natashatherobot.com/functional-programming-in-swift/#","who":"CallMeWhy"},{"desc":"Swift Scripting By Example","ganhuo_id":"56cc6d23421aa95caa7079ba","publishedAt":"2015-11-20T03:54:49.808000","readability":"","type":"iOS","url":"http://swift.ayaka.me/posts/2015/11/5/swift-scripting-generating-acknowledgements-for-cocoapods-and-carthage-dependencies","who":"CallMeWhy"},{"desc":"通过 RAC 的 RACSignal 将 iOS 的任务转变为后台任务","ganhuo_id":"56cc6d23421aa95caa7079c7","publishedAt":"2015-05-18T03:52:44.016000","readability":"","type":"iOS","url":"http://spin.atomicobject.com/2015/05/14/ios-background-task-reactivecocoa/#.VVSask7KdL8.hackernews","who":"CallMeWhy"},{"desc":"iOS 富文本控件","ganhuo_id":"56cc6d23421aa95caa7079bf","publishedAt":"2015-11-18T05:17:57.241000","readability":"","type":"iOS","url":"https://github.com/ibireme/YYText","who":"mthli"},{"desc":"XCPlayground，是时候展现 Playground 的真正实力了。","ganhuo_id":"56cc6d23421aa95caa7079d3","publishedAt":"2016-03-03T12:12:56.684000","readability":"","type":"iOS","url":"http://nshipster.com/xcplayground/?utm_campaign=This%2BWeek%2Bin%2BSwift&utm_medium=web&utm_source=This_Week_in_Swift_39","who":"CallMeWhy"},{"desc":"AFNetworking 安全bug的回复","ganhuo_id":"56cc6d23421aa95caa7079d8","publishedAt":"2015-05-27T04:21:13.831000","readability":"","type":"iOS","url":"http://www.yming9.com/?p=579","who":"Andrew Liu"},{"desc":"PaintCode教程 译自Ranwenderlich:http://www.raywenderlich.com/100281/paintcode-for-designers-getting-started","ganhuo_id":"56cc6d23421aa95caa7079d9","publishedAt":"2016-03-31T11:44:55.091000","readability":"","type":"iOS","url":"http://www.jianshu.com/p/5e75408812df","who":"Andrew Liu"}]
     */

    var count: Int = 0
    var isError: Boolean = false
    var results: List<ResultsBean>? = null

    class ResultsBean {
        /**
         * desc : The Best DevOps Tools on OSX
         * ganhuo_id : 56cc6d22421aa95caa70793a
         * publishedAt : 2016-02-26T11:58:00.331000
         * readability :
         * type : iOS
         * url : https://dzone.com/articles/the-best-devops-tools-on-osx
         * who : Andrew Liu
         */

        var desc: String? = null
        var ganhuo_id: String? = null
        var publishedAt: String? = null
        var readability: String? = null
        var type: String? = null
        var url: String? = null
        var who: String? = null
    }
}
