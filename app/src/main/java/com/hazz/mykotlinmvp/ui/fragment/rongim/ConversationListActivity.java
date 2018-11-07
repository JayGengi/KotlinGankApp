package com.hazz.mykotlinmvp.ui.fragment.rongim;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hazz.mykotlinmvp.R;


 /**  
   * @description: 会话列表
   * @author JayGengi
   * @date  2018/10/30 0030 上午 10:49
   * @email jaygengiii@gmail.com
   */

public class ConversationListActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);
    }
}
