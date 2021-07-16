package simri.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SimriDTO {
	private String category, email, nickName, subject, content, testURL, image, hashTag1, hashTag2, hashTag3;
	private int seq, hit, point, reply, comLike;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private Date comLogtime;

}