package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.AuthorDao;
import cn.qin.entity.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository extends AbstractBaseRepository<AuthorDao, Author> {
}
