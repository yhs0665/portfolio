package community.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class ReplyDTO {
	private int seq, replySeq, ref, lev, step, pseq, reply, hit, replySingo;
	private String email, replyComment, nickName, palette, profile; 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone ="Asia/Seoul")
	private Date replyLogtime;
}