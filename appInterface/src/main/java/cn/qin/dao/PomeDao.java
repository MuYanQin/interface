package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.Pome;
import cn.qin.vo.authorVo.AuthorVo;
import cn.qin.vo.pomeVo.PomeAboutVo;
import cn.qin.vo.pomeVo.PomeSearchVo;
import cn.qin.vo.pomeVo.PomeSerachList;
import cn.qin.vo.pomeVo.PomeVo;
import cn.qin.vo.rhesisVo.RhesisVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PomeDao extends BaseDao<Pome> {

    /**
     * @DESC  搜索信息包括作者、诗词、名句
     * @param searchText 搜做的内容 添加通配符
     * @param text 搜索的内容
     * @return
     */
    PomeSerachList findPomeBySearchText(@Param("searchText") String searchText, @Param("text") String text);


    /**
     * @param pomeAboutVo
     * @return
     */
    List<PomeAboutVo> searchabountPome(@Param("pomeAboutVo") PomeAboutVo pomeAboutVo);

    List<PomeAboutVo> searchabountAuthor(@Param("pomeAboutVo") PomeAboutVo pomeAboutVo);

    List<PomeAboutVo> searchabountrhesis(@Param("pomeAboutVo") PomeAboutVo pomeAboutVo);

    PomeVo findPomeDetailById(@Param("pomeId")String pomeId);

    List<PomeVo> findPomeListPageByAuthor(@Param("pomeVo") PomeVo pomeVo);

    List<PomeVo> findPomeListByPage(@Param("pomeVo") PomeVo pomeVo);


    List<PomeSearchVo> findRandomPomeForSize(@Param("size") int size,@Param("type") String type);

    List<RhesisVo> findRhesisListByPage(@Param("rhesisVo") RhesisVo rhesisVo);

    List<RhesisVo> findRhesisListByPageRand(@Param("rhesisVo") RhesisVo rhesisVo);

    PomeVo findPomeDaily(@Param("userId")String userId);

    List<AuthorVo> selectAuthorHasPome();
}
