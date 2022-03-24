package com.example.updateprojecttoapi.apis;


import com.example.updateprojecttoapi.exceptions.BadRequestException;
import com.example.updateprojecttoapi.exceptions.NotFoundException;
import com.example.updateprojecttoapi.models.Company;
import com.example.updateprojecttoapi.models.response.Response;
import com.example.updateprojecttoapi.services.impl.CompanyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/companies")
public class CompanyApi {
    private final CompanyServiceImpl service;

//    @PostMapping
//    private Response addNewCompany(@RequestBody Company company) {
//        return service.addNewCompany(company);
//    }
@PostMapping("/newcompany")
public Response addNewCompany(@RequestBody Company company) {
    return service.addNewCompany(company);
}

    @GetMapping
    public List<Company> findAllStudents() {
        return service.findAllCompanies();
    }

    @GetMapping("/get/{companyId}")
    public Company findById(@PathVariable Long companyId) {
        return service.findById(companyId);
    }


    @DeleteMapping("/delete/{companyId}")
    public Response deleteById(@PathVariable Long companyId) {
        return service.deleteById(companyId);
    }

    @PutMapping("/update/{companyId}")
    public Response updateById(@PathVariable Long companyId,
                               @RequestBody Company company) {
        return service.updateById(companyId, company);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNotFoundException(NotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
        return Response.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }
}
