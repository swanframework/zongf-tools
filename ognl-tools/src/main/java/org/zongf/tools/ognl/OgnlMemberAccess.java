package org.zongf.tools.ognl;

import ognl.MemberAccess;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.ReflectPermission;
import java.util.Map;

/**
 * 自定义MemberAccess, copy from mybatis 源码
 * @author zongf
 */
public class OgnlMemberAccess implements MemberAccess {

    private final boolean canControlMemberAccessible;

    public OgnlMemberAccess() {
        this.canControlMemberAccessible = canControlMemberAccessible();
    }

    @Override
    public Object setup(Map context, Object target, Member member, String propertyName) {
        Object result = null;
        if (isAccessible(context, target, member, propertyName)) {
            AccessibleObject accessible = (AccessibleObject) member;
            if (!accessible.isAccessible()) {
                result = Boolean.FALSE;
                accessible.setAccessible(true);
            }
        }
        return result;
    }

    @Override
    public void restore(Map context, Object target, Member member, String propertyName, Object state) {
        if (state != null) {
            ((AccessibleObject) member).setAccessible((Boolean) state);
        }
    }

    @Override
    public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
        return canControlMemberAccessible;
    }

    public static boolean canControlMemberAccessible() {
        try {
            SecurityManager securityManager = System.getSecurityManager();
            if (null != securityManager) {
                securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
            }
        } catch (SecurityException e) {
            return false;
        }
        return true;
    }

}
