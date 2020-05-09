package cn.qin.controller;


import cn.qin.base.response.RestResponse;
import cn.qin.service.JsoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jsoup")
public class JsoupController {
    @Autowired
    private JsoupService jsoupService;

    /*抓取新安人才网的兼职界面*/
    @RequestMapping(value = "insertPartJob",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> insertPartJob(){
        return new ResponseEntity<RestResponse>(jsoupService.insertPartJob(), HttpStatus.OK);
    }

    @RequestMapping(value = "getPartDetail",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> getPartDetail(){
        return new ResponseEntity<RestResponse>(jsoupService.getPartDetail(), HttpStatus.OK);
    }

    @RequestMapping(value = "getAuthorIconImg",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> getAuthorIconImg(){
        return new ResponseEntity<RestResponse>(jsoupService.getAuthorIconImg(), HttpStatus.OK);
    }

}
