package cn.sharesdk.socialization.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import cn.sharesdk.demo.R;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class PlugMainActivity extends Activity {
	
	private Button bt_share;
	private Button bt_comment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.plugmain);
		bt_share = (Button) findViewById(R.id.bt_share);
		bt_comment = (Button) findViewById(R.id.bt_comment);
		
		
		/**
		 * 点击跳转分享界面
		 * 
		 */
		bt_share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showShare();
			}
		});
		
		bt_comment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 主应用需要往插件中传入以下数据
				 * 1. 评论内容
				 * 2. 用户信息（用户信息缓存到sp中，在MyPlatform中读出来）
				 */
				
				DataUtils.userAvatar = "http://mob.com/Public/Frontend/images/android-log-bg.png";//用户头像
				DataUtils.userGender = "Male";//用户性别
				DataUtils.userId = "112233aass";//用户ID
				DataUtils.userNickname = "xiaoming";//用户昵称
				
				Intent intent  = new Intent();
				Bundle bundle = new Bundle();

				bundle.putString("topicId", "20160305111111");
				bundle.putString("topicTitle", "标题");
				bundle.putString("topicPublishTime", "20160305");
				bundle.putString("topicAuthor", "发布人");
				bundle.putString("imageuri", "http://echoapp.oss-cn-beijing.aliyuncs.com/78144f6fac654aa49748bcfaa122e565_headicon.jpg");
				bundle.putString("content", "我是内容。。。");
				
				intent.putExtras(bundle);
				intent.setClass(PlugMainActivity.this, MainActivity.class);
				startActivity(intent);
				

				
				
			}
		});
	}
	

	/**
	 * 这个方法里配置的参数是需要从主应用传入的
	 */
	private void showShare() {
		 ShareSDK.initSDK(this);
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize();
		 
		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle("我是分享标题");//微信分享显示的标题
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用  微博、人人网、QQ空间
		 oks.setTitleUrl("http://sharesdk.cn");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("我是分享文字");//微信分享显示的文字
         // 微信的两个平台、Linked-In支持此字段 
         oks.setImageUrl("http://echoapp.oss-cn-beijing.aliyuncs.com/78144f6fac654aa49748bcfaa122e565_headicon.jpg"); //微信分享显示的小图像
         
		 // url仅在微信（包括好友和朋友圈）中使用
		 oks.setUrl("http://123.56.45.223:8080/echo/html/moblie/index.html#/app/merchant/bc0001c3da3940e89b80a783af9a8b32");//微信分享跳转的链接
		 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		 oks.setComment("我是测试评论文本");
		 // site是分享此内容的网站名称，仅在QQ空间使用
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 oks.setSiteUrl("http://sharesdk.cn");
		 //评论和赞模块需要写上自己平台，这里屏蔽掉不显示，显示出来没意义
		 oks.addHiddenPlatform(new MyPlatform(this).getName());
		 // 启动分享GUI
		 oks.show(this);
	}
	
	
	
	

}
