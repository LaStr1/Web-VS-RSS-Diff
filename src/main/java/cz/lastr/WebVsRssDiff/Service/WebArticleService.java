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
@Transactional
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
        saveToTempTable(articles);
        saveFromTempTableToRegularTableIfNotExist();
        deleteAllArticlesInTempTable();
    }

    public void saveToTempTable(List<WebArticleTempTable> articles){
        webArticleRepositoryTempTable.saveAll(articles);
    }

    public void saveFromTempTableToRegularTableIfNotExist(){
        Session s = entityManager.unwrap(Session.class);

        Query query = s.createQuery(
                "insert into WebArticle(articleID, url, date, title, perex) " +
                        "select wT.articleID, wT.url, wT.date, wT.title, wT.perex " +
                        "from WebArticleTempTable wT " +
                        "where not exists " +
                        "(select we.articleID, we.url, we.date, we.title, we.perex " +
                        "from WebArticle we " +
                        "where type(we) = WebArticle " +
                        "and wT.articleID = we.articleID)");

        query.executeUpdate();
    }

    public void deleteAllArticlesInTempTable(){
        webArticleRepositoryTempTable.deleteAllInBatch();
    }

    public void deleteAllArticles(){
        webArticleRepository.deleteAllInBatch();
    }

    public void saveToRegularTable(List<WebArticle> articles){
        webArticleRepository.saveAll(articles);
    }

    public boolean containDuplicate(){
        boolean containDuplicate = false;

        TypedQuery<Integer> getDuplicateQuery = entityManager.createQuery(
                "select webarticle.articleID " +
                        "from WebArticle webarticle " +
                        "where type(webarticle) = WebArticle " +
                        "group by webarticle.articleID " +
                        "having count(webarticle.articleID) > 1",
                            Integer.class);

        List<Integer> duplicateArticles = getDuplicateQuery.getResultList();

        if (!duplicateArticles.isEmpty()){
            containDuplicate = true;
        }

        return containDuplicate;
    }

    public List<WebArticle> getDiffBetweenRssAndWeb() {
        TypedQuery<WebArticle> diffQuery = entityManager.createQuery(
                "select webArticle " +
                        "from WebArticle webArticle " +
                        "left outer join RssArticle rssArticle on webArticle.articleID = rssArticle.articleID " +
                        "where rssArticle.id is null ",
                            WebArticle.class);

        List<WebArticle> diffWebArticles = diffQuery.getResultList();

        return diffWebArticles;
    }
}
