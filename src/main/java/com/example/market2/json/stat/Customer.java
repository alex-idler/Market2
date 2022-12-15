package com.example.market2.json.stat;

import com.example.market2.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String name;
    private List<UserRepository.UserStatJSON> purchases;
    private Long totalExpenses;
}
