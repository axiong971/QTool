package com.hicore.qtool.QQManager;

import com.hicore.LogUtils.LogUtils;
import com.hicore.ReflectUtils.MClass;
import com.hicore.ReflectUtils.MMethod;
import com.hicore.qtool.HookEnv;
import com.hicore.qtool.XPWork.QQProxy.BaseChatPie;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class QQEnvUtils {
    private static final String TAG = "QQInfoUtils";
    public static String getCurrentUin(){
        try{
            Object AppRuntime = getAppRuntime();
            return MMethod.CallMethod(AppRuntime,"getCurrentAccountUin",String.class);
        }catch (Exception e){
            LogUtils.fetal_error(TAG,e);
            return "";
        }
    }
    public static String getCurrentTinyID(){
        return null;
    }
    public static ArrayList<FriendInfo> getFriendList(){
        return null;
    }
    public static class FriendInfo{
        public String Uin;
        public String Name;
        public int GroupID;
        public Object source;
    }
    public static Object getAppRuntime() throws Exception {
        Object sApplication = MMethod.CallMethod(null, MClass.loadClass("com.tencent.common.app.BaseApplicationImpl"),
                "getApplication",MClass.loadClass("com.tencent.common.app.BaseApplicationImpl"),new Class[0]);
        return MMethod.CallMethod(sApplication,"getRuntime",MClass.loadClass("mqq.app.AppRuntime"));
    }
    public static Object GetIGpsManager() throws Exception {
        return getRuntimeService(MClass.loadClass("com.tencent.mobileqq.qqguildsdk.api.IGPSService"));
    }
    public static Object getRuntimeService(Class<?> Clz) throws Exception {
        Method Invoked = null;
        for(Method fs : getAppRuntime().getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredMethods()) {
            if(fs.getName().equals("getRuntimeService"))
            {
                Invoked = fs;
                break;
            }
        }
        Object MessageFacade = Invoked.invoke(getAppRuntime(),Clz,"");
        return MessageFacade;
    }
    public static void sendLike(String Uin){

    }
    public static long GetServerTime() throws Exception {
        return MMethod.CallMethod(null,MClass.loadClass("com.tencent.mobileqq.msf.core.NetConnInfoCenter"),"getServerTime",long.class,new Class[0]);
    }
    public static Object getBusinessHandler(String Clz) throws Exception {
        Method Invoked = null;
        for(Method fs : HookEnv.AppInterface.getClass().getSuperclass().getSuperclass().getDeclaredMethods())
        {
            if(fs.getName().equals("getBusinessHandler"))
            {
                Invoked = fs;
                break;
            }
        }
        Object MessageFacade = Invoked.invoke(HookEnv.AppInterface,Clz);
        return MessageFacade;
    }
    public static Object GetRevokeHelper(){
        try{
            Object obj = MClass.NewInstance(MClass.loadClass("com.tencent.mobileqq.activity.aio.helper.AIORevokeMsgHelper"), BaseChatPie.cacheChatPie);
            return obj;
        }catch (Exception ex)
        {
            return null;
        }
    }
}