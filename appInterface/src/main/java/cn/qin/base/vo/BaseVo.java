package cn.qin.base.vo;


import cn.qin.util.DateUtils;
import cn.qin.util.EntityUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    protected Date createTime;

    @JsonIgnore
    protected String creator;

    @JsonIgnore
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

    @JsonIgnore
    @JSONField(serialize = false)
    public  BaseVo<T> getBaseVo(){
        return this;
    }

    /**
     * @Fields  :  是否分页 null、0:分页 , 1:不分页
     * @author qiaomengnan
     */
    private String pageFlag;
    @JsonIgnore
    public String getPageFlag() {
        return pageFlag;
    }
    @JsonProperty
    public void setPageFlag(String pageFlag) {
        this.pageFlag = pageFlag;
    }


    private String pageIndex;
    @JsonIgnore
    public String getPageIndex() {
        return pageIndex;
    }

    @JsonProperty
    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    @JsonIgnore
    public PageQuery getPageQuery(){
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageFlag(pageFlag);
        pageQuery.setPageIndex(pageIndex);
        return pageQuery;
    }


    /**
     * @Fields : 每页显示条数
     */
    @JsonIgnore
    private Integer pageSize = 10;

    /**
     * @Fields  :  是否需要合计
     * @author qiaomengnan
     */
    @JsonIgnore
    private String totalFlag;


}
