package cn.sharesdk.plug;
import com.apkplug.component.sharesdk.PlugShareSDK;

import org.apkplug.Bundle.ClassHookService;
import org.apkplug.Bundle.OSGIServiceAgent;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.socialization.Socialization;


public class SimpleBundle implements BundleActivator
{
	private ServiceRegistration m_reg = null;
	private ImpPlugShareSDK sharesdk=null;
	private OSGIServiceAgent<ClassHookService> agentclass=null;
	ImpClassHook hook=null;
    public void start(BundleContext context) throws Exception
    {
        System.err.println("你好我是"+context.getBundle().getName()+"插件,我已经启动了 我的BundleId为："+context.getBundle().getBundleId());
        
        ShareSDK.initSDK(context.getAndroidContext());
		ShareSDK.registerService(Socialization.class);
		Socialization service = ShareSDK.getService(Socialization.class);
		service.setCustomPlatform(new MyPlatform(context.getAndroidContext()));
		
		
        sharesdk=new ImpPlugShareSDK(context.getAndroidContext());
        m_reg = context.registerService(
        		PlugShareSDK.class.getName(),
        		sharesdk,
				null);
        
        
        hook=new ImpClassHook();
        agentclass=new OSGIServiceAgent<ClassHookService>(context,ClassHookService.class);
        try {
            agentclass.getService().registerHook(context, hook);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
   
    public void stop(BundleContext context)
    {
    	System.err.println("你好我是"+context.getBundle().getName()+"视频插件,我被停止了 我的BundleId为："+context.getBundle().getBundleId());
    	m_reg.unregister();
    	
    	 try {
             agentclass.getService().unregisterHook(context, hook);
         } catch (Exception e) {
             // TODO 自动生成的 catch 块
             e.printStackTrace();
         }

    }

}
