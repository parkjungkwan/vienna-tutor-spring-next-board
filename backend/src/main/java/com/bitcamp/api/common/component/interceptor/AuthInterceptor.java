package com.bitcamp.api.common.component.interceptor;

import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.api.user.model.User;
import com.bitcamp.api.common.component.security.JwtProvider;
import com.bitcamp.api.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final UserRepository repository;

    @Override
    public boolean preHandle(HttpServletRequest request, 
    HttpServletResponse response, 
    Object handler)
            throws Exception {

        String token = jwtProvider.extractTokenFromHeader(request);
        
        log.info("2- 인터셉터 토큰 로그 Bearer 포함 : {}", token);
        // if (ObjectUtils.isEmpty(token)) {
        //     response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        //     return false;
        // }
        String payload = jwtProvider.getPayload(token);
        int index = payload.lastIndexOf("id\":")+4;
        String test = payload.substring(index, payload.indexOf("}", index));
        log.info("3- payload test : {}", test);
        Long id = Long.parseLong(test);
       
        log.info("인터셉터 토큰 ID 로그 : {}", id);
        Optional<User> user = repository.findById(id);
        log.info("마지막 결론 : {}", user.toString());
        if (ObjectUtils.isEmpty(user)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
