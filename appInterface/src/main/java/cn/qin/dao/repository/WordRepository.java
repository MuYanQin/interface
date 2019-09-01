package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.WordDao;
import cn.qin.entity.Word;
import org.springframework.stereotype.Repository;

@Repository
public class WordRepository extends AbstractBaseRepository<WordDao, Word> {
}
