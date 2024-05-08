package com.example.project.mapper;

import com.example.project.dto.CompanyDTO;
import com.example.project.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO companyToCompanyDTO(Company company);
    Company companyDTOToCompany(CompanyDTO companyDTO);
    List<CompanyDTO> companyListToCompanyDTOList(List<Company> companyList);
}
