package com.infitry.base.entity;

import java.util.Date;

import lombok.Data;

/**
 * @since 2020. 4. 1.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : blogPost Entity
 */
@Data
public class BlogPost {
	
	long blogPostSeq;
	String subject;
	String contents;
	String regUser;
	Date regDate;
}
