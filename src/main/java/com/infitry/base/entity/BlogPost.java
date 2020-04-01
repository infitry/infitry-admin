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
	private long blogPostSeq;
	private PostCategory postCategory;
	private String subject;
	private String contents;
	private String regUser;
	private Date regDate;
}
