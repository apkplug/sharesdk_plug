package cn.sharesdk.plug;



import org.apkplug.Bundle.ClassHook;

import cn.sharesdk.socialization.sample.wxapi.WXEntryActivity;

/**
 * Created by love on 15/12/8.
 */
public class ImpClassHook implements ClassHook {
    @Override
    public boolean isHandler(String s) {
        if(s.endsWith(".wxapi.WXEntryActivity")){
            return true;
        }
     
        return false;
    }

    @Override
    public Object loadClass(String s) {
        if(s.endsWith(".wxapi.WXEntryActivity")){
            return WXEntryActivity.class;
        }
  
        return null;
    }
}
