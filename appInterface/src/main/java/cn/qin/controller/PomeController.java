package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.service.PomeService;
import cn.qin.vo.pomeVo.PomeAboutVo;
import cn.qin.vo.pomeVo.PomeVo;
import cn.qin.vo.rhesisVo.RhesisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("pome")
public class PomeController {
    @Autowired
    private PomeService pomeService;

    /**
     * @Description  搜索信息包括作者、诗词、名句--> 每个获取5条
     * @param searchText 搜做的内容 添加通配符
     * @return
     */
    @RequestMapping(value = "findPomeBySearchText",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeBySearchText(@RequestParam("searchText") String searchText){
        return new ResponseEntity<RestResponse>(pomeService.findPomeBySearchText(searchText), HttpStatus.OK);
    }

    /**
     * @Description  搜索信息包括作者、诗词、名句 单独获取全部的相关信息
     * @param pomeAboutVo searchType（pome、author、rhesis） 搜索类型 text搜索的内容
     * @return
     */
    @RequestMapping(value = "findInfoAboutSearchText",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findInfoAboutSearchText(@RequestBody PomeAboutVo pomeAboutVo){
        return new ResponseEntity<RestResponse>(pomeService.findInfoAboutSearchText(pomeAboutVo), HttpStatus.OK);
    }



    /**
     * @Description:根据pomeid获取古诗详情
     */
    @RequestMapping(value = "findPomeDetailById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeById(@RequestParam("pomeId") String pomeId){
        return new ResponseEntity<RestResponse>(pomeService.findPomeDetailById(pomeId), HttpStatus.OK);
    }

    /**
     * @Description:获取某个作者的诗文列表
     * @param pomeVo authorId或者authorName
     */
    @RequestMapping(value = "findPomeListPageByAuthor",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findPomeListPageByAuthor(@RequestBody  PomeVo pomeVo){
        return new ResponseEntity<RestResponse>(pomeService.findPomeListPageByAuthor(pomeVo), HttpStatus.OK);
    }


    /**
     * @Title:按类型随机获取一定条数诗文
     * @param size 默认15条数 type类型
     */
    @RequestMapping(value = "findRandomPomeForSize",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findRandomPomeForSize(String size,String type){
        return new ResponseEntity<RestResponse>(pomeService.findRandomPomeForSize(size,type), HttpStatus.OK);
    }


    /**
     * @Title:分页获取诗文列表
     * @param
     */
    @RequestMapping(value = "findPomeListByPage",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findPomeListByPage(@RequestBody  PomeVo pomeVo){
        return new ResponseEntity<RestResponse>(pomeService.findPomeListByPage(pomeVo), HttpStatus.OK);
    }

    /**
     * @Title:分页获取名句列表
     * @param rhesisVo rand 空 分页获取 。 不为空  则不分页随机获取15条
     */
    @RequestMapping(value = "findRhesisListByPage",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findRhesisListByPage(@RequestBody RhesisVo rhesisVo){
        return new ResponseEntity<RestResponse>(pomeService.findRhesisListByPage(rhesisVo), HttpStatus.OK);
    }


    /**
     * @Title:每日一首唐诗词
     * @param
     */
    @RequestMapping(value = "findPomeDaily",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeDaily(){
        return new ResponseEntity<RestResponse>(pomeService.findPomeDaily(), HttpStatus.OK);
    }

    /**
     * @Title:获取有作品的诗人列表
     * @param
     */
    @RequestMapping(value = "selectAuthorHasPome",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> selectAuthorHasPome(){
        return new ResponseEntity<RestResponse>(pomeService.selectAuthorHasPome(), HttpStatus.OK);
    }

    /**
     * @Title:获取有作品的诗人根据姓名首字母分组
     * @param
     */
    @RequestMapping(value = "findAuthorBySort",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findAuthorBySort(){
        return new ResponseEntity<RestResponse>(pomeService.findAuthorBySort(), HttpStatus.OK);
    }

    /**
     * @Title:获取诗人介绍
     * @param
     */
    @RequestMapping(value = "findAuthorById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findAuthorById(@RequestParam("authorId") String authorId){
        return new ResponseEntity<RestResponse>(pomeService.findAuthorById(authorId), HttpStatus.OK);
    }


    /**
     * @Title:通过接口获取 图片数据
     * @param
     */
    @GetMapping(value = "/image",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> test(String userId) throws Exception {

        File file = new File("/Users/leaduadmin/Desktop/authorIcon/" + userId +".jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return new ResponseEntity(bytes, HttpStatus.OK);

    }

}
