package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsCommentDao;
import com.aaa.yf.entity.CmsComment;
import com.aaa.yf.service.ICmsCommentService;

@Service("ccommentservice")
public class CmsCommentServiceImpl implements ICmsCommentService {

	@Autowired
	private ICmsCommentDao ccommentdao;

	public ICmsCommentDao getCcommentdao() {
		return ccommentdao;
	}

	public void setCcommentdao(ICmsCommentDao ccommentdao) {
		this.ccommentdao = ccommentdao;
	}

	public void doComment(CmsComment comment) {
		ccommentdao.add(comment);
	}

	public List<CmsComment> findCommentByPage(Map condition, String sortName,
			String sortType, Integer page, Integer rows) {
		List<CmsComment> list = ccommentdao.findInfo(condition, sortName, sortType, page, rows);
		for (CmsComment cmsComment : list) {
			cmsComment.getCmsContent().getTitle();
			cmsComment.getCmsContent().getCmsChannel().getChannelPath();
			
		}
		return list;
	}

	public long findCount(Map condition) {
		return ccommentdao.findCount(condition);
	}

	public void doCheckComment(String ids) {
		ccommentdao.checkComment(ids);
	}

	public void doDeleteComment(String ids) {
		ccommentdao.deleteComment(ids);
	}

	public void doRecommendComment(String ids) {
		ccommentdao.recommendComment(ids);
		
	}

	public CmsComment findCommentById(Integer id) {
		return ccommentdao.findById(id);
	}

	public void doReplyComment(CmsComment c) {
		ccommentdao.update(c);
	}

	public List<Object[]> commentCountChart(String way, String year) {
		return ccommentdao.commentCountChart(way, year);
	}
	
	
}
