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

    public List<WebArticle> findAll(){
        return webArticleRepository.findAll();
    }

    public void save(List<WebArticleTempTable> articles){
        deleteAllArticlesInTempTable();
        saveToTemp(articles);
        //saveOnlyNew();
    }

    public void saveWithOnlyNew(List<WebArticleTempTable> articles){
        deleteAllArticlesInTempTable();
        saveToTemp(articles);
        saveToRegularIfNotExist();
    }

    public void deleteAllArticlesInTempTable(){
        webArticleRepositoryTempTable.deleteAllInBatch();
    }

    public void deleteAllArticles(){
        webArticleRepository.deleteAllInBatch();
    }
    public void saveToTemp(List<WebArticleTempTable> articles){
        webArticleRepositoryTempTable.saveAll(articles);
    }

    public void saveToRegularTable(List<WebArticle> articles){
        webArticleRepository.saveAll(articles);
    }

    @Transactional
    public boolean containDuplicate(){
        boolean containDuplicate = false;

        TypedQuery<Integer> getDuplicateQuery = entityManager.createQuery("select webarticle.articleID " +
                                                                    "from WebArticle webarticle " +
                                                                    "where type(webarticle) = WebArticle " +
                                                                    "group by webarticle.articleID " +
                                                                    "having count(webarticle.articleID) > 1", Integer.class);
        List<Integer> duplicateArticles = getDuplicateQuery.getResultList();

        if (!duplicateArticles.isEmpty()){
            containDuplicate = true;
        }

        return containDuplicate;
    }

    @Transactional
    public void saveToRegularIfNotExist(){
        Session s = entityManager.unwrap(Session.class);

        Query query = s.createQuery("insert into WebArticle(articleID, url, date, title, perex) select wT.articleID, wT.url, wT.date, wT.title, wT.perex from WebArticleTempTable wT where not exists (select we.articleID, we.url, we.date, we.title, we.perex from WebArticle we where type(we) = WebArticle and wT.articleID = we.articleID)");
        //Query query = s.createQuery("insert into WebArticle(articleID, url, date, title, perex) select wT.articleID, wT.url, wT.date, wT.title, wT.perex from WebArticleTempTable wT left join WebArticle we on we.articleID = wT.articleID where we.articleID is null and type(we) =WebArticle ");
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
