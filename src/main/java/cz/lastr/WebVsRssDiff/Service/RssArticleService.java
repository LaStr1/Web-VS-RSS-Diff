package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.Repository.RssArticleRepository;
import cz.lastr.WebVsRssDiff.RepositoryForTempTables.RssArticleRepositoryTempTable;
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

    private EntityManager entityManager;

    private RssArticleRepositoryTempTable rssArticleRepositoryTempTable;
    private RssArticleRepository rssArticleRepository;

    public RssArticleService(EntityManager entityManager, RssArticleRepositoryTempTable rssArticleRepositoryTempTable, RssArticleRepository rssArticleRepository) {
        this.entityManager = entityManager;
        this.rssArticleRepositoryTempTable = rssArticleRepositoryTempTable;
        this.rssArticleRepository = rssArticleRepository;
    }

    public List<RssArticleTempTable> findAllInTempTable(){
        return rssArticleRepositoryTempTable.findAll();
    }

    public List<RssArticle> findAllInRegularTable(){
        return rssArticleRepository.findAll();
    }

    public void saveToTempTable(List<RssArticleTempTable> articles){
        rssArticleRepositoryTempTable.saveAll(articles);
    }

    public void saveToRegularTable(List<RssArticle> articles){
        rssArticleRepository.saveAll(articles);
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

    public void saveFromTempTableToRegularTableIfNotExist(){
        Session s = entityManager.unwrap(Session.class);

        Query query = s.createQuery(
                "insert into RssArticle(articleID) " +
                        "select rT.articleID " +
                        "from RssArticleTempTable rT " +
                        "where not exists " +
                        "(select re.articleID " +
                        "from RssArticle re " +
                        "where type(re) = RssArticle " +
                        "and rT.articleID = re.articleID)");

        query.executeUpdate();
    }
}
