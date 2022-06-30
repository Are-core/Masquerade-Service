package com.masquerade.service.characterSheet.skill;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import com.masquerade.repository.characterSheet.skill.SkillSpecializationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SkillSpecializationServiceTest {

    @Mock
    SkillSpecializationRepository skillSpecializationRepository;

    SkillSpecializationService skillSpecializationService;

    @BeforeEach
    public void setUp() {
        skillSpecializationRepository = spy(SkillSpecializationRepository.class);
        setUpMocks();
        skillSpecializationService = new SkillSpecializationService(skillSpecializationRepository);
    }

    private void setUpMocks(){
        List<SkillSpecializationEntity> list = new ArrayList<>();
        SkillSpecializationEntity skillSpe = new SkillSpecializationEntity();
        skillSpe.setId(2L);
        skillSpe.setDescriptionEN("A small description");
        skillSpe.setDescriptionFR("Une petite description");
        list.add(skillSpe);
        skillSpe.setId(1L);
        skillSpe.setDescriptionEN("A small description 2");
        skillSpe.setDescriptionFR("Une petite description 2");
        list.add(skillSpe);
        Mockito.when(skillSpecializationRepository.findAll()).thenReturn(list);
        Mockito.when(skillSpecializationRepository.findById(1L)).thenReturn(Optional.of(skillSpe));
        Mockito.when(skillSpecializationRepository.existsById(1L)).thenReturn(true);
        Mockito.when(skillSpecializationRepository.existsById(5L)).thenReturn(false);
    }

    @Test
    void getSkillSpecializationsOK() {
        List<SkillSpecializationEntity> list = skillSpecializationService.getSkillSpecializations();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertNotNull(list.get(0).getId());
        assertNotNull(list.get(0).getDescriptionEN());
        assertNotNull(list.get(0).getDescriptionFR());
        assertEquals(1L, (long) list.get(0).getId());
        verify(skillSpecializationRepository, times(1)).findAll();
    }

    @Test
    void getSkillSpecializationByIdOK() {
        try {
            SkillSpecializationEntity skillSpe = skillSpecializationService.getSkillSpecialization(1L);
            assertNotNull(skillSpe);
            assertNotNull(skillSpe.getId());
            assertNotNull(skillSpe.getDescriptionEN());
            assertNotNull(skillSpe.getDescriptionFR());
            assertEquals(1L, (long) skillSpe.getId());
            verify(skillSpecializationRepository, times(1)).findById(1L);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getSkillSpecializationByIdWithIncorrectId()  {
        try {
            skillSpecializationService.getSkillSpecialization(8L);
            fail();
        }
        catch (IllegalArgumentException i) {
            verify(skillSpecializationRepository, times(1)).findById(8L);
            assertTrue(true);
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    void getSkillSpecializationByIdMissingParameter() {
        try {
            skillSpecializationService.getSkillSpecialization(null);
            fail();
        } catch (BadRequestException e) {
            verify(skillSpecializationRepository, times(0)).findById(any());
            assertTrue(true);
        }
    }

    @Test
    void createSkillSpecializationOK() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.createSkillSpecialization(JsonMock.getSkillSpecializationJson());
            assertSame(status.getStatusCode(), HttpStatus.CREATED);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void createSkillSpecializationBadBody() {
        try {
            skillSpecializationService.createSkillSpecialization(JsonMock.getBadSkillSpecializationJson());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void createSkillSpecializationMissingBodyParameter() {
        try {
            skillSpecializationService.createSkillSpecialization(null);
            fail();
        } catch (BadRequestException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void createSkillSpecializationEmptyBody() {
        try {
            skillSpecializationService.createSkillSpecialization("[]");
            fail();
        } catch (BadRequestException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void removeSkillSpecializationOK() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.removeSkillSpecialization(1L);
            assertSame(status.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void removeSkillSpecializationMissingId() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.removeSkillSpecialization(null);
            fail();
        } catch (BadRequestException br) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void removeSkillSpecializationBadId() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.removeSkillSpecialization(9L);
            assertSame(status.getStatusCode(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationOk() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.updateSkillSpecialization(JsonMock.getExistingSkillSpecializationJson());
            assertSame(status.getStatusCode(), HttpStatus.OK);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationNullBody() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.updateSkillSpecialization(null);
            assertSame(status.getStatusCode(), HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationEmptyBody() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.updateSkillSpecialization("{}");
            assertSame(status.getStatusCode(), HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationNotExisting() {
        try {
            ResponseEntity<HttpStatus> status = skillSpecializationService.updateSkillSpecialization(JsonMock.getSkillSpecializationJson());
            fail();
        } catch (EntityRequestException ere) {
            assertTrue(true);
        } catch(Exception e) {
            fail();
        }
    }
}