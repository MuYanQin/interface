package cn.qin.base.aop.repository;

import cn.qin.base.entity.BaseEntity;
import cn.qin.enums.DeleteFlags;

import java.util.Date;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: AopRepositoryUtil
 * @Description:
 * @date 2018/2/24
 */
public class AopRepositoryUtil {

    public static void insert(Object object){
        if(object instanceof BaseEntity){
            BaseEntity entity = (BaseEntity) object;
            Date nowDate = new Date();
            entity.setCreateTime(nowDate);
            entity.setUpdateTime(nowDate);
            if(entity.getDelFlag() == null)
                entity.setDelFlag(DeleteFlags.EXIST.getFlag());
        }
    }

    public static void update(Object object){
        if(object instanceof BaseEntity){
            BaseEntity entity = (BaseEntity) object;
            Date nowDate = new Date();
            //放上本次更新时间
            entity.setUpdateTime(nowDate);
            if(entity.getDelFlag() == null)
                entity.setDelFlag(DeleteFlags.EXIST.getFlag());
        }
    }

    public static void delete(Object object){
        if(object instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) object;
            Date nowDate = new Date();
            entity.setUpdateTime(nowDate);
            entity.setDelFlag(DeleteFlags.NOT_EXIST.getFlag());
        }
    }

    public static void insertList(Object object){
        if(object instanceof List){
            List objectList = (List)object;
            for(Object element : objectList){
                if(element instanceof BaseEntity){
                    insert(element);
                }
            }
        }
    }

    public static void updateList(Object object){
        if(object instanceof List){
            List objectList = (List)object;
            for(Object element : objectList){
                if(element instanceof BaseEntity){
                    update(element);
                }
            }
        }
    }

}
