package ru.itis.flisoch.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FilterDto;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.FilterService;

import java.util.List;

@Controller
@RequestMapping(path = "/settings")
public class SettingsController {

    private final FilterService filterService;

    @Autowired
    public SettingsController(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping("/filters")
    public String myFilters(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<FilterDto> filters = filterService.getFilters(user);
        modelMap.put("filters", filters);
        return "filters/filters";
    }
    @GetMapping("/filters/new")
    public String newFilterPage(Authentication authentication, ModelMap modelMap) {
        return "filters/new";
    }
}
