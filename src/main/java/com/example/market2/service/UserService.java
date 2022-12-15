package com.example.market2.service;

import com.example.market2.entity.User;
import com.example.market2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findByLastname(String lastname) {
        return userRepository.findByLastnameContainsIgnoreCase(lastname);
    }

    public List<User> findUsersByMinCountOfProduct(String productName, Long minCount) {
        // в return пришлось дописывать проценты для регулярки в наименование, в запросе не получалось
        return userRepository.findUsersByMinCountOfProduct("%" + productName + "%", minCount);
    }

    public List<User> findUsersBySumPrice(Double minSum, Double maxSum) {
        return userRepository.findUsersBySumPrice(minSum, maxSum);
    }

    public List<User> findPassiveUsers(int value) {
        Page<User> page = userRepository.findPassiveUsers(PageRequest.of(0, value));
        return page.get().toList();
    }
}
