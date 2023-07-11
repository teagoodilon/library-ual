package br.com.compass.pb.libraryual.dto;

   import br.com.compass.pb.libraryual.model.PublishingCompany;
   import jakarta.validation.constraints.NotBlank;
   import lombok.Data;
   @Data

   public class PublishingCompanyDTO {
       private Long id;
       private String name;

       public PublishingCompanyDTO(){
       }
       public PublishingCompanyDTO(Long id, @NotBlank String name) {
           this.id = id;
           this.name = name;
       }
       public PublishingCompanyDTO(PublishingCompany entity) {
           id = entity.getId();
           name = entity.getName();
       }

       public static PublishingCompanyDTO convertToDto(PublishingCompany publishingcompany) {
           return new PublishingCompanyDTO(publishingcompany.getId(), publishingcompany.getName());
       }

       public static PublishingCompany convertToEntity(PublishingCompanyDTO publishingcompanyDTO) {
           PublishingCompany publishingCompany= new PublishingCompany();
           publishingCompany.setId(publishingcompanyDTO.getId());
           publishingCompany.setName(publishingcompanyDTO.getName());
           return publishingCompany;
       }
}
