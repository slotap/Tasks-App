package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.template.TrelloCardEmail;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {
    private static final String SUBJECT = "Tasks : Create Trello Card" ;
    private final SimpleEmailService emailService;
    private final TrelloClient trelloClient;
    private final AdminConfig adminConfig;
    private final MailCreatorService mailCreatorService;

    TrelloService(final SimpleEmailService emailService, final TrelloClient trelloClient, final AdminConfig adminConfig,  @Qualifier("trelloCardEmail") final MailCreatorService mailCreatorService) {
        this.emailService = emailService;
        this.trelloClient = trelloClient;
        this.adminConfig = adminConfig;
        this.mailCreatorService = mailCreatorService;
    }

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        Optional.ofNullable(newCard).ifPresent(card -> emailService.send(Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message("New card: " + trelloCardDto.getName() + " has been created on your Trello account")
                .build()
            ,mailCreatorService));
        return newCard;
    }
}
