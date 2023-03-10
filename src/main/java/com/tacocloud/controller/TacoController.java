package com.tacocloud.controller;

import com.tacocloud.entity.Taco;
import com.tacocloud.repository.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/tacos",
produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
    private final TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        Pageable pageable = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageable).getContent();
    }
}
