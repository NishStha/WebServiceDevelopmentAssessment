package uts.ws.SOAP;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.ws.handler.MessageContext;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;

import uts.ws.*;

@WebService
public class ArticleSOAP {
	@Resource
	private WebServiceContext context;
	
	private ArticleApplication getArticleApp() throws JAXBException, IOException {
		ServletContext application = (ServletContext)context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		synchronized (application) {
			ArticleApplication articleApp = (ArticleApplication)application.getAttribute("articleApp");
	    if (articleApp == null) {
	    	articleApp = new ArticleApplication();
	    	articleApp.setFilePath(application.getRealPath("articles.xml"));
	    	application.setAttribute("articleApp", articleApp);
	   }
	   return articleApp;
	}
	}
	
	public ArrayList<Article> fetchArticles() throws JAXBException, IOException{
		return getArticleApp().getArticleList();
	}
	
	public void removeArticle(int id) throws JAXBException, IOException{
		Article article = getArticleApp().getArticles().findById(id);
		getArticleApp().getArticles().removeArticle(article);
	}
	
	public void addArticle(int id, String author, String tag, String date, String title, String previews, String text) throws JAXBException, IOException{
		Article article = new Article(id, author, tag, date, title, previews, text);
		getArticleApp().getArticles().addArticle(article);
	}

}