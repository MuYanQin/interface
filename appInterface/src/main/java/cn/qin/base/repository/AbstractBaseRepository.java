package cn.qin.base.repository;


import cn.qin.base.dao.BaseDao;
import cn.qin.base.entity.BaseEntity;
import cn.qin.base.vo.PageQuery;
import cn.qin.constancts.MarkedWordsConstants;
import cn.qin.enums.DeleteFlags;
import cn.qin.enums.PageFlagEnums;
import cn.qin.util.ArrayUtils;
import cn.qin.util.ReflectUtils;
import cn.qin.util.SqlUtil;
import cn.qin.util.StringUtils;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: AbstractBaseRepository
 * @Description: 抽象仓库访问基类
 * @author qiaohao
 * @date 2017/11/2
 */
@Slf4j
@Data
public abstract class AbstractBaseRepository<T extends BaseDao, K extends BaseEntity> {

	@Autowired
	public T baseDao;

	/**
	 * @Title:
	 * @Description:   录入数据并返回回去
	 * @param entity
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:48:47
	 */
	public int insertData(K entity) {
		return baseDao.insert(entity);
	}

	/**
	 * @Title:
	 * @Description: 根据主键更新数据
	 * @param entity
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:50:04
	 */
	public int updateByPrimaryKeyData(K entity) {
		return baseDao.updateByPrimaryKey(entity);
	}


	/**
	 * @Title:
	 * @Description:   根据主键更新数据,只更新实体中不为null的数据
	 * @param entity
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:50:31
	 */
	public int updateByPrimaryKeySelectiveData(K entity) {
		return baseDao.updateByPrimaryKeySelective(entity);
	}


	/**
	 * @Title:
	 * @Description: 根据组合条件更新实体
	 * @param entity
	 * @param example
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:51:05
	 */
	public int updateByExampleData(K entity, Example example) {
		checkUpdateExampleNullOrTrim(example);
		SqlUtil.andEqualToDeleteExist(example.getOredCriteria());
		return baseDao.updateByExample(entity, example);
	}
	/**
	 * @Title:
	 * @Description:   根据组合条件更新实体,只更新实体中属性不为null的数据
	 * @param entity
	 * @param example
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:51:33
	 */
	public int updateByExampleSelectiveData(K entity, Example example) {
		checkUpdateExampleNullOrTrim(example);
		SqlUtil.andEqualToDeleteExist(example.getOredCriteria());
		return baseDao.updateByExampleSelective(entity, example);
	}

	/**
	 * @Title:
	 * @Description: 根据组合条件更新实体,并进行排他
	 * @param entity
	 * @param example
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:51:05
	 */
	public int updateByExampleData(K entity, Example example, boolean exclusive) {
		checkUpdateExampleNullOrTrim(example);
		return updateByExampleData(entity, example);
	}




	/**
	 * @Title:
	 * @Description: 物理删除一条数据
	 * @param:  entity
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/4/16  10:08
	 */
	public int deletePhysicsEntity(K entity){
		return baseDao.delete(entity);
	}

	/**
	 * @Title:
	 * @Description: 物理批量删除实体数据
	 * @param:  entity
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/4/16  10:08
	 */
	public int deletePhysicsEntityList(List ids){
		Class clazz = (Class <K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		List<EntityColumn> pkColumnList = new ArrayList<>(EntityHelper.getPKColumns(clazz));
		if(ArrayUtils.isNullOrLengthZero(pkColumnList) || pkColumnList.size() != 1)
			throw new RuntimeException(MarkedWordsConstants.SQL_ID_ONLY_ERROR_MESSAGE);
		Example example = SqlUtil.newExample(clazz);
		example.createCriteria().andIn(pkColumnList.get(0).getProperty(),ids);
		checkUpdateExampleNullOrTrim(example);
		return baseDao.deleteByExample(example);
	}

	/**
	 * @Title:
	 * @Description:  根据组合条件虚拟删除数据
	 * @param example
	 * @param example
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:52:29
	 */
	public int deleteExampleData(Example example,K entity){
		checkUpdateExampleNullOrTrim(example);
		SqlUtil.andEqualToDeleteExist(example.getOredCriteria());
		return baseDao.updateByExampleSelective(entity,example);
	}
	/**
	 * @Title:
	 * @Description:   根据主键删除数据 虚拟删除
	 * @param entity
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:52:13
	 */
	public int deleteData(K entity) {
		entity.setDelFlag(DeleteFlags.NOT_EXIST.getFlag());
		return baseDao.updateByPrimaryKeySelective(entity);
	}
	/**
	 * @Title:
	 * @Description:  虚拟删除 根据id删除
	 * @param ids
	 * @param entity
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 03:50:19
	 */
	public int deleteDataList(List ids,K entity){
		if(ArrayUtils.isNotNullAndLengthNotZero(ids)) {
			Class clazz = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
			List<EntityColumn> pkColumnList = new ArrayList<>(EntityHelper.getPKColumns(clazz));
			if (ArrayUtils.isNullOrLengthZero(pkColumnList) || pkColumnList.size() != 1)
				throw new RuntimeException(MarkedWordsConstants.SQL_ID_ONLY_ERROR_MESSAGE);
			return deleteByIds(ids, entity, pkColumnList.get(0).getProperty());
		}
		return 0;
	}

	/**
	 * @Title:
	 * @Description:   虚拟删除 根据定义的列名删除
	 * @param ids
	 * @param entity
	 * @param primaryKey
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:55:42
	 */
	private int deleteByIds(List ids,K entity,String primaryKey){
		if(ArrayUtils.isNotNullAndLengthNotZero(ids) && entity != null) {
			Example example = SqlUtil.newExample(entity.getClass());
			example.createCriteria().andIn(primaryKey, ids);
			checkUpdateExampleNullOrTrim(example);
			SqlUtil.andEqualToDeleteExist(example.getOredCriteria());
			return baseDao.updateByExampleSelective(entity, example);
		}
		return 0;
	}

	/**
	 * @Title:
	 * @Description:   查询所有数据
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:56:40
	 */
	public List<K> selectAll() {
        Class clazz = (Class <K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = SqlUtil.newExample(clazz);
        example.createCriteria();
        SqlUtil.andEqualToDeleteExist(example.getOredCriteria());
        return baseDao.selectByExample(example);
	}

	/**
	 * @Title:
	 * @Description:   根据组合条件查询第一条数据
	 * @param example
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:56:58
	 */
	public K selectOneByExample(Example example) {
	    if(ArrayUtils.isNullOrLengthZero(example.getOredCriteria()))
	        example.createCriteria();
		SqlUtil.andEqualToDeleteExist(example.getOredCriteria());
		List results = baseDao.selectByExample(example);
		if (ArrayUtils.isNotNullAndLengthNotZero(results))
			return (K) results.get(0);
		return null;
	}

	/**
	 * @Title:
	 * @Description:   根据主键查询数据
	 * @param id
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:57:14
	 */
	public K selectByPrimaryKey(Object id){
		Object result = baseDao.selectByPrimaryKey(id);
		//instanceof 严格来说是Java中的一个双目运算符，用来测试一个对象是否为一个类的实例，用法为
		if(result instanceof BaseEntity){
			BaseEntity entity = (BaseEntity)result;
			//如果状态是删除 则返回空回去
			if(DeleteFlags.NOT_EXIST.getFlag().equals(entity.getDelFlag()))
				return null;
		}
		return (K)result;
	}

	/**
	 * @Title:
	 * @Description:   根据组合条件查询数据
	 * @param example
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/24 04:57:36
	 */
	public List<K> selectListByExample(Example example) {
        if(ArrayUtils.isNullOrLengthZero(example.getOredCriteria()))
            example.createCriteria();
		SqlUtil.andEqualToDeleteExist(example.getOredCriteria());
		return baseDao.selectByExample(example);
	}


	/**
	 * @Title:
	 * @Description: 分页封装
	 * @param example,pageQuery
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/01/09 04:49:17
	 */
	public PageInfo selectListVoByPage(Example example,PageQuery pageQuery){
		PageInfo pageInfo = new PageInfo();
		if (PageFlagEnums.NOT_PAGE.getFlag().equals(pageQuery.getPageFlag())) {

			//不分页则全部查询
			List<K> results = baseDao.selectByExample(example);
			pageInfo.setList(results);

		}else {
			if (StringUtils.isTrimBlank(pageQuery.getPageSize())){
				pageQuery.setPageSize("15");
			}
			 pageInfo = PageHelper.startPage(new Integer(pageQuery.getPageIndex()),new Integer(pageQuery.getPageSize()))
					.doSelectPageInfo(new ISelect() {
						@Override
						public void doSelect() {
							baseDao.selectByExample(example);
						}
					});
		}

		return pageInfo;
	}

	/**
	 * @Title:
	 * @Description: 通用多表分页
	 * @param methodName
	 * @param param
	 * @param
	 * @return
	 * @throws
	 * @author qiaomengnan
	 * @date 2018/02/12 04:40:51
	 */
	public 	PageInfo selectListVoByPage(String methodName, Object param, PageQuery pageQuery){
			PageInfo pageInfo = new PageInfo();

			if (PageFlagEnums.NOT_PAGE.getFlag().equals(pageQuery.getPageFlag())){
				try {
					Method method = baseDao.getClass().getDeclaredMethod(methodName, param.getClass());
					Object result = method.invoke(baseDao, param);

					if(result != null) {
						List results = (List) result;
						pageInfo.setList(results);
					}
				}catch (Exception ex){
					log.error(ex.getMessage());
					ex.printStackTrace();
					throw  new RuntimeException("查询失败");
				}
			}else {
				if (StringUtils.isTrimBlank(pageQuery.getPageSize())){
					pageQuery.setPageSize("15");
				}
				if(StringUtils.isTrimBlank(pageQuery.getPageIndex())){
					pageQuery.setPageIndex("1");
				}
				pageInfo = PageHelper.startPage(new Integer(pageQuery.getPageIndex()),new Integer(pageQuery.getPageSize()))
						.doSelectPageInfo(new ISelect() {
							@Override
							public void doSelect() {
								try {
									Method method = baseDao.getClass().getDeclaredMethod(methodName, param.getClass());
									method.invoke(baseDao, param);
								}catch (Exception ex){
									log.error(ex.getMessage());
									ex.printStackTrace();
									throw  new RuntimeException("查询失败");
								}
							}
						});
			}
			return pageInfo;
	}

	private void checkUpdateExampleNullOrTrim(Example example){
		if(example == null)
			throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_NOT_NULL_ERROR_MESSAGE);
		List<Example.Criteria> criteriaList = example.getOredCriteria();
		if(ArrayUtils.isNullOrLengthZero(criteriaList))
			throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_CRITERIA_NOT_EXIST_ERROR_MESSAGE);
		for(Example.Criteria criteria : criteriaList){
			List<Example.Criterion> criterionList = criteria.getAllCriteria();
			if(ArrayUtils.isNullOrLengthZero(criterionList))
				throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_CRITERION_NOT_EXIST_ERROR_MESSAGE);
			for(Example.Criterion criterion : criterionList){
				if(criteria == null)
					throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_CRITERION_NOT_EXIST_ERROR_MESSAGE);
				if(criterion.getValue() == null)
					throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_CRITERION_VALUE_NOT_EXIST_ERROR_MESSAGE);
				if(criterion.getValue() instanceof String){
					if(StringUtils.isTrimBlank(criterion.getValue()))
						throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_CRITERION_VALUE_NOT_EXIST_ERROR_MESSAGE);
				} else if(criterion.getValue() instanceof List){
					if(ArrayUtils.isNullOrLengthZero((List)criterion.getValue()))
						throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_CRITERION_VALUE_NOT_EXIST_ERROR_MESSAGE);
				} else if(criterion.getValue().getClass().isArray()){
					if(Array.getLength(criterion.getValue()) < 1)
						throw new RuntimeException(MarkedWordsConstants.SQL_EXAMPLE_CRITERION_VALUE_NOT_EXIST_ERROR_MESSAGE);
				}
			}
		}
	}

	private void setIdValue(Example example,List<EntityColumn> pkColumnList,K entity,Class clazz){
		Example.Criteria criteria = example.createCriteria();
		for(EntityColumn entityColumn : pkColumnList){
			try {
				criteria.andEqualTo(entityColumn.getProperty(), ReflectUtils.getFieldValue(entityColumn.getProperty(),entity,clazz));
			} catch (Exception ex) {
				log.error(ex.getMessage());
				ex.printStackTrace();
				throw new RuntimeException(MarkedWordsConstants.SQL_GET_ID_VALUE_ERROR_MESSAGE);
			}
		}
	}

}
