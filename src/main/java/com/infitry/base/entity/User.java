package com.infitry.base.entity;

import java.util.Date;

import lombok.Data;

/**
 * @since 2020. 3. 31.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 유저 클래스
 */
@Data
public class User {
	long userSeq;
	String id;
	String password;
	String name;
	Date regDate;
}
