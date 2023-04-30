package com.login.micrologin.Repository;

import com.login.micrologin.Entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offers, Long> {
    @Query(value = "SELECT * FROM offers WHERE ofe_company_id= :CompanyId", nativeQuery = true)
    List<Offers> findAllByCompanyId(Long CompanyId);
}
