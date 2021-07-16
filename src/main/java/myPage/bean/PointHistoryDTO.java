package myPage.bean;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


@Data
public class PointHistoryDTO {
	private String email, pointContent;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone ="Asia/Seoul")
	private Date pointLogtime;
	private int plusPoint, minusPoint;
	
}
