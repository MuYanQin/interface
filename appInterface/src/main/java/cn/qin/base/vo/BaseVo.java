package cn.qin.base.vo;


import cn.qin.util.DateUtils;
import cn.qin.util.EntityUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

/**
 * @author qiaomengnan
 * @ClassName: BaseVo
 * @Description:
 * @date 2018/2/5
 */
@Slf4j
@Data
public abstract class BaseVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @JsonFormat(pattern = DateUtils.formatStr_yyyyMMddHHmmssSSS)
    protected Date createTime;
    @JsonIgnore
    protected String creator;
    @JsonIgnore
    @JsonFormat(pattern = DateUtils.formatStr_yyyyMMddHHmmssSSS)
    protected Date updateTime;
    @JsonIgnore
    protected String updater;

    @JsonIgnore
    protected Integer delFlag;

    @JsonIgnore
    @JSONField(serialize = false)
    public T getEntity(){
        try{
            Class clazz = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return EntityUtils.getEntity(this,(T)clazz.newInstance());
        }catch (IllegalAccessException ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }catch (InstantiationException ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * @Fields : 当前页数
     */
    @JsonIgnore
    private Integer currenPage;


    /**
     * @Fields : 每页显示条数
     */
    @JsonIgnore
    private Integer pageSize = 10;


    @JsonIgnore
    @JSONField(serialize = false)
    public  BaseVo<T> getBaseVo(){
        return this;
    }

    /**
     * @Fields  :  是否分页 null、0:分页 , 1:不分页
     * @author qiaomengnan
     */
    @JsonIgnore
    private String pageFlag;


    /**
     * @Fields  :  是否需要合计
     * @author qiaomengnan
     */
    @JsonIgnore
    private String totalFlag;


}
