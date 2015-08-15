function turn(url){
	window.location.href = url;
}

function icon(i){
	switch (i) {
	case 1:
		$(".mhead img").attr("src", "/resources/images/transparent.gif");
		$("#u3_img").attr("src", "/images/manage/u3.png");
		$(".mhead").css("color","#999999");
		$("#u152.text").css("color","#999999");
		$("#u3.mhead").css("color","#FFFFFF");
		break;
	case 2:
		$(".mhead img").attr("src", "/resources/images/transparent.gif");
		$("#u6_img").attr("src", "/images/manage/u3.png");

		$(".mhead").css("color","#999999");
		$("#u152.text").css("color","#999999");
		$("#u6.mhead").css("color","#FFFFFF");
		break;

	case 3:
		$(".mhead img").attr("src", "/resources/images/transparent.gif");
		$("#u9_img").attr("src", "/images/manage/u3.png");
		$(".mhead").css("color","#999999");
		$("#u152.text").css("color","#999999");
		$("#u9.mhead").css("color","#FFFFFF");
		
		break;

	case 4:
		$(".mhead img").attr("src", "/resources/images/transparent.gif");
		$("#u12_img").attr("src", "/images/manage/u3.png");

		$(".mhead").css("color","#999999");
		$("#u152.text").css("color","#999999");
		$("#u12.mhead").css("color","#FFFFFF");
		break;

	case 5:
		$(".mhead img").attr("src", "/resources/images/transparent.gif");
		$("#u15_img").attr("src", "/images/manage/u3.png");

		$(".mhead").css("color","#999999");
		$(".mhead").css("color","#999999");
		$("#u152.text").css("color","#FFFFFF");
		break;

	default:
		break;
	}
	
	$("#u1_img").attr("src","/images/manage/u1.png");
}