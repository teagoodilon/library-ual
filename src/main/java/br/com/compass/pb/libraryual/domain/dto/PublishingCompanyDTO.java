package br.com.compass.pb.libraryual.domain.dto;

import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data

public class PublishingCompanyDTO {
   private Long id;
   @NotBlank
   private String name;

   public PublishingCompanyDTO(Long id, @NotBlank String name) {
       this.id = id;
       this.name = name;
   }

    public PublishingCompanyDTO(PublishingCompany entity) {
        BeanUtils.copyProperties(entity, this);
    }

   public static PublishingCompanyDTO convertToDto(PublishingCompany entity) {
       return new PublishingCompanyDTO(entity.getId(), entity.getName());
   }

   public PublishingCompany toEntity() {
       PublishingCompany entity = new PublishingCompany();
       BeanUtils.copyProperties(this, entity);
       return entity;
   }
}
