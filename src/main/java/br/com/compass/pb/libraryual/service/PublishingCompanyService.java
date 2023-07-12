package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.PublishingCompanyDTO;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import br.com.compass.pb.libraryual.exception.ResourceNotFoundException;
import br.com.compass.pb.libraryual.repository.PublishingCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublishingCompanyService {

    @Autowired
    private PublishingCompanyRepository publishingCompanyRepository;

    public List<PublishingCompanyDTO> findAll() {
        List<PublishingCompany> publishingCompanies = publishingCompanyRepository.findAll();
        return publishingCompanies.stream()
                .map(PublishingCompanyDTO::convertToDto)
                .collect(Collectors.toList());
    }

    public PublishingCompanyDTO findById(Long id) {
        Optional<PublishingCompany> publishingCompanyOptional = publishingCompanyRepository.findById(id);
        if (publishingCompanyOptional.isPresent()) {
            PublishingCompany publishingCompany = publishingCompanyOptional.get();
            return PublishingCompanyDTO.convertToDto(publishingCompany);
        }
        throw new ResourceNotFoundException("Publishing Company", "Publishing Company not found with ID: " + id);
    }

    public PublishingCompany insert(PublishingCompanyDTO publishingCompanyDTO) {
        PublishingCompany publishingCompany = PublishingCompanyDTO.convertToEntity(publishingCompanyDTO);
        publishingCompany.setCreatedAt(LocalDateTime.now());
        return publishingCompanyRepository.saveAndFlush(publishingCompany);
    }

    public PublishingCompany update(PublishingCompanyDTO publishingCompanyDTO) {
        PublishingCompany publishingCompany = PublishingCompanyDTO.convertToEntity(publishingCompanyDTO);
        publishingCompany.setUpdateAt(LocalDateTime.now());
        return publishingCompanyRepository.saveAndFlush(publishingCompany);
    }

    public void delete(Long id) {
        publishingCompanyRepository.deleteById(id);
    }
}