package com.jaygengi.gank.net

 /**  
   * @description: 封装返回的数据
   * @author JayGengi
   * @date  2018/11/13 0013 下午 3:31
   * @email jaygengiii@gmail.com
   */
class BaseResponse<T>(val error:Boolean,
                      val results:T)