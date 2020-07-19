package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import cz.lastr.WebVsRssDiff.Repository.WebArticleRepository;
import cz.lastr.WebVsRssDiff.RepositoryForTempTables.WebArticleRepositoryTempTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class WebArticleService {

    @PersistenceContext
    EntityManager entityManager;

    private WebArticleRepositoryTempTable webArticleRepositoryTempTable;

    public WebArticleService(WebArticleRepositoryTempTable webArticleRepositoryTempTable) {
        this.webArticleRepositoryTempTable = webArticleRepositoryTempTable;
    }

    public List<WebArticleTempTable> findAll(){
        return webArticleRepositoryTempTable.findAll();
    }

    public void save(List<WebArticleTempTable> articles){
        webArticleRepositoryTempTable.saveAll(articles);
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
