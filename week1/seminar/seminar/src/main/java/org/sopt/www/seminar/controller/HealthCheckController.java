package org.sopt.www.seminar.controller;

import org.sopt.www.seminar.dto.HealthCheckResponse;
import org.sopt.www.seminar.dto.MyResponse;
import org.sopt.www.seminar.sample.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
    @GetMapping("/v1")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        return response;
    }

    @GetMapping("/v2")
    public MyResponse healthCheckV2() {
        return new MyResponse(200, "ok", true);
    }
    /* ResponseEntity 를 활용한 기존 코드
    public ResponseEntity<String> healthCheckV2() {
        return ResponseEntity.ok("OK");
    }
     */

    @GetMapping("/v3")
    public String healthCheckV3() {
        Person person = new Person("최", "윤한");
        Person person2 = Person.builder()
                .lastName("최")
                .firstName("윤한")
                .build();
        return "OK";
    }

    @GetMapping("/v4")
    public MyResponse healthCheckv4() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        return new MyResponse(200, response.get("status"), true);
    }
    /* ResponseEntity 를 활용한 기존 코드
    public ResponseEntity<Map<String, String>> healthCheckV4() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        return ResponseEntity.ok(response);
    }
     */

    @GetMapping("/v5")
    public MyResponse healthCheckV5() {
        HealthCheckResponse response = new HealthCheckResponse(); // "status" : "ok"
        return new MyResponse(200, response.getStatus(), true);
    }
    /* ResponseEntity 를 활용한 기존 코드
    public ResponseEntity<HealthCheckResponse> healthCheckV5() {
        return ResponseEntity.ok(new HealthCheckResponse());
    }
     */
}
