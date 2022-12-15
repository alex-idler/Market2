package com.example.market2.repository;

import com.example.market2.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByLastnameContainsIgnoreCase(String lastname);

    @Query( "select new com.example.market2.entity.User(u.id, u.firstname, u.lastname) " +
            "from User u " +
            "   join Purchase pur on u = pur.user " +
            "   join Product pr   on pr = pur.product " +
            "where lower(pr.title) like lower(:productName) " +
            "group by u " +
            "having count(pur) > :minCount" )
    List<User> findUsersByMinCountOfProduct(String productName, Long minCount);

    @Query( "select new com.example.market2.entity.User(u.id, u.firstname, u.lastname) " +
            "from User u " +
            "   join Purchase pur on u = pur.user " +
            "   join Product pr   on pr = pur.product " +
            "group by u " +
            "having sum(pr.price) between :minSum and :maxSum" )
    List<User> findUsersBySumPrice(Double minSum, Double maxSum);

    @Query( "select new com.example.market2.entity.User(u.id, u.firstname, u.lastname) " +
            "from User u " +
            "   join Purchase pur on u = pur.user " +
            "   join Product pr   on pr = pur.product " +
            "group by u " +
            "order by count(pur)" )
    Page<User> findPassiveUsers(Pageable pageable);

    @Query( "select concat(u.firstname, ' ', u.lastname) as username, " +
            "       pr.title as productName, " +
            "       sum(pr.price) as expenses " +
            "from User u " +
            "   join Purchase pur on u = pur.user " +
            "   join Product pr   on pr = pur.product " +
            "where date(pur.date) between :dateFrom and :dateTo " +
            "group by u.id, productName" )
    List<UserStatJSON> usersByDate(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    // нужный формат JSON (согласно ТЗ) для вывода покупателя
    interface UserStatJSON {
        @JsonIgnore
        String getUsername();
        String getProductName();
        Long getExpenses();
    }
}
