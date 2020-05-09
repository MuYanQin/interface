package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.AuthorDao;
import cn.qin.entity.Author;
import cn.qin.vo.authorVo.AuthorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository extends AbstractBaseRepository<AuthorDao, Author> {
    @Autowired
    private AuthorDao authorDao;
    public List<AuthorVo> findAuthorBySort() {
        return  authorDao.findAuthorBySort();
    }
}
