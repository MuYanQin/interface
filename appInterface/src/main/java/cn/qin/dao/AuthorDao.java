package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Author;
import cn.qin.vo.authorVo.AuthorVo;

import java.util.List;

public interface AuthorDao extends BaseDao<Author> {

    List<AuthorVo> findAuthorBySort();
}
