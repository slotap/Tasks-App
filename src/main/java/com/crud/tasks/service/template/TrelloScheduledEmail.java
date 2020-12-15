package com.crud.tasks.service.template;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service("trelloScheduledEmail")
public class TrelloScheduledEmail implements MailCreatorService {

    private static final String GOODBYE_MESSAGE = "Kind Regards";
    private final TaskRepository taskRepository;
    private TemplateEngine templateEngine;
    private AdminConfig adminConfig;
    private CompanyConfig companyConfig;

    TrelloScheduledEmail(final TaskRepository taskRepository, @Qualifier("templateEngine") final TemplateEngine templateEngine, final AdminConfig adminConfig, final CompanyConfig companyConfig) {
        this.taskRepository = taskRepository;
        this.templateEngine = templateEngine;
        this.adminConfig = adminConfig;
        this.companyConfig = companyConfig;
    }

    @Override
    public String buildEmail(final String message) {

        List<Task> tasks = taskRepository.findAll();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/task_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", GOODBYE_MESSAGE);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("all_tasks", tasks);
        return templateEngine.process("templates.mail/list-current-trello-tasks-mail.html", context);
    }
}
