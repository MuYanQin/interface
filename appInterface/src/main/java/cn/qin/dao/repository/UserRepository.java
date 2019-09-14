package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.UserDao;
import cn.qin.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractBaseRepository<UserDao, User> {
}
