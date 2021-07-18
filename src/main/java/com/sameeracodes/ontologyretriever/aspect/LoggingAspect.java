package com.sameeracodes.ontologyretriever.aspect;

import static java.time.LocalTime.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The Aspect that is used for logging all inputs and outputs of the application.
 *
 * @author sam
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

  /**
   * Logs execution time on all controller POST methods.
   *
   * @param joinPoint the advised {@link ProceedingJoinPoint} to weave.
   * @param postMapping the post mapping object that has the endpoint info.
   */
  @Around("execution(* com.sameeracodes.ontologyretriever.api.*.*(..)) && @annotation(postMapping)")
  public void logPostCalls(ProceedingJoinPoint joinPoint, PostMapping postMapping)
      throws Throwable {
    final LocalTime start = now();
    final String endPoint = postMapping.value()[0];
    log.info("POST {} {}", endPoint, joinPoint.getArgs());
    joinPoint.proceed();
    final LocalTime end = now();
    log.info("POST {} finished, took {}ms", endPoint, start.until(end, MILLIS));
  }

  /**
   * Logs execution time on all controller GET methods.
   *
   * @param joinPoint the advised {@link ProceedingJoinPoint} to weave.
   * @param getMapping the get mapping object that has the endpoint info.
   * @return the execution result.
   */
  @Around("execution(* com.sameeracodes.ontologyretriever.api.*.*(..)) && @annotation(getMapping)")
  public Object logGetCalls(ProceedingJoinPoint joinPoint, GetMapping getMapping)
      throws Throwable {
    final LocalTime start = now();
    final String endPoint = getMapping.value()[0];
    log.info("GET {}", endPoint);
    Object result = joinPoint.proceed();
    final LocalTime end = now();
    log.info("GET {} {} finished, took {}ms", endPoint, result, start.until(end, MILLIS));
    return result;
  }
}
