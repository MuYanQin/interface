package cn.qin.dao.repository;

import cn.qin.base.repository.AbstractBaseRepository;
import cn.qin.dao.SpellDao;
import cn.qin.entity.Spell;
import org.springframework.stereotype.Repository;

@Repository
public class SpellRepository extends AbstractBaseRepository<SpellDao, Spell> {
}
