package community.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LikeSingoDTO {
	private String category, email, nickName, palette, singoReason;
	private int seq, replySeq, singo, likes,checkPoint;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private Date likeLogtime;
}
