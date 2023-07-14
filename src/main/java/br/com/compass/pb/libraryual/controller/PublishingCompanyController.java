package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.domain.dto.PublishingCompanyDTO;
import br.com.compass.pb.libraryual.service.PublishingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (publishingCompanyDTO  != null) {
            return ResponseEntity.ok(publishingCompanyDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public PublishingCompanyDTO insert(@Valid @RequestBody PublishingCompanyDTO publishingCompanyDTO) {
        return publishingCompanyService.insert(publishingCompanyDTO);
    }

    @PutMapping("/{id}")
    public PublishingCompanyDTO update(@PathVariable ("id") Long id, @Valid @RequestBody PublishingCompanyDTO object) {
        return publishingCompanyService.update(id, object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id) {
        publishingCompanyService.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}