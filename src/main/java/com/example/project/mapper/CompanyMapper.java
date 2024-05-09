package com.example.project.mapper;

import com.example.project.dto.CompanyDTO;
import com.example.project.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between Company and CompanyDTO objects.
 */
@Mapper(componentModel = "spring")
public interface CompanyMapper {

    /**
     * Converts a Company object to a CompanyDTO object.
     * @param company The Company object to convert.
     * @return The converted CompanyDTO object.
     */
    CompanyDTO companyToCompanyDTO(Company company);

    /**
     * Converts a CompanyDTO object to a Company object.
     * @param companyDTO The CompanyDTO object to convert.
     * @return The converted Company object.
     */
    Company companyDTOToCompany(CompanyDTO companyDTO);

    /**
     * Converts a list of Company objects to a list of CompanyDTO objects.
     * @param companyList The list of Company objects to convert.
     * @return The converted list of CompanyDTO objects.
     */
    List<CompanyDTO> companyListToCompanyDTOList(List<Company> companyList);
}
