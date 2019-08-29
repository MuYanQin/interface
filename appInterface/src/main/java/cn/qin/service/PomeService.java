package cn.qin.service;

import cn.qin.dao.repository.PomeRepository;
import cn.qin.entity.Pome;
import cn.qin.util.HttpClientUtil;
import cn.qin.util.SqlUtil;
import cn.qin.util.UUIDUtils;
import cn.qin.vo.PomeVo;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class PomeService {
    @Autowired
    private PomeRepository pomeRepository;


    public PomeVo findPomeById(String pomeId){

        return  pomeRepository.findPomeById(pomeId);
    }

    private Pome findAndInsertData(String detailId){
        Pome pome = new Pome();
        //76399063a860b360  鸡鸡
        //我 a8d949a2591c8d0f
        //花 c064ed1f4ff90141
        String string = "https://api.jisuapi.com/tangshi/detail?";
        String param = "appkey=c064ed1f4ff90141&detailid=" + detailId;
        String text = string + param;
        String  respon =  HttpClientUtil.doGet(text);
        JSONObject jsonObject = JSONObject.parseObject(respon);
        if (jsonObject.getString("status").equals("0")){
            JSONObject result = jsonObject.getJSONObject("result");
            pome.setPomeId(UUIDUtils.getUUID());
            pome.setName(result.getString("title"));
            pome.setContent(findText(result.getString("content")));

            pome.setDetailId(result.getString("detailid"));
            pome.setAppreciation(findText(result.getString("appreciation")));
            pome.setExplanation(findText(result.getString("explanation")));
            pomeRepository.insert(pome);
        }
        return pome;
    }
    public List<Pome> findPomeByPage(String pageIndex){
        Example example = SqlUtil.newExample(Pome.class);
        example.createCriteria();
        PageInfo pageInfo = pomeRepository.selectListVoByPage(example,pageIndex);
        return pageInfo.getList();
    }
    private  String findText(String text){
        text = text.replaceAll("</p>","\n");
        text = text.replaceAll("<br />","\n");
        text = text.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        text = text.replaceAll("[(/>)<]", "").trim();

        return  text;
    }
}
