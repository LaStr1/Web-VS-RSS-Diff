package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.Repository.RssArticleRepository;
import cz.lastr.WebVsRssDiff.RepositoryForTempTables.RssArticleRepositoryTempTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RssArticleService {

    private RssArticleRepositoryTempTable rssArticleRepositoryTempTable;
    private RssArticleRepository rssArticleRepository;

    public RssArticleService(RssArticleRepositoryTempTable rssArticleRepositoryTempTable, RssArticleRepository rssArticleRepository) {
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
}
