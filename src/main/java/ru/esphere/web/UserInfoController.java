package ru.esphere.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.esphere.model.User;
import ru.esphere.model.UserStatistic;
import ru.esphere.service.UserInfoService;
import ru.esphere.service.exception.UserNameDuplicatedException;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    @ResponseBody
    public List<UserStatistic> getUserStatistic() {
        User user = userInfoService.getCurrentUser();
        return userInfoService.getUserStatistic(user.getId());
    }

    @ExceptionHandler(UserNameDuplicatedException.class)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) throws UserNameDuplicatedException {
        userInfoService.createUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
