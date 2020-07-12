package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.ModelHibernate.ArticleFromWeb;
import cz.lastr.WebVsRssDiff.Repository.WebArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebArticleService {

    private WebArticleRepository webArticleRepository;

    public WebArticleService(WebArticleRepository webArticleRepository) {
        this.webArticleRepository = webArticleRepository;
    }

    public List<ArticleFromWeb> findAll(){
        return webArticleRepository.findAll();
    }

    public void save(List<ArticleFromWeb> articles){
        webArticleRepository.saveAll(articles);
    }
}
