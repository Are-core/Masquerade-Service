package com.masquerade.controller.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.global.TitleEntity;
import com.masquerade.service.characterSheet.global.TitleService;
import com.masquerade.service.repository.characterSheet.global.TitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

class TitleControllerTest {
    @Mock
    TitleRepository titleRepository;

    TitleService titleService;

    TitleController titleController;

    @BeforeEach
    public void setUp() {
        titleRepository = spy(TitleRepository.class);
        setUpMocks();
        titleService = new TitleService(titleRepository);
        titleController = new TitleController(titleService);
    }

    private void setUpMocks(){
        List<TitleEntity> titleList = new ArrayList<>();

        titleList.add(new TitleEntity(1L, new SectEntity(), "", "", "", ""));
        titleList.add(new TitleEntity(2L, new SectEntity(), "", "", "", ""));
        titleList.add(new TitleEntity(3L, new SectEntity(), "", "", "", ""));

        Mockito.when(titleRepository.findAll()).thenReturn(titleList);
        Mockito.when(titleRepository.existsById(1L)).thenReturn(true);
        Mockito.when(titleRepository.findById(1L)).thenReturn(Optional.of(new TitleEntity(1L, new SectEntity(), "Test", "", "", "")));
        Mockito.when(titleRepository.save(any())).thenReturn(new TitleEntity(2L, new SectEntity(), "", "", "", ""));
    }

    @Test
    public void testGetStatusTypes() {
        ResponseEntityDTO<ResponseDTO> response = titleController.getTitles();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = titleController.getTitle(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetStatusTypeWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = titleController.getTitle(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetStatusTypeWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = titleController.getTitle(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = titleController.removeTitle(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveStatusTypeWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = titleController.removeTitle(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveStatusTypeWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = titleController.removeTitle(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = titleController.createTitle(JsonMock.getTitle());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateStatusTypeWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = titleController.createTitle(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateStatusTypeWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = titleController.createTitle(JsonMock.getBadTitle());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = titleController.updateTitle(JsonMock.getTitle());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateStatusTypeWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = titleController.updateTitle(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = titleController.updateTitle("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = titleController.updateTitle(JsonMock.getBadTitle());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = titleController.updateTitle(JsonMock.getNotExistingTitle());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}