package com.example.market2.json.search;

import com.example.market2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResult {

    private Object criteria;
    private List<User> results = new ArrayList<>();

}
