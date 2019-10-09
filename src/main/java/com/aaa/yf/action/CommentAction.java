package com.aaa.yf.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aaa.yf.entity.CmsComment;
import com.aaa.yf.entity.CmsContent;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsCommentService;
import com.aaa.yf.util.toJsonUtil;
import com.et.mvc.util.Json;

@Controller
@Scope("prototype")
@Action
@ParentPackage(value="cms")
public class CommentAction extends BaseAction {

	@Autowired
	private ICmsCommentService ccommentservice;
	private CmsComment comment = new CmsComment();
	private String validateCode;
	private CmsContent content;
	private String begin;
	private String end;
	private Integer page;
	private Integer rows;
	private String ids;
	private String way;
	private String year;
	
	
	/*
	 * 回复量报表
	 */
	public String commentCountChart() throws Exception{
		StringBuffer json= new StringBuffer("{\"color\":\"#61B161\",\"label\":\"评论数量\",\"data\":[");
		try {
			List<Object[]> list1 = ccommentservice.commentCountChart(way, year);
			if(list1.size() > 0){
				for (Object[] obj : list1) {
					json.append("["+obj[0]+","+obj[1]+"],");
				}
				StringBuffer data = new StringBuffer(json.substring(0, json.length()-1));
				data.append("]}");
				this.printJSON(data.toString());
			}else{
				this.printJSON("{\"color\":\"#61B161\",\"label\":\"评论数量\",\"data\":[["+year+",0]]}");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * 恢复评论
	 */
	public String replyComment() throws Exception{
		try {
			CmsComment c = ccommentservice.findCommentById(comment.getComId());
			c.setIsRecommend(comment.getIsRecommend());
			
			c.setReplyContent(comment.getReplyContent());
			c.setReplyUserId(((CmsUser)this.getSession().getAttribute("user")).getUserId());
			c.setReplyTime(new Date().toLocaleString());
			ccommentservice.doReplyComment(c);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("恢复评论失败");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 推荐评论
	 */
	public String recommendComment() throws Exception{
		
		try {
			ccommentservice.doRecommendComment(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("推荐评论失败");
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 删除评论
	 */
	public String deleteComment() throws Exception{
		try {
			ccommentservice.doDeleteComment(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("删除评论失败");
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	 * 审核通过
	 */
	public String checkComment() throws Exception{
		try {
			ccommentservice.doCheckComment(ids);
			this.printJSON("yes");
		} catch (Exception e) {
			this.printJSON("no");
			this.getLogger().info("审核评论失败");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String findCommentByPage() throws Exception{
		Map condition = new HashMap();
		condition.put("obj.content like ?", "%"+comment.getContent()+"%");
		condition.put("obj.createTime > ?", begin);
		condition.put("obj.createTime < ?", end);
		
		toJsonUtil json = new toJsonUtil();
		json.setRows(ccommentservice.findCommentByPage(condition, "obj.createTime", "desc", page, rows));
		json.setTotal(ccommentservice.findCount(condition));
		
		this.printJSON(Json.toJson(json));
		
		return null;
	}
	
	/*
	 * 评论新闻
	 */
	
	public String doComment() throws Exception{
		
		try {
			if(this.getSession().getAttribute("u") == null){
				this.printJSON("no");
			}else{
				
				if(this.getSession().getAttribute("rand")==null || "".equals(validateCode) || validateCode == null){
					this.printJSON("yesno");
				}else{
					if(validateCode.equalsIgnoreCase(this.getSession().getAttribute("rand").toString())){
						comment.setCmsContent(content);//设置内容id
						comment.setUserName(((CmsUser)this.getSession().getAttribute("u")).getUserName());
						comment.setCreateTime(new Date().toLocaleString());
						comment.setIsChecked(0);
						comment.setIsRecommend(0);
						ccommentservice.doComment(comment);
						this.printJSON("yes");
					}else{
						this.printJSON("noyes");
					}
				}
				
			}
		} catch (Exception e) {
			this.printJSON("no");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ICmsCommentService getCcommentservice() {
		return ccommentservice;
	}

	public void setCcommentservice(ICmsCommentService ccommentservice) {
		this.ccommentservice = ccommentservice;
	}

	public CmsComment getComment() {
		return comment;
	}

	public void setComment(CmsComment comment) {
		this.comment = comment;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public CmsContent getContent() {
		return content;
	}

	public void setContent(CmsContent content) {
		this.content = content;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getWay() {
		return way;
	}


	public void setWay(String way) {
		this.way = way;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}
	
	
}
