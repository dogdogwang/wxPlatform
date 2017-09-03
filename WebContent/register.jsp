<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
   
 <!--支持国产浏览器的高速渲染-->
 <meta name="renderer" content="Webkit">

<title>登录</title>
<script src="${pageContext.request.contextPath}/resources/jquery-1.11.3.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>  
<script src="${pageContext.request.contextPath}/resources/bootstrapValidator.min.js"></script> 

<script type="text/javascript">

$(function(){
	$("#registerForm").bootstrapValidator({
		   message:'This value is not valid',
		//   定义未通过验证的状态图标
		   feedbackIcons: {/*输入框不同状态，显示图片的样式*/
		    valid: 'glyphicon glyphicon-ok',
		    invalid: 'glyphicon glyphicon-remove',
		    validating: 'glyphicon glyphicon-refresh'
		   },//   字段验证
		   fields:{
//			    用户名
			    username:{
			     message:'用户名非法',
			     validators:{
//			      非空
			      notEmpty:{
			       message:'用户名不能为空'
			      },
//			      限制字符串长度
			      stringLength:{
			       min:3,
			       max:20,
			       message:'用户名长度必须位于3到20之间'
			      },
//			      基于正则表达是的验证
			      regexp:{
			       regexp:/^[a-zA-Z0-9_\.]+$/,
			       message:'用户名由数字字母下划线和.组成'
			      }
			     }
			    },
			 
//			    密码
			    password:{
			     message:'密码非法',
			     validators:{
			      notEmpty:{
			       message:'密码不能为空'
			      },
//			      限制字符串长度
			      stringLength:{
			       min:3,
			       max:20,
			       message:'密码长度必须位于3到20之间'
			      },
//			      相同性检测
			      identical:{
//			       需要验证的field
			       field:'password',
			       message:'两次密码输入不一致'
			      },
//			      基于正则表达是的验证
			      regexp:{
			       regexp:/^[a-zA-Z0-9_\.]+$/,
			       message:'密码由数字字母下划线和.组成'
			      }
			     }
			    },
//			    确认密码
			    repassword:{
			     message:'密码非法',
			     validators:{
			      notEmpty:{
			       message:'密码不能为空'
			      },
//			      限制字符串长度
			      stringLength:{
			       min:3,
			       max:20,
			       message:'密码长度必须位于3到20之间'
			      },
//			      相同性检测
			      identical:{
//			       需要验证的field
			       field:'password',
			       message:'两次密码输入不一致'
			      },
//			      基于正则表达是的验证
			      regexp:{
			       regexp:/^[a-zA-Z0-9_\.]+$/,
			       message:'密码由数字字母下划线和.组成'
			      }
			     }
			    }

		   }
	});
});

</script> 

</head>
<body>
<form action="${pageContext.request.contextPath}/user/register.do" method="post" id="registerForm" style="padding:10px;">
	<div class="text-center text-info">
	    <h2>请注册</h2>
	</div>
	<div class="form-group">
	    <input type="text" class="form-control" name="username" placeholder="请输入账户名" required autofocus/>
	</div>
	<div class="form-group">
	    <input type="password" class="form-control" name="password" placeholder="请输入密码" required/>
	</div>
	<div class="form-group">
	    <input type="password" class="form-control" name="repassword" placeholder="请输入确认密码" required/>
	</div>
	<div class="form-group">
	    <button type="submit" class="form-control btn btn-primary btn-block">注册</button>
	</div>
</form>
</body>
</html>