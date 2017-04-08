package hello.service;

import hello.domain.entity.Groups;
import hello.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sasha on 07.04.2017.
 */
@Service
public interface GroupService {
  Groups findByName(String name, User user);

  Groups save(Groups groups);

  List<Groups> findByPartName(String name,Long userId);
}
