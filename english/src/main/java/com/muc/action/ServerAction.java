package com.muc.action;

import com.muc.bean.OutlineVocabularyKey;
import com.muc.bean.TestWordCount;
import com.muc.service.PredictService;
import com.muc.service.VocabularyService;
import com.muc.service.WordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/server")
public class ServerAction {
    @Resource
    WordService wordService;
    @Resource
    VocabularyService vocabularyService;
    @Resource
    PredictService predictService;
    @GetMapping("/predict")
    @ApiOperation(value = "计算并预测单词")
    public int predictWord(int type,String password)
    {
        if(!password.equals("wangyun"))
        {
            return 0;
        }
        //获取大纲单词
        try {
            ArrayList<OutlineVocabularyKey> outlineWords = vocabularyService.getVocabulary(type);
            return predictService.pridict(outlineWords,type);

        }
        catch (Exception e)
        {

        return 0;
        }

    }
    @GetMapping("/deleteUseless")
    @ApiOperation(value = "删除没有用的脏数据")
    public int deleteUselessWord(String password)
    {
        if(!password.equals("wangyun"))
            return -1;
        int j=0;



                j+=wordService.delete();

                j+=predictService.delete();

                j+=vocabularyService.delete();




        return j;

    }
}
