package com.infitry.base.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	long userSeq;
	String id;
	String password;
	String name;
	Date regDate;
}
