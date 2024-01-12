package com.jumpfish.developer.porjects.monitors.authority.exception;

import com.jumpfish.developer.porjects.monitors.common.result.DefaultResultCode;
import com.jumpfish.developer.porjects.monitors.common.result.Result;
import com.jumpfish.developer.porjects.monitors.common.result.ResultFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Component
public class UserOAuth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        e.printStackTrace();
        Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain(e);
        // 自定义一异常数据直接抛出
        Exception ase = (UserAuthenticationException) this.throwableAnalyzer.getFirstThrowableOfType(UserAuthenticationException.class, causeChain);
        if (null != ase) {
            return ResponseEntity.badRequest().body(ResultFactory.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ase.getMessage()));
        }
        //身份验证相关异常
        ase = (AuthenticationException) this.throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
        if (ase != null) {
            return ResponseEntity.badRequest().body(ResultFactory.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "身份验证异常"));
        }
        //异常链中包含拒绝访问异常
        ase = (AccessDeniedException) this.throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (null != ase) {
            return ResponseEntity.badRequest().body(ResultFactory.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "异常链中包含拒绝访问异常"));
        }
        //异常链中包含Http方法请求异常
        ase = (HttpRequestMethodNotSupportedException) this.throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
        if (null != ase) {
            return ResponseEntity.badRequest().body(statusResult(HttpStatus.METHOD_NOT_ALLOWED));
        }
        ase = (InvalidTokenException) this.throwableAnalyzer.getFirstThrowableOfType(InvalidTokenException.class, causeChain);
        if (null != ase) {
            return ResponseEntity.badRequest().body(ResultFactory.of(DefaultResultCode.TOKEN_INVALID));
        }
        ase = (InvalidGrantException) this.throwableAnalyzer.getFirstThrowableOfType(InvalidGrantException.class, causeChain);
        if (null != ase) {
            return ResponseEntity.badRequest().body(ResultFactory.of(DefaultResultCode.TOKEN_AUTHORIZATION_FAILED));
        }
        return ResponseEntity.badRequest().body(ResultFactory.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    public Result statusResult(HttpStatus httpStatus) {
        return ResultFactory.of(httpStatus.value(), httpStatus.getReasonPhrase());
    }


}

