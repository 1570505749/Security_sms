package com.zy.report.security.jwt;

import com.zy.report.commons.enums.ResultEnum;
import com.zy.report.modules.user.entity.SysUser;
import com.zy.report.security.handles.UserDefinedException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

/**
 * @author: nile
 * @create: 2020-03-22 22:19
 * Jwt参数配置类
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    /**
     *自定义盐，整个jwt中的重要部分
     */
    private static final String JWT_SECRET = "zyReportSecret";

    /**
     * 根据Authentication信息生成token
     * @param authentication authentication of spring security
     */
    public String generateJwtToken(Boolean isRemember,Authentication authentication) {

        SysUser userPrincipal = new SysUser();
        userPrincipal.setUserName(authentication.getName());

        int jwtExpiration = isRemember?1000*60*60*24*7:1000*60*60*2;

        return Jwts.builder()
		                .setSubject("Peng44Web")
                        .setIssuer("hniit.zy")
                        .setAudience(userPrincipal.getUserName())
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
		                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
		                .compact();
    }

    /**
     * 验证当前token是否有效
     * @param authToken json web token
     */
    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser()
                        .setSigningKey(JWT_SECRET)
                        .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("无效的JWT签名 -> 详细信息:{}"+"\n验证Token: "+authToken, e.getMessage());
            throw new UserDefinedException(ResultEnum.NOT_AUTHORITY);
        } catch (MalformedJwtException e) {
            logger.error("无效的JWT令牌 -> 详细信息:{}"+"\n验证Token: "+authToken, e.getMessage());
            throw new UserDefinedException(ResultEnum.NOT_AUTHORITY);
        } catch (ExpiredJwtException e) {
            logger.error("JWT令牌已过期 -> 详细信息:{}"+"\n验证Token: "+authToken, e.getMessage());
            throw new UserDefinedException(ResultEnum.UNAUTHORIZED);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message:{}"+"\n验证Token: "+authToken, e.getMessage());
            throw new UserDefinedException(ResultEnum.NOT_AUTHORITY);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message:{}"+"\n验证Token: "+authToken, e.getMessage());
            throw new UserDefinedException(ResultEnum.NOT_AUTHORITY);
        }
    }

    /**
     * 根据token返回用户名
     * @param token json web token
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(JWT_SECRET)
			                .parseClaimsJws(token)
			                .getBody().getAudience();
    }
}