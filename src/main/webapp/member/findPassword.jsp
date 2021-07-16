<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>비밀번호 찾기</title>

    <!-- Custom fonts for this template-->
    <link rel="stylesheet" href="/simriTest/css/login.css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">


</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-2">비밀번호가 기억나지 않으세요?</h1>
                                        <p class="mb-4">
											회원님의 회원 정보에 등록된 이메일을 통해서 임시 비밀번호를발급 받으실 수 있습니다.</p>
                                    </div>
                                    <form class="user">
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user"
                                                id="email" aria-describedby="emailHelp"
                                                placeholder="이메일">
                                        </div>
                                        <input type="button" class="btn btn-primary btn-user btn-block" value="임시 비밀번호 받기" id="findBtn"></a>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="/simriTest/member/memberWriteForm">회원가입!</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="/simriTest/member/login">로그인</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>


    <!-- Custom scripts for all pages-->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
    		$('#findBtn').click(function(){
        		if($("#email").val() == ''){
        			alert("이메일을 입력해주세요");
        			return;
        		}
        			$('#findBtn').attr('disabled',true);
    			$.ajax({
    				url:"/simriTest/member/getPwd",
    				type:"POST",
    				data: "email=" + $("#email").val(),
    				dataType:"text",
    				success: function(result){
    					if(result=="exist"){
    						alert("등록된 이메일로 임시 비밀번호를 발송하였습니다");
    						window.close();
    					}else {
    						alert("가입되지 않은 이메일입니다. 다시 확인해 주세요");
    						$("#email").focus();
    						$('#findBtn').attr('disabled',false);
    					}
    				},
    				error: function(err){
    					alert(err);
    				}
    			});
    		});
    </script>
   
    

</body>

</html>>