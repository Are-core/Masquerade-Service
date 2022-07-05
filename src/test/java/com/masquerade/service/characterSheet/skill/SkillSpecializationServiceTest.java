package com.masquerade.service.characterSheet.skill;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import com.masquerade.repository.characterSheet.skill.SkillSpecializationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

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
        skillSpe.setId(null);
        Mockito.when(skillSpecializationRepository.save(skillSpe)).thenReturn(skillSpe);
    }

    @Test
    void getSkillSpecializationsOK() {
        ResponseDTO response = skillSpecializationService.getSkillSpecializations();
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertEquals(2, listValue.size());
            if (listValue.get(0) instanceof SkillSpecializationEntity) {
                SkillSpecializationEntity entity = (SkillSpecializationEntity) listValue.get(0);
                assertNotNull(entity.getDescriptionEN());
                assertNotNull(entity.getDescriptionFR());
                verify(skillSpecializationRepository, times(1)).findAll();
                assertSame(response.getHttpStatus(), HttpStatus.OK);
            } else {
                fail();
            }
        }
        else {
            fail();
        }
    }

    @Test
    void getSkillSpecializationByIdOK() {
        try {
            ResponseDTO response = skillSpecializationService.getSkillSpecialization(1L);
            SkillSpecializationEntity skillSpe = (SkillSpecializationEntity) response.getBody();
            assertNotNull(skillSpe);
            assertNotNull(skillSpe.getDescriptionEN());
            assertNotNull(skillSpe.getDescriptionFR());
            verify(skillSpecializationRepository, times(1)).findById(1L);
            assertSame(response.getHttpStatus(), HttpStatus.OK);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getSkillSpecializationByIdWithIncorrectId()  {
        try {
            ResponseDTO response = skillSpecializationService.getSkillSpecialization(8L);
            verify(skillSpecializationRepository, times(1)).findById(8L);
            assertSame(response.getHttpStatus(), HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    void getSkillSpecializationByIdMissingParameter() {
        try {
            ResponseDTO response = skillSpecializationService.getSkillSpecialization(null);
            verify(skillSpecializationRepository, times(0)).findById(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void createSkillSpecializationOK() {
        try {
            ResponseDTO response = skillSpecializationService.createSkillSpecialization(JsonMock.getExistingSkillSpecializationJson());
            assertSame(response.getHttpStatus(), HttpStatus.CREATED);
            verify(skillSpecializationRepository, times(1)).save(any());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void createSkillSpecializationBadBody() {
        try {
            ResponseDTO response = skillSpecializationService.createSkillSpecialization(JsonMock.getBadSkillSpecializationJson());
            verify(skillSpecializationRepository, times(0)).save(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void createSkillSpecializationMissingBodyParameter() {
        try {
            ResponseDTO response = skillSpecializationService.createSkillSpecialization(null);
            verify(skillSpecializationRepository, times(0)).save(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void createSkillSpecializationEmptyBody() {
        try {
            ResponseDTO response = skillSpecializationService.createSkillSpecialization("[]");
            verify(skillSpecializationRepository, times(0)).save(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void removeSkillSpecializationOK() {
        try {
            ResponseDTO response = skillSpecializationService.removeSkillSpecialization(1L);
            verify(skillSpecializationRepository, times(1)).findById(any());
            verify(skillSpecializationRepository, times(1)).delete(any());
            assertSame(response.getHttpStatus(), HttpStatus.OK);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void removeSkillSpecializationMissingId() {
        try {
            ResponseDTO response = skillSpecializationService.removeSkillSpecialization(null);
            verify(skillSpecializationRepository, times(0)).findById(any());
            verify(skillSpecializationRepository, times(0)).delete(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void removeSkillSpecializationBadId() {
        try {
            ResponseDTO response = skillSpecializationService.removeSkillSpecialization(9L);
            verify(skillSpecializationRepository, times(1)).findById(any());
            verify(skillSpecializationRepository, times(0)).delete(any());
            assertSame(response.getHttpStatus(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationOk() {
        try {
            ResponseDTO response = skillSpecializationService.updateSkillSpecialization(JsonMock.getExistingSkillSpecializationJson());
            verify(skillSpecializationRepository, times(1)).save(any());
            assertSame(response.getHttpStatus(), HttpStatus.OK);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationNullBody() {
        try {
            ResponseDTO response = skillSpecializationService.updateSkillSpecialization(null);
            verify(skillSpecializationRepository, times(0)).save(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationEmptyBody() {
        try {
            ResponseDTO response = skillSpecializationService.updateSkillSpecialization("{}");
            verify(skillSpecializationRepository, times(0)).save(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationBadBody() {
        try {
            ResponseDTO response = skillSpecializationService.updateSkillSpecialization(JsonMock.getBadSkillSpecializationJson());
            verify(skillSpecializationRepository, times(0)).save(any());
            assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void updateSkillSpecializationNotExisting() {
        try {
            ResponseDTO response = skillSpecializationService.updateSkillSpecialization(JsonMock.getNotExistingSkillSpecializationJson());
            assertSame(response.getHttpStatus(), HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            fail();
        }
    }
}