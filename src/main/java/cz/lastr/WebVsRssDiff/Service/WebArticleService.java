package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Repository.WebArticleRepository;
import cz.lastr.WebVsRssDiff.RepositoryForTempTables.WebArticleRepositoryTempTable;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class WebArticleService {

    private EntityManager entityManager;

    private WebArticleRepositoryTempTable webArticleRepositoryTempTable;

    private WebArticleRepository webArticleRepository;

    public WebArticleService(EntityManager entityManager, WebArticleRepositoryTempTable webArticleRepositoryTempTable, WebArticleRepository webArticleRepository) {
        this.entityManager = entityManager;
        this.webArticleRepositoryTempTable = webArticleRepositoryTempTable;
        this.webArticleRepository = webArticleRepository;
    }

    public List<WebArticleTempTable> findAllInTempTable(){
        return webArticleRepositoryTempTable.findAll();
    }

    public List<WebArticle> findAllRegular(){
        return webArticleRepository.findAll();
    }

    public void save(List<WebArticleTempTable> articles){
        deleteAllArticlesInTempTable();
        saveToTempTable(articles);
        //saveOnlyNew();
    }

    public void saveWithOnlyNew(List<WebArticleTempTable> articles){
        deleteAllArticlesInTempTable();
        saveToTempTable(articles);
        saveOnlyNew();
    }

    public void deleteAllArticlesInTempTable(){
        webArticleRepositoryTempTable.deleteAllInBatch();
    }

    public void saveToTempTable(List<WebArticleTempTable> articles){
        webArticleRepositoryTempTable.saveAll(articles);
    }

    public WebArticle saveToRegularTable(WebArticle webArticle){
        return webArticleRepository.save(webArticle);
    }

    @Transactional
    public void saveOnlyNew(){
        Session s = entityManager.unwrap(Session.class);

        Query query = s.createQuery("insert into WebArticle(articleID, url, date, title, perex) select wT.articleID, wT.url, wT.date, wT.title, wT.perex from WebArticleTempTable wT"); // where not exists (select wTT.articleID from WebArticleTempTable wTT where wT.articleID = wTT.articleID)");
        query.executeUpdate();
    }

    @Transactional
    public List<WebArticleTempTable> getDiff() {
        TypedQuery<WebArticleTempTable> diffQuery = entityManager.createQuery(
                "select webArticle " +
                        "from WebArticleTempTable webArticle " +
                        "left outer join RssArticleTempTable rssArticle on webArticle.articleID = rssArticle.articleID " +
                        "where rssArticle.id is null ",
                WebArticleTempTable.class);

        List<WebArticleTempTable> diffWebArticles = diffQuery.getResultList();

        return diffWebArticles;
    }
}
