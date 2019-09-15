package cn.qin.controller;

import cn.qin.base.response.ResponseEnums;
import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.entity.CollectionData;
import cn.qin.service.CollectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collection")
public class CollectionDataController {

    @Autowired
    private CollectionDataService collectionDataService;
    /**
     * @Title:添加收藏
     * @param
     */
    /**
     * @Title:根据Tag获取列表
     * @param collectionData
     */
    @RequestMapping(value = "saveCollectionById",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> saveCollectionById(@RequestBody CollectionData collectionData){
        collectionDataService.saveCollectionById(collectionData);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(ResponseEnums.SUCCESS.getMark()), HttpStatus.OK);
    }
}
