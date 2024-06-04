package com.wmb.wmbApp.spesification;

import com.wmb.wmbApp.dto.request.SearchMenuRequest;
import com.wmb.wmbApp.entity.Menu;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MenuSpecification {
    public static Specification<Menu> getSpecification(SearchMenuRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }

            if (request.getPrice() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), request.getPrice()));
            }

            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
