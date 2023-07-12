package br.com.compass.pb.libraryual.domain.dto;

import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data

public class PublishingCompanyDTO {
   private Long id;
   private String name;

   public PublishingCompanyDTO(Long id, @NotBlank String name) {
       this.id = id;
       this.name = name;
   }

   public static PublishingCompanyDTO convertToDto(PublishingCompany entity) {
       return new PublishingCompanyDTO(entity.getId(), entity.getName());
   }

   public static PublishingCompany convertToEntity(PublishingCompanyDTO entityDTO) {
       PublishingCompany publishingCompany = new PublishingCompany();
       publishingCompany.setId(entityDTO.getId());
       publishingCompany.setName(entityDTO.getName());
       return publishingCompany;
   }
}
