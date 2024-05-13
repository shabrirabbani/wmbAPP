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

            if (request.getMenuName() != null){
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("menuName")), "%s" + request.getMenuName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (request.getPrice() != null){
                Predicate pricePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), request.getPrice());
                predicates.add(pricePredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
