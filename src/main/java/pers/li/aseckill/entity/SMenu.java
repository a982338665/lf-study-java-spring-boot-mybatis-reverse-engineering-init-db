package pers.li.aseckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SMenu {
	private String id;
	private String name;
	private String url;
	private String status;
	private String sort;
	private String pid;
	private String cron;
}
