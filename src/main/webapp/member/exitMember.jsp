<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form id="exitMemberForm" method="post"
	action="/simriTest/member/exitMember">
	<div class="card pt-2 ps-5 pb-5">
		<div class="card-body">
			<h5>
				<strong>회원탈퇴 안내</strong>
			</h5>
		</div>
		<div class="col-8">
			<hr>
		</div>
		<div class="card-body">

			<div>
				<ul>
					<li>회원 탈퇴 시 고객님의 정보는 소비자 보호에 관한 법률에 의거한 모하고심리? 고객정보 보호정책에 따라
						관리 됩니다.</li>
					<li>탈퇴 시 보유하고있던 포인트, 닉네임변경권은 모두 영구 삭제됩니다.</li>
					<li>탈퇴 후 기존에 사용하신 이메일과 아이디로는 재가입이 불가능합니다.</li>
				</ul>
			</div>

			<div class="card">
				<div class="card-body text-center">
					현재 고객님의 사용 가능 포인트는 <span style="font-weight: bold; color: red;">
						${memberDTO.point }</span> p입니다
				</div>
			</div>
			<br>
			<hr>
			<div class="mt-3 mb-3">
				<p>
					<strong style="margin-right: 10px;">탈퇴 사유 확인</strong> <span
						id="exitDiv"></span>
				</p>
				<div class="ml-6 form-check form-check-inline">
					<input class="form-check-input" type="radio" name="palette"
						id="inlineRadio1" value="red"> <label
						class="form-check-label" for="inlineRadio1">이용빈도 낮음</label>
				</div>
				&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="palette"
						id="inlineRadio2" value="green"> <label
						class="form-check-label" for="inlineRadio2">재미없음</label>
				</div>
				&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="palette"
						id="inlineRadio3" value="yellow"> <label
						class="form-check-label" for="inlineRadio3">컨텐츠 부족</label>
				</div>
				&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="palette"
						id="inlineRadio4" value="blue"> <label
						class="form-check-label" for="inlineRadio4">기타</label>
				</div>

			</div>

			<textarea class="textarea exit-reason" id="exit-reason" rows="10"
				cols="150" placeholder="기타 사유를 입력해 주세요" maxlength="300"></textarea>

			<br> <br> <br> <input type="button" id="exitPwdBtn"
				value="탈퇴하기"
				style="float: right; margin-right: 80px; margin-top: 80px;">
		</div>
	</div>

</form>