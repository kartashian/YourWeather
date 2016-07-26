package ru.esphere.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User with the same name already exist")
public class UserNameDuplicatedException extends Exception {
}
