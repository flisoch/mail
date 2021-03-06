package ru.itis.flisoch.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FilterShortDto;
import ru.itis.flisoch.mail.dto.FolderDto;
import ru.itis.flisoch.mail.form.FilterForm;
import ru.itis.flisoch.mail.form.FolderForm;
import ru.itis.flisoch.mail.form.GeneralSettingsForm;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.FilterService;
import ru.itis.flisoch.mail.service.FolderService;
import ru.itis.flisoch.mail.service.UserService;

import java.util.List;

@Controller
@RequestMapping(path = "/settings")
public class SettingsController {

    private final FilterService filterService;
    private final UserService userService;
    private final FolderService folderService;

    @Autowired
    public SettingsController(FilterService filterService, UserService userService, FolderService folderService) {
        this.filterService = filterService;
        this.userService = userService;
        this.folderService = folderService;
    }

    @GetMapping
    public String settings() {
        return "redirect:/settings/general";
    }

    @GetMapping("/general")
    public String generalSettings(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        String signature = user.getSignature();
        modelMap.put("signature", signature);
        return "settings/general";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addSignature(Authentication authentication, ModelMap modelMap,
                                       @RequestBody GeneralSettingsForm form) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        String signature = userService.addSignature(user, form.getSignature());
        return ResponseEntity.ok(signature);
    }

    @GetMapping("/folders")
    public String foldersSettings(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<FolderDto> folders = folderService.customUserFolders(user);
        modelMap.put("customFolders", folders);
        return "settings/folders";
    }

    @PostMapping("/folders")
    public String newFolder(Authentication authentication, String folderName) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        folderService.addFolder(user, folderName);
        return "redirect:/settings/folders";
    }

    @PutMapping("/folders/{folderId}")
    @ResponseBody
    public ResponseEntity newFolder(Authentication authentication,
                            @PathVariable Long folderId,
                            @RequestBody FolderForm form) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        FolderDto folder = folderService.editFolder(user, folderId, form);
        return ResponseEntity.ok(folder);
    }

    @DeleteMapping("/folders/{folderId}")
    @ResponseBody
    public ResponseEntity deleteFolder(Authentication authentication, @PathVariable Long folderId) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        folderService.deleteFolder(user, folderId);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/filters")
    public String myFilters(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<FilterShortDto> filters = filterService.getFilters(user);
        modelMap.put("filters", filters);
        return "filters/filters";
    }

    @GetMapping("/filters/new")
    public String newFilterPage(Authentication authentication, ModelMap modelMap) {
        return "filters/new";
    }

    @PostMapping("/filters")
    public String newFilter(Authentication authentication, @RequestBody FilterForm filterForm) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        filterService.createFilter(user, filterForm);
        return "redirect:/settings/filters";
    }

    @DeleteMapping("/filters/{filterId}")
    @ResponseBody
    public ResponseEntity deleteFilter(Authentication authentication, @PathVariable Long filterId) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        filterService.deleteFilter(user, filterId);
        return ResponseEntity.status(200).build();
    }
}
