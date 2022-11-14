package com.academy.billing.repository;

import com.academy.billing.model.Billing;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends PagingAndSortingRepository<Billing, Long> {
}
