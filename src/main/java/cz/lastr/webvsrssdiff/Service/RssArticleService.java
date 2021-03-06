package cz.lastr.webvsrssdiff.Service;

import cz.lastr.webvsrssdiff.Model.RssArticle;
import cz.lastr.webvsrssdiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.webvsrssdiff.Repository.RssArticleRepository;
import cz.lastr.webvsrssdiff.RepositoryForTempTables.RssArticleRepositoryTempTable;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class RssArticleService {
    private final EntityManager entityManager;
    private final RssArticleRepository rssArticleRepository;
    private final RssArticleRepositoryTempTable rssArticleRepositoryTempTable;

    public RssArticleService(EntityManager entityManager, RssArticleRepository rssArticleRepository, RssArticleRepositoryTempTable rssArticleRepositoryTempTable) {
        this.entityManager = entityManager;
        this.rssArticleRepository = rssArticleRepository;
        this.rssArticleRepositoryTempTable = rssArticleRepositoryTempTable;
    }

    public List<RssArticle> findAllInRegularTable(){
        return rssArticleRepository.findAll();
    }

    public List<RssArticleTempTable> findAllInTempTable(){
        return rssArticleRepositoryTempTable.findAll();
    }

    public void saveToRegularTable(List<RssArticle> articles){
        rssArticleRepository.saveAll(articles);
    }

    public void saveToTempTable(List<RssArticleTempTable> articles){
        rssArticleRepositoryTempTable.saveAll(articles);
    }

    public void saveToRegularTableIfNotExist(List<RssArticleTempTable> articles){
        saveToTempTable(articles);
        saveFromTempTableToRegularTableIfNotExist();
        deleteAllArticlesInTempTable();
    }

    public void saveFromTempTableToRegularTableIfNotExist(){
        Session s = entityManager.unwrap(Session.class);

        Query query = s.createQuery(
                "insert into RssArticle(articleID) " +
                        "select rssArticleTempTable.articleID " +
                        "from RssArticleTempTable rssArticleTempTable " +
                        "where not exists " +
                        "(select rssArticle.articleID " +
                        "from RssArticle rssArticle " +
                        "where type(rssArticle) = RssArticle " +
                        "and rssArticleTempTable.articleID = rssArticle.articleID)");

        query.executeUpdate();
    }

    public void deleteAllArticlesInTempTable() {
        rssArticleRepositoryTempTable.deleteAll();
    }

    public boolean containDuplicate() {
        boolean containDuplicate = false;

        TypedQuery<Integer> getDuplicateQuery = entityManager.createQuery(
                "select rssArticle.articleID " +
                        "from RssArticle rssArticle " +
                        "where type(rssArticle) = RssArticle " +
                        "group by rssArticle.articleID " +
                        "having count(rssArticle.articleID) > 1",
                Integer.class);

        List<Integer> duplicateArticles = getDuplicateQuery.getResultList();

        if (!duplicateArticles.isEmpty()){
            containDuplicate = true;
        }

        return containDuplicate;
    }
}
