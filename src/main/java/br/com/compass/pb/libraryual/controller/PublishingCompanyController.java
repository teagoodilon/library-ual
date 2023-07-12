package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.domain.dto.PublishingCompanyDTO;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import br.com.compass.pb.libraryual.service.PublishingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/publishingCompany")
public class PublishingCompanyController {

    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @GetMapping("/")
    public List<PublishingCompanyDTO> findAll() {
        return publishingCompanyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublishingCompanyDTO> findById(@PathVariable Long id) {
        PublishingCompanyDTO publishingCompanyDTO = publishingCompanyService.findById(id);
            return ResponseEntity.ok(publishingCompanyDTO);
    }

    @PostMapping("/")
    public PublishingCompany insert(@RequestBody PublishingCompany object) {
        return publishingCompanyService.insert(PublishingCompanyDTO.convertToDto(object));
    }

    @PutMapping("/")
    public PublishingCompany update(@RequestBody PublishingCompany object) {
        return publishingCompanyService.update(PublishingCompanyDTO.convertToDto(object));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        publishingCompanyService.delete(id);
        return ResponseEntity.ok().build();
    }
}