package cn.sharesdk.plug;

import android.text.TextUtils;
import cn.sharesdk.socialization.CommentFilter;
import cn.sharesdk.socialization.CommentFilter.FilterItem;

public class MyBuilder {
	CommentFilter.Builder builder = new CommentFilter.Builder();
	public CommentFilter.Builder getBuilder(){
		// 非空过滤器
		builder.append(new FilterItem() {
			// 返回true表示是垃圾评论
			public boolean onFilter(String comment) {
				if (TextUtils.isEmpty(comment)) {
					return true;
				} else if (comment.trim().length() <= 0) {
					return true;
				} else if (comment.trim().toLowerCase().equals("null")) {
					return true;
				}
				return false;
			}
	
			@Override
			public int getFilterCode() {
				return 0;
			}
		});
		// 字数上限过滤器
		builder.append(new FilterItem() {
			// 返回true表示是垃圾评论
			public boolean onFilter(String comment) {
				if (comment != null) {
					String pureComment = comment.trim();
					String wordText = "";//cn.sharesdk.framework.utils.R.toWordText(pureComment, 140);
					if (wordText.length() != pureComment.length()) {
						return true;
					}
				}
				return false;
			}
	
			@Override
			public int getFilterCode() {
				return 0;
			}
		});
		return builder;
	}
}
