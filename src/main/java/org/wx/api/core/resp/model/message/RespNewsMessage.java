package org.wx.api.core.resp.model.message;

import java.util.List;

import org.wx.api.core.resp.model.message.base.Article;
import org.wx.api.core.resp.model.message.base.BaseRespMessage;


/**
 * 回复图文消息
 * 消息类型,news
 * 注：图文消息条数限制在10条以内，注意，如果图文数超过10，则将会无响应
 * @author Administrator
 *
 */
public class RespNewsMessage extends BaseRespMessage{
	
	//图文消息个数，限制为10条以内
	private int ArticleCount; 
	
	//多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

}
