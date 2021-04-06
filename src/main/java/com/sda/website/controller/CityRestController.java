package com.sda.website.controller;


import com.sda.website.entity.CityEntity;
import com.sda.website.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CityRestController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public List<CityEntity> getAllCities() {
        return cityRepository.findAll();
    }

    @PostMapping("/cities/add")
    public ResponseEntity<String> addNewCity(@Valid @RequestBody CityEntity cityEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors().stream().map(element -> element.getDefaultMessage()).collect(Collectors.joining("; "));
            return ResponseEntity.status(412).body(errors);
        } else {
            cityRepository.save(cityEntity);
            return ResponseEntity.ok().body("success");
        }

    }
}
