package com.buildify.wms.serviceTests;

import com.wareflow.buildify.domain.auth.signup.service.SignupService;
import com.wareflow.buildify.dto.UserDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class authServiceTests {

    @Autowired
    SignupService signupService;

    @Test
    public void testAddAuth() {
        signupService.addAuth("1", 0);
    }
}
