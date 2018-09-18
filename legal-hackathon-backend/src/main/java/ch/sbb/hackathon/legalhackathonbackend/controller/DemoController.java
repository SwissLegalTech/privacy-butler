package ch.sbb.hackathon.legalhackathonbackend.controller;

import ch.sbb.hackathon.legalhackathonbackend.model.ResponseDto;
import ch.sbb.hackathon.legalhackathonbackend.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DemoController {

    @Autowired
    private ApiService demoService;

    @GetMapping("/demo")
    public ResponseEntity greeting(
            @RequestParam(value = "url") String url) {
        ResponseDto response = demoService.analyze(url);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
