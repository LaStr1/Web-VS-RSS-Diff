package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.WebArticle;
import cz.lastr.WebVsRssDiff.Repository.WebArticleRepository;
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

    private WebArticleRepository webArticleRepository;

    public WebArticleService(WebArticleRepository webArticleRepository) {
        this.webArticleRepository = webArticleRepository;
    }

    public List<WebArticle> findAll(){
        return webArticleRepository.findAll();
    }

    public void save(List<WebArticle> articles){
        webArticleRepository.saveAll(articles);
    }

    @Transactional
    public List<WebArticle> getDiff() {
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
