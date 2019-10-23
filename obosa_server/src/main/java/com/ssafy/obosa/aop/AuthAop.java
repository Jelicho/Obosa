package com.ssafy.obosa.aop;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.service.common.JwtService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Aspect
@Component
public class AuthAop
{
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final HttpServletRequest httpServletRequest;

    public  AuthAop(UserRepository userRepository, JwtService jwtService, HttpServletRequest httpServletRequest)
    {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.httpServletRequest = httpServletRequest;
    }

    @Pointcut("@annotation(com.ssafy.obosa.annotation.Auth)")
    public void auth(){}

    @Pointcut("execution(* com..*Service.create*(..))")
    public void create(){}

    @Pointcut("execution(* com..*Service.update*(..))")
    public void update(){}

    @Pointcut("execution(* com..*Service.delete*(..))")
    public void delete(){}

    @Around("auth() || create() || update() || delete()")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable
    {
        if(validToken() == false)
        {
            return DefaultRes.builder()
                    .status(401)
                    .message("인증 실패")
                    .build();
        }
        return pjp.proceed(pjp.getArgs());
    }

    private boolean validToken()
    {
        final String jwt = httpServletRequest.getHeader("Authorization");

        if(!Optional.ofNullable(jwt).isPresent())
        {
            return false;
        }

        final String email = jwtService.decode(jwt);

        if(!Optional.ofNullable(email).isPresent())
        {
            return false;
        }

        if(!userRepository.findByEmail(email).isPresent())
        {
            return false;
        }

        return true;
    }
}
