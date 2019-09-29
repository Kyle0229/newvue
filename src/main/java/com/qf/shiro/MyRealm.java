package com.qf.shiro;

import com.qf.domain.SysPermission;
import com.qf.mapper.SysPermissionMapper;
import com.qf.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        List<SysPermission> sysPermissions = sysPermissionMapper.selectPermissionByUserName(username);
        Collection permissions = new HashSet<>();
        for (SysPermission s:sysPermissions){
            permissions.add(s.getPer_name());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = userMapper.selectPsswordByName(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,ByteSource.Util.bytes("likun"),getName());

        return simpleAuthenticationInfo;
    }
}
