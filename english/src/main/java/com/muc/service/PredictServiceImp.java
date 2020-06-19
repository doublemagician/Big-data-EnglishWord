package com.muc.service;

import com.muc.bean.OutlineVocabularyKey;
import com.muc.bean.PredictWord;
import com.muc.bean.TestWordCount;
import com.muc.mapper.PredictWordMapper;
import com.muc.util.Slope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
@Service
public class PredictServiceImp implements  PredictService{
    @Resource WordService wordService;
    @Resource
    PredictWordMapper predictWordMapper;
    @Override
    public int pridict(ArrayList<OutlineVocabularyKey> words, int type) {
        int end=0;
        for(OutlineVocabularyKey t:words) {
            ArrayList<TestWordCount> originalData = null;
            //获取单词数据
            try {
                originalData = wordService.wordCountPreYear(t.getWord(), type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<int[]> lineChartData = new ArrayList<>();
            double slope=0;
            //生成曲线图所需的的数据格式
            int count=0;
            for(TestWordCount i:originalData)
            {
                count+=i.getCount();
            }
            if(originalData.size()>1) {

                for (TestWordCount i : originalData) {
                    int temp[] = new int[2];
                    try {
                        temp[0] = Integer.parseInt(i.getTestTime());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    temp[1] = i.getCount();
                    lineChartData.add(temp);
                }

                //计算单词的斜率
              slope = Slope.getslope(lineChartData);

            }
            else
            {
                slope=-1.0;

            }
            double rate=slope*t.getWord().length()/10+Double.valueOf(count)/words.size()*10;
            System.out.println("word:"+t.getWord()+"   type:"+type+ "   rate:"+rate);


            //存入数据库
            if(rate>0)
            {
                try {
                    PredictWord predictWord = new PredictWord();
                    predictWord.setProbability(BigDecimal.valueOf(rate));
                    predictWord.setTestType(type);
                    predictWord.setWord(t.getWord());
                    predictWordMapper.insert(predictWord);
                    end++;
                }
                catch (Exception e)
                {
                    continue;
                }
            }
        }

        return end;
    }

    @Override
    public ArrayList<PredictWord> getPredcitWord(int type) {
        return predictWordMapper.selectByType(type);
    }

    @Override
    public int delete() {
        return predictWordMapper.deleteByWord();
    }
}
