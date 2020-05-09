package cn.qin.controller;

import cn.qin.base.response.RestResponse;
import cn.qin.service.PomeService;
import cn.qin.vo.pomeVo.PomeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("pome")
public class PomeController {
    @Autowired
    private PomeService pomeService;

    /**
     * @Title:搜索诗词
     * @param searchText 参数
     */
    @RequestMapping(value = "findPomeBySearchText",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeBySearchText(@RequestParam("searchText") String searchText){
        return new ResponseEntity<RestResponse>(pomeService.findPomeBySearchText(searchText), HttpStatus.OK);
    }

    /**
     * @Title:根据id获取古诗
     * @Description:
     */
    @RequestMapping(value = "findPomeDetailById",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeById(@RequestParam("pomeId") String pomeId){
        return new ResponseEntity<RestResponse>(pomeService.findPomeDetailById(pomeId), HttpStatus.OK);
    }

    /**
     * @Title:获取某个作者的诗文列表
     * @param
     */
    @RequestMapping(value = "findPomeListByPage",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findPomeByPage(@RequestBody  PomeVo pomeVo){
        return new ResponseEntity<RestResponse>(pomeService.findPomeListByPage(pomeVo), HttpStatus.OK);
    }


    /**
     * @Title:随机获取诗文
     * @param
     */
    @RequestMapping(value = "findRandomPomeForSize",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findRandomPomeForSize(String size){
        return new ResponseEntity<RestResponse>(pomeService.findRandomPomeForSize(size), HttpStatus.OK);
    }

    /**
     * @Title:每日一首
     * @param
     */
    @RequestMapping(value = "findPomeDaily",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findPomeDaily(){
        return new ResponseEntity<RestResponse>(pomeService.findPomeDaily(), HttpStatus.OK);
    }

    /**
     * @Title:获取有作品的诗人
     * @param
     */
    @RequestMapping(value = "selectAuthorHasPome",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> selectAuthorHasPome(){
        return new ResponseEntity<RestResponse>(pomeService.selectAuthorHasPome(), HttpStatus.OK);
    }

    /**
     * @Title:获取全部的诗人根据姓名首字母分组
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
