package com.example.updateprojecttoapi.services;

import com.example.updateprojecttoapi.models.Company;
import com.example.updateprojecttoapi.models.response.Response;

import java.util.List;

public interface CompanyService {
    Response addNewCompany(Company company);

    List<Company> findAllCompanies();

    Company findById(Long companyId);

    Response deleteById(Long companyId);

    Response updateById(Long studentId, Company newCompany);
}
