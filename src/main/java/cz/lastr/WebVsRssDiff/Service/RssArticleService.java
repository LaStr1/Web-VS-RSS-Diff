package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.ModelHibernate.ArticleFromRSS;
import cz.lastr.WebVsRssDiff.Repository.RssArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssArticleService {

    private RssArticleRepository rssArticleRepository;

    public RssArticleService(RssArticleRepository rssArticleRepository){
        this.rssArticleRepository = rssArticleRepository;
    }

    public List<ArticleFromRSS> findAll(){
        return rssArticleRepository.findAll();
    }

    public void save(List<ArticleFromRSS> articles){
        rssArticleRepository.saveAll(articles);
    }
}
