<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="content" style="display: none;">해당 계정은 '${memberCondition.stopReason}'와(과) 같은 사유로 ${memberCondition.stopPeriod } 일간 정지되었습니다.</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	alert($('#content').text());
	location.href="/simriTest/member/login"
</script>
</body>
</html>