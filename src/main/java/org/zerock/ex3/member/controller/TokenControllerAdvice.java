package org.zerock.ex3.member.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex3.member.exception.MemberTaskException;

import java.util.Map;

@RestController
@Log4j2
public class TokenControllerAdvice {

    @ExceptionHandler(MemberTaskException.class)
    public ResponseEntity<Map<String, String>>
    handleTakeException(MemberTaskException ex) {
        log.error(ex.getMessage());

        String msg = ex.getMsg();
        int status = ex.getCode();
        Map<String, String> map = Map.of("error", msg);

        return ResponseEntity.status(status).body(map);
    }
}
