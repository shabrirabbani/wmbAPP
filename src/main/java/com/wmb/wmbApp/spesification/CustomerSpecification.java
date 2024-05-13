package com.wmb.wmbApp.spesification;

import com.wmb.wmbApp.dto.request.SearchCustomerRequest;
import com.wmb.wmbApp.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


import java.util.ArrayList;
import java.util.List;


public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null){
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (request.getPhone() != null){
                Predicate mobilePhoneNoPredicate = criteriaBuilder.equal(root.get("mobilePhone"), request.getPhone());
                predicates.add(mobilePhoneNoPredicate);
            }

            if (request.getIsMember() != null){
                Predicate statusPredicate = criteriaBuilder.equal(root.get("isMember"), request.getIsMember());
                predicates.add(statusPredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
