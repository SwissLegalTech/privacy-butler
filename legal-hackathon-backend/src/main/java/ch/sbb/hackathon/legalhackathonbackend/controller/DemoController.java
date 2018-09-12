package ch.sbb.hackathon.legalhackathonbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.language.v1beta2.AnalyzeEntitiesResponse;

import ch.sbb.hackathon.legalhackathonbackend.mapper.EntityResponseMapper;
import ch.sbb.hackathon.legalhackathonbackend.model.EntityResponse;
import ch.sbb.hackathon.legalhackathonbackend.service.DemoService;

@RestController
@CrossOrigin
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/demo")
    public ResponseEntity<List<EntityResponse>> greeting(
            @RequestParam(value = "url") String url,
            @RequestParam(value = "geotracking") Boolean geotracking) {
        AnalyzeEntitiesResponse analyze = demoService.analyze(url, geotracking);
        return new ResponseEntity<>(EntityResponseMapper.map(analyze), HttpStatus.OK);
    }
}
