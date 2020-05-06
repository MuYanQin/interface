package cn.qin.service;

import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.dao.repository.PartJobDetRepository;
import cn.qin.dao.repository.PartJobRepository;
import cn.qin.entity.PartJob;
import cn.qin.entity.PartJobDet;
import cn.qin.entity.Rhesis;
import cn.qin.util.*;
import cn.qin.vo.partJob.PartJobVo;
import cn.qin.vo.partJobDet.PartJobDetVo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class JsoupService {

    @Autowired
    private PartJobRepository partJobRepository;
    @Autowired
    private PartJobDetRepository partJobDetRepository;
    /*抓取新安人才网的兼职界面*/
    public RestResponse insertPartJob(){
        //358
        for (int i = 1; i < 4; i++) {
//            String text = "https://parttime.goodjobs.cn/index.php/action/JobSearch?&cepage=solrpcparttime1588233089ZHJ3sy&page="+i ;
            String text = "https://parttime.goodjobs.cn/index.php/action/JobSearch?boxft=s7777&cepage=solrpcparttime1588294813XOfn6V&page="+i ;

            String  respon =  HttpClientUtil.doGet(text);
            Document doc = Jsoup.parse(respon);//解析HTML字符串返回一个Document实现
            Elements elementsByClass = doc.getElementsByClass("pjobList");
            if (ArrayUtils.isNotNullAndLengthNotZero(elementsByClass)){
                for (Element sub : elementsByClass) {

                    PartJobVo partJobVo = new PartJobVo();

                    PartJobDetVo partJobDetVo = new PartJobDetVo();

                    //获取图片与ID
                    Elements childs = sub.getElementsByClass("qiye_logo");
                    Element subimg = childs.get(0);
                    if (StringUtils.isNotTrimBlank(subimg.attr("href"))){
                        String s1 =  subimg.attr("href");
                        String[] list1 = s1.split("&");
                        partJobVo.setPartJobId(list1[0].substring(16));//公司id
                        partJobDetVo.setPartJobId(list1[0].substring(16));
                        partJobVo.setSType(list1[1].substring(2));//s类型
                    }

                    Element img = subimg.getElementsByTag("img").get(0);
                    if (StringUtils.isNotTrimBlank(img.attr("src"))){
                        partJobVo.setCompanyIcon(img.attr("src"));//公司名称
                    }



                    Elements subDiv = sub.getElementsByClass("zhiwei_b_p");
                    String title = subDiv.get(0).attr("title");
                    partJobVo.setJobName(title);//工作名称
                    if (ArrayUtils.isNotNullAndLengthNotZero(sub.getElementsByClass("zhiwei_b_pimg"))){
                        partJobVo.setHot("1");
                    }
                    partJobVo.setType("其他");
                    String[] xinzideng =  sub.getElementsByTag("li").text().split(" ");
                    partJobDetVo.setSalary(xinzideng[0]);
                    partJobDetVo.setSettleType(xinzideng[2]);
                    partJobDetVo.setPersonNum(xinzideng[4]);

                    String company  = sub.getElementsByClass("zhiwei_b_pa").get(0).attr("title");
                    partJobVo.setCompanyName(company);

                    Elements workTimeAddr = sub.getElementsByClass("zhiwei_c");
                    Elements plist = workTimeAddr.get(0).getElementsByTag("p");
                    partJobDetVo.setWorkTime(plist.get(0).text());
                    partJobDetVo.setJobAddr(plist.get(1).text());

                    //没有的数据再插入
                    PartJob partJob =  partJobRepository.selectByPrimaryKey(partJobVo.getPartJobId());
                    if (partJob == null){
                        partJobRepository.insertData(partJobVo.getEntity());
                        partJobDetRepository.insertData(partJobDetVo.getEntity());
                    }


                }
            }
        }
        return RestResponseGenerator.genSuccessResponse();
    }

    public RestResponse getPartDetail(){
        List<PartJob> partJobs =  partJobRepository.selectAll();

        for (PartJob subPart:partJobs) {
            Example example = SqlUtil.newExample(PartJobDet.class);
            example.createCriteria().andEqualTo("partJobId",subPart.getPartJobId());
            PartJobDet partJobDet = partJobDetRepository.selectOneByExample(example);
            if (StringUtils.isTrimBlank(partJobDet.getContent())){
                String text = "https://parttime.goodjobs.cn/show.php?jobID=" + subPart.getPartJobId() +"&s=" + subPart.getSType();
                String  respon =  HttpClientUtil.doGet(text);
                Document doc = Jsoup.parse(respon);//解析HTML字符串返回一个Document实现

                Elements welfare = doc.getElementsByClass("welfare");

                if (ArrayUtils.isNotNullAndLengthNotZero(welfare)){
                    String tags = "";
                    for (Element element:welfare) {
                        tags = tags  +  element.text() + ",";
                    }
                    partJobDet.setTags(tags);
                }

                Elements inconbg_ic =  doc.getElementsByClass("inconbg_ic");
                if (ArrayUtils.isNotNullAndLengthNotZero(inconbg_ic)){
                    partJobDet.setJobTime(inconbg_ic.get(0).text());
                }

                Elements endtimes  = doc.getElementsByClass("cjz-d-l-li").get(0).getElementsByTag("li");

                for (Element subEle:endtimes) {
                    if (ArrayUtils.isNotNullAndLengthNotZero(subEle.select(".incon.bg1"))){
                        partJobDet.setWorkTime(subEle.text());

                    }else  if (ArrayUtils.isNotNullAndLengthNotZero(subEle.select(".incon.bg3"))){
                        partJobDet.setApplyEndTime(subEle.text());
                    }
                }

                List<TextNode> textNodes =  doc.getElementsByClass("cjz-d-l-li").get(1).textNodes();
                String content = "";
                for (int i = 0; i < textNodes.size(); i++) {
                    content = content + textNodes.get(i).text()  + "\n";
                }
                partJobDet.setContent(content);
                partJobDetRepository.updateByPrimaryKeySelectiveData(partJobDet);
            }
        }
        return RestResponseGenerator.genSuccessResponse();

    }
}
