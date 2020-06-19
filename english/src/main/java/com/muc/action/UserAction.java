package com.muc.action;

import com.muc.bean.*;
import com.muc.service.PredictService;
import com.muc.service.TypeService;
import com.muc.service.VocabularyService;
import com.muc.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@RequestMapping("/english")
@CrossOrigin
@RestController
public class UserAction {
    @Resource
    WordService wordService;
    /*
    * @Author Wangyun
    * @Date 2020/6/10
    * @Parm year 考试日期
    * @Parm type 考试类型
    * */
    @GetMapping("/getWordCloud")
    @ApiOperation(value = "查询某年的考试词云",notes = "year参数为4位数的字符串，前两位表示年份后两位表示月份，类型有cet4，cet6，雅思，托福等"
    )
    public HashMap<String,Object> getWordCloud(String year,String type,Integer limit)
    {
        HashMap map=new HashMap<String,Object>();
                    ArrayList data=wordService.getWordCloud(year, type,limit);
                    map.put("result","success");
                    map.put("data",data);
        return  map;
    }

    /*
    *@Author Wangyun
    *@Date 2020/6/10
    *@Description 将下载好的数据，进行处理后导入数据库
    * */
    @PostMapping("/importNativeData")
    @ApiOperation(value = "导入本地数据到数据库",notes = "将下载到本地的数据，进行处理后导入数据库，返回-1表示密码输入错误")
    public int improtNativeWord(String fileName,String time,int type,String password) throws IOException {
        if(!password.equals("wangyun"))
        {
            return -1;
        }
        FileReader fileReader = new FileReader(fileName);
        //使用流的方式读取内容
        BufferedReader reader = new BufferedReader(fileReader);
        //使用TreeMap，它会自动将结果按照字典的顺序排序
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        String readLine = null;
        while((readLine = reader.readLine()) != null){
            //将字母排序为小写
            readLine = readLine.toLowerCase();
            //过滤出只含有字母的字段
            readLine=readLine.replaceAll("[^a-z]"," ");
            String[] str = readLine.split("[\\s]+");
            //过滤掉所有的空格,“+”代表多个的意思。
            for (int i = 0; i < str.length; i++) {//循环统计出现次数
                String word = str[i].trim();
                if (tm.containsKey(word)) {
                    tm.put(word, tm.get(word) + 1);
                } else {
                    tm.put(word, 1);
                }
            }
        }
        System.out.println("按字典序输出为：");
        Iterator<Map.Entry<String, Integer>> it = tm.entrySet().iterator();
        //使用迭代器取值
        int i=0;
        while(it.hasNext()) {
            try {


            Map.Entry<String, Integer> entry = it.next();
            //将结果输出到控制台
            System.out.println(entry.getKey() + "\t" + entry.getValue());

            wordService.insert(new TestWordCount(entry.getKey(),entry.getValue(),type,time));
            i++;
            }
            catch (Exception e)
            {
                continue;
            }
        }
        return i;
    }
    /*
     *@Author Wangyun
     *@Date 2020/6/10
     *@Description 将下载好的数据，进行处理后导入数据库
     * */
    @PostMapping("/importRemoteData")
    @ApiOperation(value = "导入本地数据到数据库",notes = "将上传远程数据，进行处理后导入数据库，返回-1表示密码输入错误")
    public int improtRemoteWord(MultipartFile file, String time, int type,String password) throws IOException {
        if(!password.equals("wangyun"))
        {
            return -1;
        }
        InputStream inputStream= file.getInputStream();
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        try {

            int size=inputStream.available();
            byte b[]=new byte[size];
            inputStream.read(b);
            String text=new String(b);
            System.out.println(text);
            text=text.toLowerCase();
            text=text.replaceAll("[^a-z]"," ");
            String[] str = text.split("[\\s]+");

                for (int i = 0; i < str.length; i++) {//循环统计出现次数
                    String word = str[i].trim();
                    System.out.println(str[i]);
                    if (tm.containsKey(word)) {
                        tm.put(word, tm.get(word) + 1);
                    } else {
                        tm.put(word, 1);
                    }
                }
            System.out.println("按字典序输出为：");
            Iterator<Map.Entry<String, Integer>> it = tm.entrySet().iterator();
            //使用迭代器取值
            int i=0;
            while(it.hasNext()) {
                try {
                    Map.Entry<String, Integer> entry = it.next();
                    //将结果输出到控制台
                    System.out.println(entry.getKey() + "\t" + entry.getValue());

                    wordService.insert(new TestWordCount(entry.getKey(),entry.getValue(),type,time));
                    i++;
                }
                catch (Exception e)
                {
                    continue;
                }

            }
            return i;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    /*
     * @Author Wangyun
     * @Date 2020/6/12
     * @Description 查询某个单词的历年的词频
     * @Param String word 单词
     * @Param int type 考试类型
     * */
    @GetMapping("lineChart")
    @ApiOperation(value = "查询某个单词的一类考试的历年词频")
    public Map<String,Object> getWordCountPreYear(String word,int type)
    {
        HashMap<String,Object> map=new HashMap<String,Object>();
        ArrayList<TestWordCount> originalData=null;
        //获取单词数据
        try {
            originalData=wordService.wordCountPreYear(word,type);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ArrayList<Object> lineChartData=new ArrayList<>();
        //生成曲线图所需的的数据格式
        for (TestWordCount i:originalData)
        {
            int temp[]=new int[2];
            try {
                temp[0] = Integer.parseInt(i.getTestTime());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            temp[1]=i.getCount();
            lineChartData.add(temp);
        }
        //返回结果
        map.put("result","success");
        map.put("word",word);
        map.put("originalData",originalData);
        map.put("lineChartData",lineChartData);

        return map;
    }
    @Resource
    TypeService typeService;
    /*
     * @Author Wangyun
     * @Date 2020/6/12
     * @Description 系统内容分类
     * */
    @GetMapping("/getType")
    @ApiOperation(value = "获取系统的具体分类")
    public Map<String,Object> getType(){
        Map map=new HashMap<String,Object>();
        //考试类型存储
        ArrayList<TestType> testTypes=null;
        //年份存储
        ArrayList<YearType> yearTypes=null;
        //二维分类存储
        ArrayList<Object> typeArray=new ArrayList<Object>();

        try{
            //获取考试类型
            testTypes=typeService.getTestType();
            //获取年份分类
            for(TestType i:testTypes)
            {
                yearTypes=typeService.getYearType(i.getId());
                Object temp[]=new Object[2];
                temp[0]=i.getVal();
                temp[1]=yearTypes;
                typeArray.add(temp);
            }
            //返回成功
            map.put("result","success");
            map.put("typeArray",typeArray);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //返回失败
            map.put("result","fail");
            return map;
        }

        return map;
    }
    @Resource
    VocabularyService vocabularyService;
    /*
     * @Author Wangyun
     * @Date 2020/6/13
     * @Description 上传单词的大纲
     * @Param MultipartFile file 表单文件类型
     * @Param int type 那种类型考试的大纲词汇
     * */
    @PostMapping("/importOutLine")
    @ApiOperation(value = "大纲单词上传",notes = "直接上传文件即可,返回-1表示密码输入错误")
    public int importOutlineVocabulary(MultipartFile file,int type,String password) throws IOException {
        if(!password.equals("wangyun"))
        {
            return -1;
        }
        InputStream inputStream= file.getInputStream();
        ArrayList<OutlineVocabularyKey> temp=new ArrayList<OutlineVocabularyKey>();
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        try {

            int size=inputStream.available();
            byte b[]=new byte[size];
            inputStream.read(b);
            String text=new String(b);
            System.out.println(text);
            //text=text.toLowerCase();
            text=text.replaceAll("[^a-z]"," ");
            String[] str = text.split("[\\s]+");

            for (int i = 0; i < str.length; i++) {//循环统计出现次数
                String word = str[i].trim();
                System.out.println(str[i]);
                if (tm.containsKey(word)) {
                    tm.put(word, tm.get(word) + 1);
                } else {
                    tm.put(word, 1);
                }
            }
            //使用迭代器取值
            Iterator<Map.Entry<String, Integer>> it = tm.entrySet().iterator();
            int i=0;
            while(it.hasNext()) {
                try {
                    Map.Entry<String, Integer> entry = it.next();
                    //将结果输出到控制台
                    OutlineVocabularyKey t=new OutlineVocabularyKey();
                    t.setWord(entry.getKey());
                    t.setTestType(type);
                    vocabularyService.insert(t);

                    i++;
                }
                catch (Exception e)
                {
                    continue;
                }

            }
            return i;
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    /*
     * @Author Wangyun
     * @Date 2020/6/13
     * @Description 查询大纲词汇
     * @Param MultipartFile file 表单文件类型
     * @Param int type 那种类型考试的大纲词汇
     * */
    @GetMapping("/getOutLineVocabulary")
    @ApiOperation(value = "大纲单词查询",notes = "通过考试类型查询大纲单词")
    public Map getOutlineVocabulary(int type)
    {
        HashMap<String,Object> map =new HashMap<>();
        try{
            ArrayList<TestType> types=typeService.getTestType();
            String typeString="";
            for(TestType i:types)
            {
                if(i.getId()==type)
                    typeString=i.getVal();
            }
            ArrayList<OutlineVocabularyKey> data= vocabularyService.getVocabulary(type);
            map.put("result","success");
            map.put("type",typeString);
            map.put("records",data.size());
            map.put("data",data);
        }catch(Exception e)
        {
            map.put("result","fail");
        }
        return map;
    }
    @Resource
    PredictService predictService;
    /*
    * @Author Wangyun
    * @Date 2020/6/14
    * @Desc 根据考试类型获取预测单词
    * @Param int type 考试类型，具体值擦好看数据库
     * */
    @GetMapping("/predictWords")
    @ApiOperation(value = "根据考试类型获取预测单词")
    public Map getPredictWords(int type){

        HashMap<String,Object> map=new HashMap<>();
        ArrayList<PredictWord> data=new ArrayList<>();
        try{
            //查询数据
         data= predictService.getPredcitWord(type);
         map.put("result","success");
         map.put("data",data);
        }
        catch (Exception e)
        {
            map.put("result","fail");
            map.put("data",data);
        }
        return map;
    }

}
