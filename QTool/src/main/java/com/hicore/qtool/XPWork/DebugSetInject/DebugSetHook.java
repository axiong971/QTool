package com.hicore.qtool.XPWork.DebugSetInject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hicore.HookItem;
import com.hicore.HookUtils.XPBridge;
import com.hicore.ReflectUtils.MClass;
import com.hicore.ReflectUtils.MMethod;
import com.hicore.Utils.Utils;
import com.hicore.qtool.XposedInit.ItemLoader.BaseHookItem;

import java.lang.reflect.Method;

@HookItem(isDelayInit = false,isRunInAllProc = false)
public class DebugSetHook extends BaseHookItem {
    @Override
    public String getTag() {
        return super.getTag();
    }

    @Override
    public boolean startHook() {
        Method hookMethod = getHookMethod();
        XPBridge.HookAfter(hookMethod,param ->{
            Utils.ShowTaost("Item Click");
        });
        return super.startHook();
    }

    @Override
    public boolean isEnable() {
        return super.isEnable();
    }

    @Override
    public String getErrorInfo() {
        return "No Info";
    }

    @Override
    public boolean check() {
        return super.check();
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded();
    }
    public Method getHookMethod(){
        Method m = MMethod.FindMethod(MClass.loadClass("com.tencent.mobileqq.settings.message.AssistantSettingFragment"),
                "doOnCreateView",void.class,new Class[]{
                        LayoutInflater.class, ViewGroup.class, Bundle.class
                });
        return m;
    }
}
