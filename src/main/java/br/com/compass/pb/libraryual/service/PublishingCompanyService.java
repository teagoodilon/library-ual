package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.PublishingCompanyDTO;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
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
        return null;
    }

    public PublishingCompanyDTO insert(PublishingCompanyDTO publishingCompanyDTO) {
        PublishingCompany publishingCompany = publishingCompanyDTO.toEntity();
        publishingCompany.setCreatedAt(LocalDateTime.now());
        PublishingCompany createdPublishingCompany = publishingCompanyRepository.saveAndFlush(publishingCompany);
        return new PublishingCompanyDTO(createdPublishingCompany);
    }

    public PublishingCompanyDTO update(Long id, PublishingCompanyDTO publishingCompanyDTO) {
        Optional<PublishingCompany> optionalPublishingCompany = publishingCompanyRepository.findById(id);
        if(optionalPublishingCompany.isPresent()){
            PublishingCompany publishingCompany = optionalPublishingCompany.get();
            publishingCompany.setName(publishingCompanyDTO.getName());
            publishingCompany.setUpdatedAt(LocalDateTime.now());
            PublishingCompany updatedPublishingCompany = publishingCompanyRepository.saveAndFlush(publishingCompany);
            return new PublishingCompanyDTO(updatedPublishingCompany);
        }
        return null;
    }

    public void delete(Long id) {
        publishingCompanyRepository.deleteById(id);
    }
}