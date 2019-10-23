package com.ssafy.obosa.registration;

import java.util.Locale;

import com.ssafy.obosa.model.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}