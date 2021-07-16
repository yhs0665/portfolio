package simri.bean;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class AdvertiseDTO {
	private int seq,point;
	private String category, subject, image;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yy/MM/dd/ hh:mm:ss")
	private Date cflogtime;
}
