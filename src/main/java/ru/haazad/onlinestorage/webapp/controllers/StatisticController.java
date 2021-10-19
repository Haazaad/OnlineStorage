package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.haazad.onlinestorage.webapp.exceptions.ApplicationError;
import ru.haazad.onlinestorage.webapp.utils.ServiceProfilerUtil;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatisticController {
    private final ServiceProfilerUtil serviceProfilerUtil;

    @GetMapping
    public ResponseEntity<?> getStatistic(Principal principal) {
        if (principal != null) {
            return ResponseEntity.ok(serviceProfilerUtil.toString());
        }
        return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED, "Not authorized"), HttpStatus.UNAUTHORIZED);

    }
}
