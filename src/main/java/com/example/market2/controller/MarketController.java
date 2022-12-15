package com.example.market2.controller;

import com.example.market2.entity.Product;
import com.example.market2.entity.Purchase;
import com.example.market2.entity.User;
import com.example.market2.repository.UserRepository;
import com.example.market2.search.FindUserByDates;
import com.example.market2.search.FindUsersByMinCountOfProduct;
import com.example.market2.search.FindUsersByMinMaxSum;
import com.example.market2.service.ProductService;
import com.example.market2.service.PurchaseService;
import com.example.market2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer")
public class MarketController {

    private final UserService userService;
    private final ProductService productService;
    private final PurchaseService purchaseService;

    @Autowired
    public MarketController(UserService userService, ProductService productService, PurchaseService purchaseService) {
        this.userService = userService;
        this.productService = productService;
        this.purchaseService = purchaseService;
    }

    @PostMapping("findByName")
    public ResponseEntity<List<User>> findByLastname(@RequestBody String lastname) {
        return ResponseEntity.ok(userService.findByLastname(lastname));
    }

    @PostMapping("getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("findByProduct")
    public ResponseEntity<List<User>> findUsersByMinCountOfProduct(@RequestBody FindUsersByMinCountOfProduct searchFields) {
        return ResponseEntity.ok(userService.findUsersByMinCountOfProduct(searchFields.getProductName(),
                                                                          searchFields.getMinPurchases()));
    }

    @PostMapping("findMinMax")
    public ResponseEntity<List<User>> findUsersByMinMaxSum(@RequestBody FindUsersByMinMaxSum searchFields) {
        return ResponseEntity.ok(userService.findUsersBySumPrice(searchFields.getMinSum(),
                                                                 searchFields.getMaxSum()));
    }

    @PostMapping("findBad")
    public ResponseEntity<List<User>> findPassiveUsers(@RequestBody int value) {
        return ResponseEntity.ok(userService.findPassiveUsers(value));
    }

    @PostMapping("/buyerStat")
    public ResponseEntity<List<UserRepository.UserStatJSON>> usersByDate(@RequestBody FindUserByDates userByDates) {
        return ResponseEntity.ok(userService.usersByDate(userByDates.getDateFrom(), userByDates.getDateTo()));
    }

}
