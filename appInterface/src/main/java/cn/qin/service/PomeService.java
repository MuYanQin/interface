package cn.qin.service;

import cn.qin.dao.repository.AuthorRepository;
import cn.qin.dao.repository.PomeRepository;
import cn.qin.entity.Author;
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

    @Autowired
    private AuthorRepository authorRepository;


    /**
     * @Title:获取诗词的详情
     * @param pomeId 获取条数 默认15条
     */
    public PomeVo findPomeById(String pomeId){
        for (int i = 300; i < 330; i++) {
            findAndInsertData(""+i);
        }
        return  pomeRepository.findPomeById(pomeId);
    }

    private Pome findAndInsertData(String detailId){
        Pome pome = new Pome();
        //极速数据
        //鸡鸡 76399063a860b360
        //我 a8d949a2591c8d0f
        //花 c064ed1f4ff90141

        //唐诗 tangshi
        //宋词 songci
        //元曲 yuanqu
        String string = "https://api.jisuapi.com/tangshi/detail?";
        String param = "appkey=76399063a860b360&detailid=" + detailId;
        String text = string + param;
        String  respon =  HttpClientUtil.doGet(text);
        JSONObject jsonObject = JSONObject.parseObject(respon);
        if (jsonObject.getString("status").equals("0")){

            JSONObject result = jsonObject.getJSONObject("result");

            Example example = SqlUtil.newExample(Author.class);
            example.createCriteria().andEqualTo("name",result.getString("author"));
            Author author = authorRepository.selectOneByExample(example);
            if (author == null){
                author = new Author();
                String authorid = UUIDUtils.getUUID();
                author.setAuthorId(authorid);
                author.setName(result.getString("author"));
                authorRepository.insert(author);
            }

            pome.setPomeId(UUIDUtils.getUUID());
            pome.setName(result.getString("title"));
            pome.setContent(findText(result.getString("content")));
            pome.setAppreciation(findText(result.getString("appreciation")));
            pome.setExplanation(findText(result.getString("explanation")));
            pome.setType("1");
            pome.setAuthorId(author.getAuthorId());
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
