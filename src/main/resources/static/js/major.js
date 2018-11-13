 var curIndex = 0, //当前index
      imgLen = $("#imgList li").length; //图片总数
     // 定时器自动变换3.5秒每次
  var autoChange = setInterval(function(){ 
    if(curIndex < imgLen-1){ 
      curIndex ++; 
    }else{ 
      curIndex = 0;
    }
    //调用变换处理函数
    changeTo(curIndex); 
  },3500);
 
  $("#indexList").find("li").each(function(item){ 
    $(this).hover(function(){ 
      clearInterval(autoChange);
      changeTo(item);
      curIndex = item;
    },function(){ 
      autoChangeAgain();
    });
  });
  //清除定时器时候的重置定时器--封装
  function autoChangeAgain(){ 
      autoChange = setInterval(function(){ 
      if(curIndex < imgLen-1){ 
        curIndex ++;
      }else{ 
        curIndex = 0;
      }
    //调用变换处理函数
      changeTo(curIndex); 
    },3500);
    }
  function changeTo(num){ 
    var goLeft = num * 430;
    $("#imgList").animate({left: "-" + goLeft + "px"},500);
    $("#infoList").find("li").removeClass("infoOn").eq(num).addClass("infoOn");
    $("#indexList").find("li").removeClass("indexOn").eq(num).addClass("indexOn");
  }