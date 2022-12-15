package com.example.market2.controller;

import com.example.market2.entity.Product;
import com.example.market2.entity.Purchase;
import com.example.market2.entity.User;
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

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

//    @GetMapping("user")
//    public List<User> findByLastname(@RequestParam("lastname") String lastname) {
//        return userService.findByLastname(lastname);
//    }

    @GetMapping("count")
    public List<User> findUsersByCountOfProducts(@RequestParam("product") String title,
                                                 @RequestParam("value")   Long   value) {
        return userService.findUsersByCountOfProducts(title, value);
    }

    @GetMapping("sum")
    public List<User> findUsersBySumPrice(@RequestParam("min") Double minSum,
                                          @RequestParam("max") Double maxSum) {
        return userService.findUsersBySumPrice(minSum, maxSum);
    }

    @GetMapping("passive")
    public List<User> findPassiveUsers(@RequestParam("value") int value) {
        return userService.findPassiveUsers(value);
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
