package cn.qin.controller;

import cn.qin.base.response.ResponseEnums;
import cn.qin.base.response.RestResponse;
import cn.qin.base.response.RestResponseGenerator;
import cn.qin.entity.CollectionData;
import cn.qin.service.CollectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value = "saveCollection",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> saveCollection(@RequestBody CollectionData collectionData){
        collectionDataService.saveCollection(collectionData);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(ResponseEnums.SUCCESS.getMark()), HttpStatus.OK);
    }


    /**
     * @Title:获取收藏
     * @param
     */
    @RequestMapping(value = "findCollectionDataList",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> findCollectionDataList(@RequestParam("type")String type){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(collectionDataService.findCollectionDataList(type)), HttpStatus.OK);
    }

    /**
     * @Title:取消收藏
     * @param
     */
    @RequestMapping(value = "deleteCollectionData",method = RequestMethod.GET)
    public ResponseEntity<RestResponse> deleteCollectionData(@RequestParam("collectionId")String collectionId){
        collectionDataService.deleteCollectionData(collectionId);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(ResponseEnums.SUCCESS.getMark()), HttpStatus.OK);
    }

}
