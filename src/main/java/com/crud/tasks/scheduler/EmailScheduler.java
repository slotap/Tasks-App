package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;
    private final MailCreatorService mailCreatorService;
    private static final String SUBJECT = "Tasks: Once a day email";

    EmailScheduler(final SimpleEmailService simpleEmailService, final TaskRepository taskRepository, final AdminConfig adminConfig, @Qualifier("trelloScheduledEmail") final MailCreatorService mailCreatorService) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
        this.mailCreatorService = mailCreatorService;
    }

    //@Scheduled(cron = "0 0 10 * * *")
   // @Scheduled(fixedDelay = 20000)
    public void sendInformationEmail(){
        simpleEmailService.send(Mail.builder()
                .mailTo(adminConfig.getAdminMail())
                .subject(SUBJECT)
                .message(createMessage())
                .build(),mailCreatorService);
    }

    private String createMessage() {
        long size = taskRepository.count();
        return String.format("Currently in database you have: %s task%s", size, (size > 1) ? "s" : "");
    }
}
