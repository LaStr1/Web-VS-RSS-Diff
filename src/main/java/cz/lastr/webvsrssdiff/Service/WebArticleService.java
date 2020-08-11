package cz.lastr.webvsrssdiff.Service;

import cz.lastr.webvsrssdiff.Model.WebArticle;
import cz.lastr.webvsrssdiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.webvsrssdiff.Repository.WebArticleRepository;
import cz.lastr.webvsrssdiff.RepositoryForTempTables.WebArticleRepositoryTempTable;
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
    private final EntityManager entityManager;
    private final WebArticleRepository webArticleRepository;
    private final WebArticleRepositoryTempTable webArticleRepositoryTempTable;

    public WebArticleService(EntityManager entityManager, WebArticleRepository webArticleRepository, WebArticleRepositoryTempTable webArticleRepositoryTempTable) {
        this.entityManager = entityManager;
        this.webArticleRepository = webArticleRepository;
        this.webArticleRepositoryTempTable = webArticleRepositoryTempTable;
    }

    public List<WebArticle> findAllInRegularTable(){
        return webArticleRepository.findAll();
    }

    public List<WebArticleTempTable> findAllInTempTable(){
        return webArticleRepositoryTempTable.findAll();
    }

    public void saveToRegularTable(List<WebArticle> articles){
        webArticleRepository.saveAll(articles);
    }

    public void saveToTempTable(List<WebArticleTempTable> articles){
        webArticleRepositoryTempTable.saveAll(articles);
    }

    public void saveToRegularTableIfNotExist(List<WebArticleTempTable> articles){
        saveToTempTable(articles);
        saveFromTempTableToRegularTableIfNotExist();
        deleteAllArticlesInTempTable();
    }

    public void saveFromTempTableToRegularTableIfNotExist(){
        Session s = entityManager.unwrap(Session.class);

        Query query = s.createQuery(
                "insert into WebArticle(articleID, url, date, title, perex) " +
                        "select webArticleTempTable.articleID, webArticleTempTable.url, webArticleTempTable.date, webArticleTempTable.title, webArticleTempTable.perex " +
                        "from WebArticleTempTable webArticleTempTable " +
                        "where not exists " +
                        "(select webArticle.articleID, webArticle.url, webArticle.date, webArticle.title, webArticle.perex " +
                        "from WebArticle webArticle " +
                        "where type(webArticle) = WebArticle " +
                        "and webArticleTempTable.articleID = webArticle.articleID)");

        query.executeUpdate();
    }

    public void deleteAllArticlesInRegularTable(){
        webArticleRepository.deleteAllInBatch();
    }

    public void deleteAllArticlesInTempTable(){
        webArticleRepositoryTempTable.deleteAllInBatch();
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

    public List<WebArticle> getDiffBetweenWebAndRss() {
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
