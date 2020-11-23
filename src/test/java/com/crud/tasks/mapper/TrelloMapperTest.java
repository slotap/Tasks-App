package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    TrelloMapper trelloMapper = new TrelloMapper();

    List<TrelloList> trelloLists = new ArrayList<>();
    List<TrelloListDto> trelloListDtoList = new ArrayList<>();
    TrelloCard trelloCard;
    TrelloCardDto trelloCardDto;

    @BeforeEach
    void init(){
        TrelloList testList1 = new TrelloList("testList1", "1",true);
        TrelloList testList2 = new TrelloList("testList2", "2",false);
        trelloLists.add(testList1);
        trelloLists.add(testList2);

        TrelloListDto testListDto1 = new TrelloListDto("1", "testListDto1",true);
        TrelloListDto testListDto2 = new TrelloListDto("2", "testListDto2",false);
        trelloListDtoList.add(testListDto1);
        trelloListDtoList.add(testListDto2);

        trelloCard = new TrelloCard("card","test Card","a","1");
        trelloCardDto = new TrelloCardDto("card","test Card","a","1");
    }

    @Test
    void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        TrelloBoardDto board1 = new TrelloBoardDto("1","firstBoard",trelloListDtoList);
        TrelloBoardDto board2 = new TrelloBoardDto("2","secondBoard",trelloListDtoList);
        trelloBoardDtoList.add(board1);
        trelloBoardDtoList.add(board2);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(trelloBoardList.size(),trelloBoardDtoList.size());
        assertEquals(trelloBoardList.get(0).getName(),trelloBoardDtoList.get(0).getName());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("1","firstBoard",trelloLists);
        TrelloBoard board2 = new TrelloBoard("2","secondBoard",trelloLists);
        trelloBoardList.add(board1);
        trelloBoardList.add(board2);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(trelloBoardList.size(),trelloBoardDtoList.size());
        assertEquals(trelloBoardList.get(0).getName(),trelloBoardDtoList.get(0).getName());
    }

    @Test
    void mapToListTest() {
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals(trelloLists.size(),trelloListDtoList.size());
        assertEquals(trelloLists.get(0).isClosed(),trelloListDtoList.get(0).isClosed());
    }

    @Test
    void mapToCardDtoTest() {
        //When
        TrelloCardDto trelloCardDtoMapped = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDtoMapped, trelloCardDto);
    }

    @Test
    void mapToCardTest() {
        //When
        TrelloCard trelloCardMapped = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardMapped.getName(), trelloCard.getName());
    }
}