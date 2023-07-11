package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.dto.PublishingCompanyDTO;
import br.com.compass.pb.libraryual.model.PublishingCompany;
import br.com.compass.pb.libraryual.service.PublishingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/publishingCompany")
public class PublishingCompanyController {

    private final PublishingCompanyService publishingCompanyService;

    @Autowired
    public PublishingCompanyController(PublishingCompanyService publishingCompanyService) {
        this.publishingCompanyService = publishingCompanyService;
    }

    @GetMapping("/")
    public List<PublishingCompanyDTO> findAll() {
        return publishingCompanyService.findAll();
    }

    @PostMapping("/")
    public PublishingCompany insert(@RequestBody PublishingCompanyDTO publishingCompanyDTO) {
        return publishingCompanyService.insert(publishingCompanyDTO);
    }

    @PutMapping("/")
    public PublishingCompany update(@RequestBody PublishingCompanyDTO publishingCompanyDTO) {
        return publishingCompanyService.update(publishingCompanyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        publishingCompanyService.delete(id);
        return ResponseEntity.ok().build();
    }
}