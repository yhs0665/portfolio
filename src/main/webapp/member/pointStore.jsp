<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="card">
	<div class='css3-tab'>

		<input type='radio' name='a' id='tabTwo' tabindex="2" checked>
		<input type='radio' name='a' id='tabThree' tabindex="3">


		<div class="css3-tab-nav">
			<label for='tabTwo' style="width: 10px;">포인트 충전</label> 
			<label  for='tabThree' style="width: 10px;">포인트 상점</label>
		</div>


		<div class='css3-tab-content tab-two' style="padding-bottom: 0px;">
			<!-- start slipsum code -->
			<div class="card">
				<div class="card-body bg-transparent  ">
					<div class="row">
						<div class="card-body ms-5 fs-4">포인트 충전</div>
						<div class="col-md-6">
							<div class="card-body">
								<table class="table text-center">
									<tbody>
										<tr>
											<td class="align-middle fs-5 font-monospace"
												style="width: 25%;">100p</td>
											<td style="text-align: end;">
												<button type="button" id="onepointBtn"
													class="btn btn-warning btn-sm font-monospace pointChargeBtn">1000원</button>
												<input type="hidden" value="100">	
											</td>
										</tr>
										<tr>
											<td class="fs-5 font-monospace align-middle"
												style="width: 25%;">200p</td>
											<td style="text-align: end;">
												<button type="button" id="twopointBtn"
													class="btn btn-warning btn-sm font-monospace pointChargeBtn">2000원</button>
												<input type="hidden" value="200">
											</td>
										</tr>
										<tr>
											<td class="fs-5 font-monospace align-middle"
												style="width: 25%;">500p</td>
											<td style="text-align: end;">
												<button type="button" id="fivepointBtn"
													class="btn btn-warning btn-sm font-monospace pointChargeBtn">5000원</button>
												<input type="hidden" value="500">		
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card-body">
								<table class="table text-center">
									<tbody>
										<tr>
											<td class="align-middle fs-5 font-monospace"
												style="width: 25%;">1100p</td>
											<td style="text-align: end;">
												<button type="button"
													class="btn btn-warning btn-sm font-monospace pointChargeBtn">10000원</button>
												<input type="hidden" value="1100">		
											</td>
										</tr>
										<tr>
											<td class="fs-5 font-monospace align-middle"
												style="width: 25%;">2400p</td>
											<td style="text-align: end;">
												<button type="button"
													class="btn btn-warning btn-sm font-monospace pointChargeBtn">20000원</button>
												<input type="hidden" value="2400">		
											</td>
										</tr>
										<tr>
											<td class="fs-5 font-monospace align-middle"
												style="width: 25%;">6000p</td>
											<td style="text-align: end;">
												<button type="button"
													class="btn btn-warning btn-sm font-monospace pointChargeBtn">50000원</button>
												<input type="hidden" value="6000">		
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<br>
					<small>
						<ul>
						<li>포인트 충전 이후 결제 취소 및 환불은 불가합니다.</li>
								<li>사이트 내 이용원칙 위반 시 사전안내없이 사이트이용이 제한될 수 있습니다.
								이로 인한 모든 환불은 불가합니다.</li>
								</ul></small>
		</div>

		<!-- 포인트상점 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 -->
	<div class='css3-tab-content tab-three'>
			<!-- start slipsum code -->
			<div class="row row-cols-1 row-cols-md-4 g-4">
				<div class="col">
					<div class="card" style="height: 390px;">
						<img src="/simriTest/img/red.png" class="card-img-top"
							style="height: 330px;" alt="...">
						<div class="card-body">
							<h5 class="card-title font-monospace">RED CARD</h5>
						</div>
						<div class="card-body" style="text-align: end;">
							<button type="button" class="btn btn-warning btn-sm font-monospace buyBtn" id="red">200p</button>
							<input type="hidden" value="200" class="색상변경권  구입">
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card" style="height: 390px;">
						<img src="/simriTest/img/blue.png" class="card-img-top"
							style="height: 330px;" alt="...">
						<div class="card-body">
							<h5 class="card-title font-monospace">BLUE CARD</h5>
						</div>
						<div class="card-body" style="text-align: end;">
							<button type="button" class="btn btn-warning btn-sm font-monospace buyBtn" id="blue">200p</button>
							<input type="hidden" value="200" class="색상변경권  구입">
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card" style="height: 390px;">
						<img src="/simriTest/img/green.png" style="height: 300px;"
							class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title font-monospace">GREEN CARD</h5>
						</div>
						<div class="card-body" style="text-align: end;">
							<button type="button" class="btn btn-warning btn-sm font-monospace buyBtn" id="green">200p</button>
							<input type="hidden" value="200" class="색상변경권  구입">
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card" style="height: 390px;">
						<img src="/simriTest/img/yellow.png" class="card-img-top"
							style="height: 330px;" alt="...">
						<div class="card-body">
							<h5 class="card-title font-monospace">YELLOW CARD</h5>
						</div>
						<div class="card-body" style="text-align: end;">
							<button type="button" class="btn btn-warning btn-sm font-monospace buyBtn" id="yellow">200p</button>
							<input type="hidden" value="200" class="색상변경권  구입">
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card" style="height: 403px;">
						<img src="/simriTest/img/rainbow.jpg" class="card-img-top"
							style="height: 330px;" alt="...">
						<div class="card-body ">
							<h5 class="card-title font-monospace">RAINBOW CARD</h5>


							<p class="card-text mb-0">모든 CLASS 입장 가능한 와일드 카드입니다</p>


							<div class="card-body pt-0" style="text-align: end;">
								<button type="button" class="btn btn-warning btn-sm font-monospace buyBtn" id="rainbow">500p</button>
								<input type="hidden" value="500" class="레인보우카드  구입">
								
							</div>

						</div>
					</div>
				</div>
				<div class="col">
					<div class="card" style="height: 403px;">
						<img src="/simriTest/img/id1.jpg" class="card-img-top"
							style="height: 313px;" alt="...">
						<hr>
						<div class="card-body">
							<h5 class="card-title ">닉네임 변경권</h5>
							<p class="card-text mb-0">
								닉네임 변경에 사용됩니다. <br> 매회 1개가 소모됩니다.<br> 이미 존재하는 닉네임으로는
								변경할 수 없습니다.
							</p>
						</div>
						<div class="card-body pt-0" style="text-align: end;">
							<button type="button" class="btn btn-warning btn-sm font-monospace buyBtn" id="nickChange">1000p</button>
							<input type="hidden" value="1000" class="닉네임 변경권  구입">
						</div>
					</div>
				</div>
			</div>
			<br> <br> <br> <br>
			


		</div>
	</div>

</div>
