package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.exception.UserAlreadyConnectedException;
import org.greenwin.VLclient.proxies.LogInProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private LogInProxy logInProxy;

    @Mock
    private MockHttpSession session;

    @InjectMocks
    private LoginService loginService;

    @Test
    public void signIn() {
        AppUser user = new AppUser();
        loginService.signIn(new AppUser(), session);
        verify(logInProxy, times(1)).login(user);
    }

    //TODO: complete
    @Test
    public void signInWhenUserAlreadyConnected() {
        AppUser connectedUser = new AppUser();
        loginService.signIn(new AppUser(), session);
    }

    @Test
    public void signUp() {
        AppUser user = new AppUser();
        loginService.signUp(user, session);
        verify(logInProxy, times(1)).saveUser(user);

    }
}
