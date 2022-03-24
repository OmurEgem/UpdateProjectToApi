package com.example.updateprojecttoapi.services.impl;

import com.example.updateprojecttoapi.exceptions.BadRequestException;
import com.example.updateprojecttoapi.exceptions.NotFoundException;
import com.example.updateprojecttoapi.models.Company;
import com.example.updateprojecttoapi.models.response.Response;
import com.example.updateprojecttoapi.repositories.CompanyRepository;
import com.example.updateprojecttoapi.services.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public Response addNewCompany(Company company) {
        String email = company.getCompanyName();

        checkCompanyName(email);

        Company savedCompany
                = companyRepository.save(company);

        log.info("Company with name = {} has sucessfully saved to database", savedCompany.getCompanyName());

        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Company with name = %s successfully registered",
                        savedCompany.getCompanyName()))
                .build();
    }

    private void checkCompanyName(String name) {
        boolean exists = companyRepository.existsByName(name);

        if (exists) {
            log.warn("company with name = {} already exists", name);
            throw new BadRequestException(
                    "company with name = " + name + " already exists"
            );
        }
    }

    @Override
    public List<Company> findAllCompanies() {
        List<Company> allCompany = companyRepository.findAll();
        log.info("founded {} companies", allCompany.size());
        return allCompany;
    }

    @Override
    public Company findById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> {
                    log.error("company with id = {} does not exists", companyId);
                    throw new NotFoundException(
                            String.format("company with id = %s does not exists", companyId)
                    );
                });

        log.info("founded company with id = {}", companyId);

        return company;
    }

    @Override
    public Response deleteById(Long companyId) {
        boolean exists = companyRepository.existsById(companyId);

        if (!exists) {
            log.error("Company with id = {} does not exists, you can't delete it", companyId);
            throw new BadRequestException(
                    String.format("Company with id = %s does not exists, you can't delete it", companyId)
            );
        }

        companyRepository.deleteById(companyId);

        log.info("Company with id = {} has successfully deleted", companyId);

        String message = String.format("Company with id = %s has successfully deleted", companyId);

        return Response.builder()
                .httpStatus(HttpStatus.OK)
                .message(message)
                .build();
    }

    @Override
    @Transactional
    public Response updateById(Long companyId, Company newCompany) {
        Company company = findById(companyId);

        String currentCompanyName = company.getCompanyName();
        String newCompanyName = newCompany.getCompanyName();

        if (!Objects.equals(currentCompanyName, newCompanyName)) {
            company.setCompanyName(newCompanyName);
            log.info("Company with id = {} changed company name from {} to {}",
                    companyId, currentCompanyName, newCompanyName);
        }

        String currentLocatedCountry = company.getLocatedCountry();
        String newLocatedCountry = newCompany.getLocatedCountry();

        if (currentLocatedCountry != newLocatedCountry) {
            company.setLocatedCountry(newLocatedCountry);
            log.info("Company with id = {} changed Located from {} to {}",
                    companyId, currentLocatedCountry, newLocatedCountry);
        }

        String message = String.format("Company with company id = %s has successfully updated", companyId);

        return Response.builder()
                .httpStatus(HttpStatus.RESET_CONTENT)
                .message(message)
                .build();
    }
}

