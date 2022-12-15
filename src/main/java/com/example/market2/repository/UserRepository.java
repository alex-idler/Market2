package com.example.market2.repository;

import com.example.market2.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByLastnameContainsIgnoreCase(String lastname);

    @Query( "select new com.example.market2.entity.User(u.id, u.firstname, u.lastname) " +
            "from User u join Purchase pur on u = pur.user join Product pr on pr = pur.product " +
            "where lower(pr.title) like lower(:title) group by u having count(pur) > :value")
    List<User> findUsersByCountOfProducts(String title, Long value);

    @Query( "select new com.example.market2.entity.User(u.id, u.firstname, u.lastname) " +
            "from User u join Purchase pur on u = pur.user join Product pr on pr = pur.product " +
            "group by u having sum(pr.price) between :minSum and :maxSum")
    List<User> findUsersBySumPrice(Double minSum, Double maxSum);

    @Query( "select new com.example.market2.entity.User(u.id, u.firstname, u.lastname) " +
            "from User u join Purchase pur on u = pur.user join Product pr on pr = pur.product " +
            "group by u order by count(pur)")
    Page<User> findPassiveUsers(Pageable pageable);
}
