package ru.itis.flisoch.mail.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FolderDto;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.FolderService;

import java.util.List;

@Aspect
@Component
public class FillPagesAspect {

    @Autowired
    private FolderService folderService;

    @Around(value = "execution(* ru.itis.flisoch.mail.controller..*(..)) && args(authentication, modelMap, ..)",
            argNames = "pjp, authentication, modelMap")
    public Object fill(ProceedingJoinPoint pjp, Authentication authentication, ModelMap modelMap) throws Throwable {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<FolderDto> folders = folderService.foldersByUser(user);
        modelMap.put("user", UserDto.from(user));
        modelMap.put("folders", folders);
        return pjp.proceed(pjp.getArgs());
    }
}
