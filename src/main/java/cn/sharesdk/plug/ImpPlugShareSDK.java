package cn.sharesdk.plug;

import android.content.Context;
import android.view.View;

import com.apkplug.component.sharesdk.PlugShareInfo;
import com.apkplug.component.sharesdk.PlugShareSDK;
import com.apkplug.component.sharesdk.PlugTopicTitle;

import cn.sharesdk.demo.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.socialization.CommentListPage;
import cn.sharesdk.socialization.QuickCommentBar;
import cn.sharesdk.socialization.component.TopicTitle;


public class ImpPlugShareSDK implements PlugShareSDK{
	private Context context=null;
	public ImpPlugShareSDK(Context context){
		this.context=context;
	}
	@Override
	public void showShare(PlugShareInfo arg0) {
		initOnekeyShare(arg0).show(this.context);
	}
	@Override
	public View getQuickCommentBar(PlugTopicTitle arg0,String shareContent, PlugShareInfo arg1) {
		QuickCommentBar qcBar=new QuickCommentBar(context);
		qcBar.setTopic(arg0.getTopicId(), arg0.getTopicTitle(), arg0.getTopicPublishTime(), arg0.getTopicAuthor());
		qcBar.setTextToShare(shareContent);
		//qcBar.getBackButton().setOnClickListener(this);
		qcBar.setAuthedAccountChangeable(false);
		//qcBar.setCommentFilter(new MyBuilder().getBuilder().build());
		qcBar.setOnekeyShare(initOnekeyShare(arg1));
		return qcBar;
	}
	@Override
	public View getTopicTitle(PlugTopicTitle arg0) {
		TopicTitle topic=new TopicTitle(context);
		topic.setTitle(arg0.getTopicTitle());
		topic.setPublishTime(arg0.getTopicPublishTime());
		topic.setAuthor(arg0.getTopicAuthor());
		return topic;
	}
	@Override
	public void showCommentListPage(PlugTopicTitle arg0, PlugShareInfo arg1) {
		CommentListPage page = new CommentListPage();
		page.setTopic(arg0.getTopicId(), arg0.getTopicTitle(), arg0.getTopicPublishTime(), arg0.getTopicAuthor());
		//page.setCommentFilter(new MyBuilder().getBuilder().build());
		page.setOnekeyShare(initOnekeyShare(arg1));
		page.show(context, null);
	}
	@Override
	public int version() {
		
		return 300;
	}

	
	private OnekeyShare initOnekeyShare(PlugShareInfo info) {
		OnekeyShare oks = new OnekeyShare();
		oks.setAddress(info.getAddress());
		oks.setTitle(info.getTitle());
		oks.setTitleUrl(info.getTitleUrl());
		oks.setText(info.getText());
		oks.setImagePath(info.getImagePath());
		oks.setImageUrl(info.getImageUrl());
		oks.setUrl(info.getUrl());
		oks.setFilePath(info.getFilePath());
		oks.setComment(info.getComment());
		oks.setSite(info.getSite());
		oks.setSiteUrl(info.getSiteUrl());
		oks.setVenueName(info.getVenueName());
		oks.setVenueDescription(info.getVenueDescription());
		oks.setLatitude(info.getLatitude());
		oks.setLongitude(info.getLongitude());
		if(info.isDisableSSOWhenAuthorize()){
			oks.disableSSOWhenAuthorize();
		}
		oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
			public void onShare(Platform platform, ShareParams paramsToShare) {
				// 改写twitter分享内容中的text字段，否则会超长，
				// 因为twitter会将图片地址当作文本的一部分去计算长度
				if ("Twitter".equals(platform.getName())) {
					paramsToShare.setText(platform.getContext().getString(R.string.share_content_short));
				}
			}
		});
		return oks;
	}
	
}
