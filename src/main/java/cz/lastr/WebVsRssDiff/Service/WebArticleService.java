package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.ModelHibernate.WebArticle;
import cz.lastr.WebVsRssDiff.Repository.WebArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebArticleService {

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
}
