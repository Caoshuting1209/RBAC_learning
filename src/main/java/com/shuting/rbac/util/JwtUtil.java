package com.shuting.rbac.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shuting.rbac.common.CodeEnum;
import com.shuting.rbac.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.apache.bcel.classfile.Code;

import java.security.SignatureException;
import java.util.Date;

@Slf4j
public class JwtUtil {
    private static final String SECRET = "shuting.com";

    //生成token
    public static <T> String generateToken(T t) {
        Date expiration = DateUtils.addMonths(new Date(), 1);
        JWTCreator.Builder builder = JWT.create()
                .withClaim(t.getClass().getSimpleName(), JSONUtil.toJsonStr(t));
        return builder.withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(SECRET));
    }

    //校验token是否合法
    public static DecodedJWT verifyToken(String token) {
        String errMsg;
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token);
        }catch (AlgorithmMismatchException  e) {
            errMsg = "Algorithm Mismatch";
        }catch(InvalidClaimException e) {
            errMsg = "Invalid Claim";
        }catch(SignatureVerificationException e) {
            errMsg = "Signature Verification failed";
        }catch(TokenExpiredException e) {
            errMsg = "Token Expired";
        }catch(Exception e) {
            errMsg = "Token verify failed";
        }
        log.error("Token verify failed, token:{}, reason:{}", token, errMsg);
        throw new GlobalException(CodeEnum.AUTH_ERROR);
    }

    //从token中获取信息
    public static <T> T parseToken(DecodedJWT decodedJWT, Class<T> clazz) {
        Claim claim = decodedJWT.getClaim(clazz.getSimpleName());
        if(claim == null) {
            return null;
        }
        return JSONUtil.toBean(claim.asString(), clazz);
    }

}
