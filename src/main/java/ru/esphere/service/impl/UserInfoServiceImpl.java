package ru.esphere.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.esphere.model.User;
import ru.esphere.model.UserAction;
import ru.esphere.model.UserStatistic;
import ru.esphere.repository.UserInfoRepository;
import ru.esphere.service.UserInfoService;
import ru.esphere.service.exception.UserNameDuplicatedException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userInfoRepository.getUserByName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + "not found");
        }
        writeUserAuthStatistic(user.getId());
        return buildUserForAuthentication(user);
    }

    private UserDetails buildUserForAuthentication(User user) {
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER")));
    }

    private void writeUserAuthStatistic(Long userId) {
        UserStatistic statistic = new UserStatistic(userId, new Date(), UserAction.LOGIN.name(), "");
        userInfoRepository.saveUserStatistic(statistic);
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfoRepository.getUserByName(userDetails.getUsername());
    }

    @Override
    public void createUser(User user) throws UserNameDuplicatedException {
        if (user == null) return;
        User existedUser = userInfoRepository.getUserByName(user.getName());
        if (existedUser != null) {
            throw new UserNameDuplicatedException();
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userInfoRepository.saveUser(user);
    }

    @Override
    public List<UserStatistic> getUserStatistic(Long userId) {
        List<UserStatistic> statistics = userInfoRepository.getUserStatistic(userId);
        Collections.sort(statistics, (s1, s2) -> s2.getActionTime().compareTo(s1.getActionTime()));
        return statistics;
    }
}
