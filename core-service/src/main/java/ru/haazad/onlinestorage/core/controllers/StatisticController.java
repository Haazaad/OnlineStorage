package ru.haazad.onlinestorage.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.api.exceptions.ApplicationError;
import ru.haazad.onlinestorage.core.utils.ServiceProfilerUtil;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatisticController {
    private final ServiceProfilerUtil serviceProfilerUtil;

    @GetMapping
    public ResponseEntity<?> getStatistic(@RequestHeader String username) {
        if (username != null) {
            return ResponseEntity.ok(serviceProfilerUtil.toString());
        }
        return new ResponseEntity<>(new ApplicationError(HttpStatus.UNAUTHORIZED.toString(), "Not authorized"), HttpStatus.UNAUTHORIZED);

    }
}
