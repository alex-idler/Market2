package com.example.market2.controller;

import com.example.market2.entity.Product;
import com.example.market2.entity.Purchase;
import com.example.market2.entity.User;
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
@RequestMapping("/")
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

    @PostMapping("findByLastname")
    public ResponseEntity<List<User>> findByLastname(@RequestBody String lastname) {
        return ResponseEntity.ok(userService.findByLastname(lastname));
    }

    @PostMapping("getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("findUsersByMinCountOfProduct")
    public ResponseEntity<List<User>> findUsersByMinCountOfProduct(@RequestBody FindUsersByMinCountOfProduct searchFields) {
        return ResponseEntity.ok(userService.findUsersByMinCountOfProduct(searchFields.getProductName(),
                                                                          searchFields.getMinCount()));
    }

    @PostMapping("findUsersByMinMaxSum")
    public ResponseEntity<List<User>> findUsersByMinMaxSum(@RequestBody FindUsersByMinMaxSum searchFields) {
        return ResponseEntity.ok(userService.findUsersBySumPrice(searchFields.getMinSum(),
                                                                 searchFields.getMaxSum()));
    }

    @GetMapping("passive")
    public List<User> findPassiveUsers(@RequestParam("value") int value) {
        return userService.findPassiveUsers(value);
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("products")
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("purchases")
    public List<Purchase> findAllPurchases() {
        return purchaseService.findAll();
    }
}
