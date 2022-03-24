package com.example.updateprojecttoapi.repositories;


import com.example.updateprojecttoapi.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select s from Company s where s.companyName = :compName")
    Optional<Company> findByName(@Param("compName")String companyName);
    @Query("select case when count(s) > 0 then true else false end " +
                  "from Company s where s.companyName = ?1")
    boolean existsByName(String name);
}