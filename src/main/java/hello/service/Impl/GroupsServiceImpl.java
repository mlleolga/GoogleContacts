package hello.service.Impl;

import hello.domain.entity.Groups;
import hello.domain.entity.User;
import hello.repository.GroupRepository;
import hello.service.GroupService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Override
    public Groups findByName(String name, User user) {
       return groupRepository.findByNameAndUser(name,user);
    }

    @Override
    public Groups save(Groups groups) {
       return groupRepository.save(groups);
    }

    @Override
    public List<Groups> findByPartName(String name,Long userId) {
        return groupRepository.findByNameIgnoreCaseContainingAndUser(name, userService.findUserById(userId));
    }
}
