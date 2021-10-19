package ru.haazad.onlinestorage.webapp.utils;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class ServiceProfilerUtil {
    private Map<String, Long> serviceList;

    public void addService(String serviceName, Long duration) {
        if (serviceList.containsKey(serviceName)) {
            serviceList.replace(serviceName, serviceList.get(serviceName) + duration);
            return;
        }
        serviceList.put(serviceName, duration);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Long> e: serviceList.entrySet()) {
            sb.append(e.getKey())
                    .append(" duration is: ")
                    .append(e.getValue())
                    .append(" ms")
                    .append(System.lineSeparator());
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
