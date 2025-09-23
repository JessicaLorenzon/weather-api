package com.lorenzon.weather_api.config;

import com.lorenzon.weather_api.exception.TooManyRequestsException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        10,
                        Refill.intervally(10, Duration.ofMinutes(1))
                ))
                .build();
    }

    private Bucket resolveBucket(String key) {
        return buckets.computeIfAbsent(key, k -> createNewBucket());
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {

        String key = request.getRemoteAddr() + ":" + request.getRequestURI();
        Bucket bucket = resolveBucket(key);

        if (bucket.tryConsume(1)) {
            return true;
        } else {
            throw new TooManyRequestsException();
        }
    }
}
