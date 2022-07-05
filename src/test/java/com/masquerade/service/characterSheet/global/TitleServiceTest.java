package com.masquerade.service.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.global.TitleEntity;
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

class TitleServiceTest {
    @Mock
    TitleRepository titleRepository;

    TitleService titleService;

    @BeforeEach
    public void setUp() {
        titleRepository = spy(TitleRepository.class);
        setUpMocks();
        titleService = new TitleService(titleRepository);
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
    public void testTitleList() {
        ResponseDTO response = titleService.getTitles();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testTitleById() {
        ResponseDTO response = titleService.getTitle(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        TitleEntity title = (TitleEntity) response.getBody();
        assertEquals("Test", title.getDescriptionEN());
    }

    @Test
    public void testTitleByIdWithMissingId() {
        ResponseDTO response = titleService.getTitle(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testTitleByIdWithNotExistingId() {
        ResponseDTO response = titleService.getTitle(9L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testRemoveTitleOk() {
        ResponseDTO response = titleService.removeTitle(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testRemoveTitleWithMissingId() {
        ResponseDTO response = titleService.removeTitle(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testRemoveTitleWithNotExistingId() {
        ResponseDTO response = titleService.removeTitle(9L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateTitleOk() {
        ResponseDTO response = titleService.createTitle(JsonMock.getTitle());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateTitleWithMissingBody() {
        ResponseDTO response = titleService.createTitle(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateTitleWithBadBody() {
        ResponseDTO response = titleService.createTitle(JsonMock.getBadTitle());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateTitleOk() {
        ResponseDTO response = titleService.updateTitle(JsonMock.getTitle());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateTitleWithMissingBody() {
        ResponseDTO response = titleService.updateTitle(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateTitleWithBadBody() {
        ResponseDTO response = titleService.updateTitle(JsonMock.getBadTitle());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateTitleWithEmptyBody() {
        ResponseDTO response = titleService.updateTitle("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateTitleWithNotExistingId() {
        ResponseDTO response = titleService.updateTitle(JsonMock.getNotExistingTitle());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

}