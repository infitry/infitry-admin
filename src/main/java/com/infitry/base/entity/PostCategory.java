package com.infitry.base.entity;

import java.util.Date;

import lombok.Data;

/**
 * @since 2020. 4. 1.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : blogPostCategory Entity
 */
@Data
public class PostCategory {
	private long blogPostCategorySeq;
	private String name;
	private String regUser;
	private Date regDate;
}
