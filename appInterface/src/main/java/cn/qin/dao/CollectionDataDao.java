package cn.qin.dao;

import cn.qin.base.dao.BaseDao;
import cn.qin.entity.CollectionData;
import cn.qin.vo.collectionData.CollectionDataVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionDataDao extends BaseDao<CollectionData> {

    List<CollectionDataVo> seletWordList(@Param("collectionDataVo") CollectionDataVo collectionDataVo);

    List<CollectionDataVo> seletIdiomList(@Param("collectionDataVo") CollectionDataVo collectionDataVo);

    List<CollectionDataVo> seletRhesisList(@Param("collectionDataVo") CollectionDataVo collectionDataVo);

    List<CollectionDataVo> seletPomeList(@Param("collectionDataVo") CollectionDataVo collectionDataVo);
}
