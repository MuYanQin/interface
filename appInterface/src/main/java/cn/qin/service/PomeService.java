package cn.qin.service;

import cn.qin.dao.repository.AuthorRepository;
import cn.qin.dao.repository.PomeRepository;
import cn.qin.entity.Pome;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import cn.qin.vo.idiomVo.IdiomSearchVo;
import cn.qin.vo.pomeVo.PomeSearchVo;
import cn.qin.vo.pomeVo.PomeVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PomeService {
    @Autowired
    private PomeRepository pomeRepository;

    @Autowired
    private AuthorRepository authorRepository;


    /**
     * @param searchText 参数
     * @Title:搜索诗词
     */
    public List<PomeSearchVo> findPomeBySearchText(String searchText) {
        if (StringUtils.isTrimBlank(searchText)) {
            throw new RuntimeException("搜索内容不能为空");
        }
        String text = searchText;
        searchText = SqlUtil.likePattern(searchText);

        return pomeRepository.findPomeBySearchText(searchText, text);
    }

    /**
     * @param pomeId 诗词的ID
     * @Title:获取诗词的详情
     */
    public PomeVo findPomeDetailById(String pomeId) {
        return pomeRepository.findPomeDetailById(pomeId);
    }

    /**
     * @param pomeVo 参数 authorName 作者
     * @Title:分页获取诗词
     */
    public List<Pome> findPomeListByPage(PomeVo pomeVo) {
        if (StringUtils.isNotTrimBlank(pomeVo.getAuthorName())) {
            pomeVo.setAuthorName(SqlUtil.likePattern(pomeVo.getAuthorName()));
        } else {
            pomeVo.setAuthorName(null);
        }
        PageInfo pageInfo = pomeRepository.selectListVoByPage("findPomeListByPage", pomeVo, pomeVo.getPageQuery());
        return pageInfo.getList();
    }

    /**
     * @param size 获取条数 默认15条
     * @Title:随机获取诗文
     */
    public List<PomeSearchVo> findRandomPomeForSize(String size) {
        int siz = 15;
        if (StringUtils.isNotTrimBlank(size)) {
            siz = Integer.parseInt(size);
        }
        return pomeRepository.findRandomPomeForSize(siz);
    }
    /**
     * @Title:每日一首
     * @param
     */
    public PomeVo findPomeDaily(){
        return  pomeRepository.findPomeDaily();
    }
}
