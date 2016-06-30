/**
 * 
 */
package com.llsfw.demo.security.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.llsfw.core.security.realm.UserAuthenRealm;

/**
 * @author Administrator
 *
 */
public class DemoUserAuthenRealm extends UserAuthenRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获得所关联的权限
        List<String> roleList = new ArrayList<String>();
        Set<String> permissions = new HashSet<String>();

        // 设置所关联的权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(new HashSet<String>(roleList));
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取用户信息
        String username = (String) token.getPrincipal();

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,
                "f9153ea5bfa7e6e53ca559d2abbe56dc", ByteSource.Util.bytes("95fcb114c8df19ce07df8dab45383de2"),
                getName());
        return authenticationInfo;
    }

    public static void main(String[] arge) {
        String hashAlgorithmName = "md5";
        String password = "123456";
        int hashIterations = 2;
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash(hashAlgorithmName, password, ByteSource.Util.bytes(salt), hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(encodedPassword);
        System.out.println(salt);
    }
}
