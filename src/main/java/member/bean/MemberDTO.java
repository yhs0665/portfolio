package member.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MemberDTO {
	private String email;
	private String name;
	private String nickname;
	private String pwd;
	private String palette;
	private int point;
	private int memSingo;
	private String profile;
	private String condition;
	private int changeNick;
	private String singoLogtime;
	private String stopReason;
	private String signLogtime;
	private int stopPeriod;
	private String joinType;
	private int todayWriteCount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yy/MM/dd")
	private Date writeDate;
}

