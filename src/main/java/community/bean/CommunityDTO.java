package community.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class CommunityDTO {
	private String category, email, nickName, subject, content, image, palette,hashtag1,hashtag2,hashtag3,testURL;
	private int seq, hit, comSingo, comShare, reply, comLike ,point;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private Date comLogtime;

}