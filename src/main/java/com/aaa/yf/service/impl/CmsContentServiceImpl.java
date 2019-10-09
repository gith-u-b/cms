package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsContentDao;
import com.aaa.yf.entity.CmsContent;
import com.aaa.yf.service.ICmsContentService;

@Service("cnewservice")
public class CmsContentServiceImpl implements ICmsContentService {

	@Autowired
	private ICmsContentDao cnewdao;

	public ICmsContentDao getCnewdao() {
		return cnewdao;
	}

	public void setCnewdao(ICmsContentDao cnewdao) {
		this.cnewdao = cnewdao;
	}

	public List<CmsContent> findAllContent(Map condition, String sortName,
			String sortType, Integer page, Integer rows) {
		List<CmsContent> clist = cnewdao.findInfo(condition, sortName, sortType, page, rows);
		for (CmsContent c : clist) {
			c.getCmsChannel().getChannelName();
			c.getCmsUser().getUserName();
			c.getCmsContentType().getTypeName();
			c.getCmsTemplate().getTemplateName();
		}
		return clist;
	}

	public long findCount(Map condition) {
		return cnewdao.findCount(condition);
	}

	public void doAddContent(CmsContent content) {
		cnewdao.add(content);
	}

	public void doDeleteContent(String ids) {
		cnewdao.deleteContent(ids);
	}

	public void doUpdateContent(CmsContent content) {
		cnewdao.update(content);
	}

	public List<CmsContent> findHotContent() {
		List<CmsContent> list =  cnewdao.findHotContent();
		for (CmsContent cmsContent : list) {
			cmsContent.getCmsChannel().getChannelPath();
		}
		return list;
	}

	public CmsContent findContentById(Integer id) {
		CmsContent content  = cnewdao.findById(id);
		content.getCmsChannel().getCmsTemplate().getPath();
		content.getCmsTemplate().getPath();
		return content;
	}

	public void doYesNoStatic(String yn,String ids) {
		cnewdao.yesNoStatic(yn,ids);
	}

	public void doCheckNews(String yn,String ids) {
		cnewdao.checkNews(yn,ids);
	}

	public void doUpdateGarbage(String yn, String ids) {
		cnewdao.updateGarbage(yn, ids);
	}

	public void doSetContentChannel(Integer id, String ids) {
		cnewdao.setContentChannel(id, ids);
		
	}

	public void doDeleteContentByCid(String ids) {
		cnewdao.deleteContentByCid(ids);
	}
	
	
}
