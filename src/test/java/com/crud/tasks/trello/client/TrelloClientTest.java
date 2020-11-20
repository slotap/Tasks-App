package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

    //@InjectMocks
    @Autowired
    private TrelloClient trelloClient;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private TrelloConfig trelloConfig;

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
            when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
            when(trelloConfig.getTrelloAppKey()).thenReturn("test");
            when(trelloConfig.getTrelloToken()).thenReturn("test");
            when(trelloConfig.getTrelloUsername()).thenReturn("test");

            TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
            trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

            URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");

            when(restTemplate.getForObject(uri,TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
            List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
            assertEquals(fetchedTrelloBoards.size(),1);
            assertEquals(fetchedTrelloBoards.get(0).getId(),"test_id");
            assertEquals(fetchedTrelloBoards.get(0).getName(),"test_board");
            assertEquals(fetchedTrelloBoards.get(0).getLists(),new ArrayList<>());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "Test Description", "top", "test_Id");

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_Id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "Test task",
                "http://test.com"
        );

        when(restTemplate.postForObject(uri,null,CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals(newCard.getId(),"1");
        assertEquals(newCard.getName(), "Test task");
        assertEquals(newCard.getShortUrl(),"http://test.com");
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUsername()).thenReturn("test");


        URI uri = new URI("http://test.com/members/test/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri,TrelloBoardDto[].class)).thenReturn(null);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(fetchedTrelloBoards.size(),0);
    }
}