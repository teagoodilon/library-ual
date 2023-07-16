package br.com.compass.pb.libraryual.service;

import br.com.compass.pb.libraryual.domain.dto.PublishingCompanyDTO;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import br.com.compass.pb.libraryual.exception.ResourceNotFoundException;
import br.com.compass.pb.libraryual.repository.PublishingCompanyRepository;
import br.com.compass.pb.libraryual.service.PublishingCompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublishingCompanyServiceTest {

    @Mock
    private PublishingCompanyRepository publishingCompanyRepository;

    @InjectMocks
    private PublishingCompanyService publishingCompanyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        PublishingCompany publishingCompany = createPublishingCompany();
        when(publishingCompanyRepository.findById(id)).thenReturn(Optional.of(publishingCompany));

        PublishingCompanyDTO result = publishingCompanyService.findById(id);

        assertEquals(publishingCompany.getId(), result.getId());
        assertEquals(publishingCompany.getName(), result.getName());

        verify(publishingCompanyRepository, times(1)).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        Long invalidId = 80L;
        when(publishingCompanyRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            publishingCompanyService.findById(invalidId);
        });

        verify(publishingCompanyRepository, times(1)).findById(invalidId);
    }

    @Test
    void testFindAll() {
        List<PublishingCompany> publishingCompanies = new ArrayList<>();
        publishingCompanies.add(createPublishingCompany());
        when(publishingCompanyRepository.findAll()).thenReturn(publishingCompanies);

        List<PublishingCompanyDTO> result = publishingCompanyService.findAll();

        assertEquals(1, result.size());

        verify(publishingCompanyRepository, times(1)).findAll();
    }

    @Test
    void testFindAllWhenEmpty() {
        when(publishingCompanyRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> {
            publishingCompanyService.findAll();
        });

        verify(publishingCompanyRepository, times(1)).findAll();
    }

    @Test
    void testInsert() {
        PublishingCompanyDTO publishingCompanyDTO = createPublishingCompanyDTO();
        PublishingCompany publishingCompany = createPublishingCompany();
        when(publishingCompanyRepository.saveAndFlush(any(PublishingCompany.class))).thenReturn(publishingCompany);

        PublishingCompanyDTO result = publishingCompanyService.insert(publishingCompanyDTO);

        assertEquals(publishingCompany.getId(), result.getId());
        assertEquals(publishingCompany.getName(), result.getName());

        verify(publishingCompanyRepository, times(1)).saveAndFlush(any(PublishingCompany.class));
    }

    @Test
    void testUpdate() {
        PublishingCompanyDTO publishingCompanyDTO = createPublishingCompanyDTO();
        PublishingCompany publishingCompany = createPublishingCompany();
        when(publishingCompanyRepository.findById(publishingCompanyDTO.getId())).thenReturn(Optional.of(publishingCompany));
        when(publishingCompanyRepository.saveAndFlush(any(PublishingCompany.class))).thenReturn(publishingCompany);

        PublishingCompanyDTO result = publishingCompanyService.update(publishingCompanyDTO.getId(), publishingCompanyDTO);

        assertEquals(publishingCompany.getId(), result.getId());
        assertEquals(publishingCompany.getName(), result.getName());

        verify(publishingCompanyRepository, times(1)).findById(publishingCompanyDTO.getId());
        verify(publishingCompanyRepository, times(1)).saveAndFlush(any(PublishingCompany.class));
    }

    @Test
    void testUpdate_InvalidId() {
        Long invalidId = 80L;
        PublishingCompanyDTO publishingCompanyDTO = createPublishingCompanyDTO();
        when(publishingCompanyRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            publishingCompanyService.update(invalidId, publishingCompanyDTO);
        });

        verify(publishingCompanyRepository, times(1)).findById(invalidId);
        verify(publishingCompanyRepository, never()).saveAndFlush(any(PublishingCompany.class));
    }

    @Test
    void testDelete() {
        Long id = 1L;

        PublishingCompany publishingCompany = createPublishingCompany();
        when(publishingCompanyRepository.findById(id)).thenReturn(Optional.of(publishingCompany));

        publishingCompanyService.delete(id);

        verify(publishingCompanyRepository, times(1)).deleteById(id);
    }

    @Test
    void testDelete_InvalidId() {
        Long invalidId = 80L;

        assertThrows(ResourceNotFoundException.class, () -> {
            publishingCompanyService.delete(invalidId);
        });

        verify(publishingCompanyRepository, times(0)).deleteById(invalidId);
    }


    private PublishingCompany createPublishingCompany() {
        PublishingCompany publishingCompany = new PublishingCompany();
        publishingCompany.setId(1L);
        publishingCompany.setName("publishingCompany");

        return publishingCompany;
    }

    private PublishingCompanyDTO createPublishingCompanyDTO() {
        Long id= 1L;
        String name = "publishingCompany";

        return new PublishingCompanyDTO(id, name);
    }
}
