package cn.ahead.dcube.file.dto;

import java.util.Date;

import cn.ahead.dcube.base.dto.IDTO;

public class UploadFileDTO implements IDTO {

	/**
	 * 文件上传ID
	 */
	private String id;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件大小
	 */
	private long fileSize;

	/**
	 * 文件路径
	 */
	private String filePath;

	/**
	 * 文件类型 0 文档 1目录
	 */
	private int fileType = 0;

	/**
	 * 文件存储位置，预留字段，避免出现多存储位置
	 */
	private String filePosition;
	/**
	 * 文件描述
	 */
	private String fileDesc;

	/**
	 * 创建用户
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private Date createTime;

}
