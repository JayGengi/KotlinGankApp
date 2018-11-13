package com.jaygengi.gank.base



 /**  
   * @description: Presenterj基类
   * @author JayGengi
   * @date  2018/11/13 0013 上午 10:35
   * @email jaygengiii@gmail.com
   */


interface IPresenter<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}
