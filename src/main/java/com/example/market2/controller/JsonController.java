package com.example.market2.controller;

import com.example.market2.json.search.SearchResult;
import com.example.market2.json.stat.Stat;
import com.example.market2.search.FindUserByDates;
import com.example.market2.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/json")
public class JsonController {
    private final JsonService jsonService; // сервис для формирования JSON'ов

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("searchWithJson")
    public ResponseEntity<SearchResult> searchWithJson(@RequestBody Object obj) {
        // приведение объекта входяшего JSON в его фактический формат
        LinkedHashMap<String, List<Object>> map = (LinkedHashMap<String, List<Object>>) obj;
        return ResponseEntity.ok(jsonService.searchJson(map));
    }

    // Собирает статистику в итоговый JSON - по всем покупателям и датам
    @PostMapping("buyerStatJson")
    public ResponseEntity<Stat> stat(@RequestBody FindUserByDates dates) {
        return ResponseEntity.ok(jsonService.statJson(dates.getDateFrom(), dates.getDateTo()));
    }
}
