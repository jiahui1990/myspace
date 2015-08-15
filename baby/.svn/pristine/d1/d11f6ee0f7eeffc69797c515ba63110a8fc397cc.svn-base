$(function(){
	var cus = 0;
    var classname = "";
    var arry = new Array();
    var valueArr = new Array();
    var $autocomplete = $("<ul class='autocomplete'></ul>").hide().insertAfter("#box4");
    var selectEle = $("#select_kin");
    selectEle.find("option").each(function(i, n) {
        arry[i] = $(this).text();
        valueArr[i] = $(this).val();
    });
    $("#box4").keyup(function(event) {
        if ((event.keyCode != 38) && (event.keyCode != 40) && (event.keyCode != 13)) {
            $autocomplete.empty();
            var $SerTxt = $("#box4").val().toLowerCase();
            if ($SerTxt != "" && $SerTxt != null) {
                for (var k = 0; k < arry.length; k++) {
                    if (arry[k].toLowerCase().indexOf($SerTxt) >= 0) {
                        $("<li title='" + arry[k] + "' class='" + classname + "' data_val='"+valueArr[k]+"'></li>").text(arry[k]).appendTo($autocomplete).mouseover(function() {
                            $(".autocomplete li").removeClass("hovers");
                            $(this).addClass("hovers");
                        }).mouseout(function() {
                            $(this).removeClass("hovers");
                        })/*.click(function() {
                        	alert(1);
                            $("#box4").val($(this).text());
                            $autocomplete.hide();
                            selectEle.val($(this).attr("data_val"));
                            selectEle.change();
                        })*/.mousedown(function(){
                            $("#box4").val($(this).text());
                            $autocomplete.hide();
                            selectEle.val($(this).attr("data_val"));
                            selectEle.change();
                        });
                    }
                }
            }
            
            if($(".autocomplete li").size()>0){
            	$autocomplete.show();
            }else{
            	$autocomplete.hide();
            }
        }
        var listsize = $(".autocomplete li").size();
        $(".autocomplete li").eq(0).addClass("hovers");
        if (event.keyCode == 38) {
            if (cus < 1) {
                cus = listsize - 1;
                $(".autocomplete li").removeClass("hovers");
                $(".autocomplete li").eq(cus).addClass("hovers");
                var text = $(".autocomplete li").eq(cus).text();
                $("#box4").val(text);
            } else {
                cus--;
                $(".autocomplete li").removeClass("hovers");
                $(".autocomplete li").eq(cus).addClass("hovers");
                var text = $(".autocomplete li").eq(cus).text();
                $("#box4").val(text);
            }
        }
        if (event.keyCode == 40) {
            if (cus < (listsize - 1)) {
                cus++;
                $(".autocomplete li").removeClass("hovers");
                $(".autocomplete li").eq(cus).addClass("hovers");
                var text = $(".autocomplete li").eq(cus).text();
                $("#box4").val(text);
            } else {
                cus = 0;
                $(".autocomplete li").removeClass("hovers");
                $(".autocomplete li").eq(cus).addClass("hovers");
                var text = $(".autocomplete li").eq(cus).text();
                $("#box4").val(text);
            }
        }
        if (event.keyCode == 13) {
            $(".autocomplete li").removeClass();
            /*$("#HTML").html("你选择的是<font color='red'>" + $(".autocomplete li").eq(cus).text()+"</font>");*/
            $autocomplete.hide();
            selectEle.val($(".autocomplete li").eq(cus).attr("data_val"));
            selectEle.change();
        }
    }).blur(function() {
        /*setTimeout(function() {*/
            $autocomplete.hide();
            $(this).val(selectEle.find("option:selected").text());
            /*$(this).hide();*/
        /*},
        1000);*/
    });
});	