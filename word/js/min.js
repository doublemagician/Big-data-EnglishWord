$(function() {
	
	function getParameter(param) {
                  var query = window.location.search; //获取URL地址中？后的所有字符  
                 var iLen = param.length; //获取你的参数名称长度  
                 var iStart = query.indexOf(param); //获取你该参数名称的其实索引  
                 if(iStart == -1) //-1为没有该参数  
                     return "";
                 iStart += iLen + 1;
                 var iEnd = query.indexOf("&", iStart); //获取第二个参数的其实索引  
                if(iEnd == -1) //只有一个参数  
                    return query.substring(iStart); //获取单个参数的参数值  
                 return query.substring(iStart, iEnd); //获取第二个参数的值               
			  }
            function getword() {
                 var param = getParameter("word");  //这个值是传来的查询词频的单词,根据这个值写ajax代码
				return param;
            
             }
	var theword=getword();
	$("#tname").append(theword)
	console.log(theword);
	
	function Column1() {
		 $.ajax({
                  url: "http://120.26.184.150/english/lineChart?type=1&word="+theword,
                  type: "GET",
				  async:false,
                  dataType: "JSON",
				  headers:{
					  "Content-Type":"application/json"
				  },
                  success: function(data){
				  console.log(data);
					  data=data.lineChartData;
					  var data1=[];
					  var data2=[];
					  for(var i=0;i<data.length;i++)
						  {
							  data1[i]=data[i][0];
							  data2[i]=data[i][1];
						  }
					  console.log(data1);
                     var myChart = echarts.init(document.getElementById('min1'));
        // 指定图表的配置项和数据
        var option = {
		    title : {
                text: '四级考试',
                subtext: '',
                y: '10',
                x:'center'
            },
            grid:{
                x:60,
                y:50,
                x2:40,
                y2:50,
            },
		    tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器,坐标轴触发有效
                    type : 'shadow'        // 默认为直线,可选为：'line' | 'shadow'
                }
            },
            xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data:data1
            },
		    yAxis: {
		        min:0,
				max:40,
				interval:8,
		    },
		    series: [
		        {
		            type:'line',
		            data:data2,
		        }
		    ]
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
                  }
             });
    	
    }/*
	function Column1() {
    	var myChart = echarts.init(document.getElementById('min1'));
        // 指定图表的配置项和数据
        var option = {
		    title : {
                text: '四级考试',
                subtext: '',
                y: '10',
                x:'center'
            },
            grid:{
                x:60,
                y:50,
                x2:40,
                y2:50,
            },
		    tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器,坐标轴触发有效
                    type : 'shadow'        // 默认为直线,可选为：'line' | 'shadow'
                }
            },
           xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data: ['2011年6月','2011年9月','2012年6月 ','2012年9月','2013年6月','2013年9月','2014年6月','2014年9月']
            },
		    yAxis: {
		        min:0,
				max:25,
				interval:4,
		    },
		    series: [
		        {
		            type:'line',
		            data:[12,4,20,14,11,13,4,8],
		        }
		    ]
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }*/
    function Column2() {
		 $.ajax({
                  url: "http://120.26.184.150/english/lineChart?type=2&word="+theword,
                  type: "GET",
				  async:false,
                  dataType: "JSON",
				  headers:{
					  "Content-Type":"application/json"
				  },
                  success: function(data){
				  console.log(data);
					  data=data.lineChartData;
					  var data1=[];
					  var data2=[];
					  for(var i=0;i<data.length;i++)
						  {
							  data1[i]=data[i][0];
							  data2[i]=data[i][1];
						  }
					  console.log(data1);
                     var myChart = echarts.init(document.getElementById('min2'));
        // 指定图表的配置项和数据
        var option = {
		    title : {
                text: '六级考试',
                subtext: '',
                y: '10',
                x:'center'
            },
            grid:{
                x:60,
                y:50,
                x2:40,
                y2:50,
            },
		    tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器,坐标轴触发有效
                    type : 'shadow'        // 默认为直线,可选为：'line' | 'shadow'
                }
            },
            xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data:data1
            },
		    yAxis: {
		        min:0,
				max:40,
				interval:8,
		    },
		    series: [
		        {
		            type:'line',
		            data:data2,
		        }
		    ]
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
                  }
             });
    	
    }
     function Column3() {
		 $.ajax({
                  url: "http://120.26.184.150/english/lineChart?type=3&word="+theword,
                  type: "GET",
				  async:false,
                  dataType: "JSON",
				  headers:{
					  "Content-Type":"application/json"
				  },
                  success: function(data){
				  console.log(data);
					  data=data.lineChartData;
					  var data1=[];
					  var data2=[];
					  for(var i=0;i<data.length;i++)
						  {
							  data1[i]=data[i][0];
							  data2[i]=data[i][1];
						  }
					  console.log(data1);
                     var myChart = echarts.init(document.getElementById('min3'));
        // 指定图表的配置项和数据
        var option = {
		    title : {
                text: '考研',
                subtext: '',
                y: '10',
                x:'center'
            },
            grid:{
                x:60,
                y:50,
                x2:40,
                y2:50,
            },
		    tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器,坐标轴触发有效
                    type : 'shadow'        // 默认为直线,可选为：'line' | 'shadow'
                }
            },
            xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data:data1
            },
		    yAxis: {
		        min:0,
				max:40,
				interval:8,
		    },
		    series: [
		        {
		            type:'line',
		            data:data2,
		        }
		    ]
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
                  }
             });
    	
    }
     function Column4() {
		 $.ajax({
                  url: "http://120.26.184.150/english/lineChart?type=4&word="+theword,
                  type: "GET",
				  async:false,
                  dataType: "JSON",
				  headers:{
					  "Content-Type":"application/json"
				  },
                  success: function(data){
				  console.log(data);
					  data=data.lineChartData;
					  var data1=[];
					  var data2=[];
					  for(var i=0;i<data.length;i++)
						  {
							  data1[i]=data[i][0];
							  data2[i]=data[i][1];
						  }
					  console.log(data1);
                     var myChart = echarts.init(document.getElementById('min4'));
        // 指定图表的配置项和数据
        var option = {
		    title : {
                text: '高考',
                subtext: '',
                y: '10',
                x:'center'
            },
            grid:{
                x:60,
                y:50,
                x2:40,
                y2:50,
            },
		    tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器,坐标轴触发有效
                    type : 'shadow'        // 默认为直线,可选为：'line' | 'shadow'
                }
            },
            xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data:data1
            },
		    yAxis: {
		        min:0,
				max:40,
				interval:8,
		    },
		    series: [
		        {
		            type:'line',
		            data:data2,
		        }
		    ]
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
                  }
             });
    	
    }
    function Column5() {
		 $.ajax({
                  url: "http://120.26.184.150/english/lineChart?type=5&word="+theword,
                  type: "GET",
				  async:false,
                  dataType: "JSON",
				  headers:{
					  "Content-Type":"application/json"
				  },
                  success: function(data){
				  console.log(data);
					  data=data.lineChartData;
					  var data1=[];
					  var data2=[];
					  for(var i=0;i<data.length;i++)
						  {
							  data1[i]=data[i][0];
							  data2[i]=data[i][1];
						  }
					  console.log(data1);
                     var myChart = echarts.init(document.getElementById('min5'));
        // 指定图表的配置项和数据
        var option = {
		    title : {
                text: '托福',
                subtext: '',
                y: '10',
                x:'center'
            },
            grid:{
                x:60,
                y:50,
                x2:40,
                y2:50,
            },
		    tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器,坐标轴触发有效
                    type : 'shadow'        // 默认为直线,可选为：'line' | 'shadow'
                }
            },
            xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data:data1
            },
		    yAxis: {
		        min:0,
				max:40,
				interval:8,
		    },
		    series: [
		        {
		            type:'line',
		            data:data2,
		        }
		    ]
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
                  }
             });
    	
    }
  
 function Column6() {
		 $.ajax({
                  url: "http://120.26.184.150/english/lineChart?type=6&word="+theword,
                  type: "GET",
				  async:false,
                  dataType: "JSON",
				  headers:{
					  "Content-Type":"application/json"
				  },
                  success: function(data){
				  console.log(data);
					  data=data.lineChartData;
					  var data1=[];
					  var data2=[];
					  for(var i=0;i<data.length;i++)
						  {
							  data1[i]=data[i][0];
							  data2[i]=data[i][1];
						  }
					  console.log(data1);
                     var myChart = echarts.init(document.getElementById('min6'));
        // 指定图表的配置项和数据
        var option = {
		    title : {
                text: '雅思',
                subtext: '',
                y: '10',
                x:'center'
            },
            grid:{
                x:60,
                y:50,
                x2:40,
                y2:50,
            },
		    tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器,坐标轴触发有效
                    type : 'shadow'        // 默认为直线,可选为：'line' | 'shadow'
                }
            },
            xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data:data1
            },
		    yAxis: {
		        min:0,
				max:40,
				interval:8,
		    },
		    series: [
		        {
		            type:'line',
		            data:data2,
		        }
		    ]
		};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
                  }
             });
    	
    }	
	
    Column1();
    Column2();
    Column3();
    Column4();
    Column5();
	 Column6();
});