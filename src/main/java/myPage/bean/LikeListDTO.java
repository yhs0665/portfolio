package myPage.bean;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class LikeListDTO {
	private String subject,category, email, image;
	private int seq,likes;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private Date likeLogtime;
}
