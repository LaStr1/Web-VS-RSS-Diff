package cz.lastr.WebVsRssDiff.Service;

import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import cz.lastr.WebVsRssDiff.RepositoryForTempTables.RssArticleRepositoryTempTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RssArticleService {

    private RssArticleRepositoryTempTable articleRepositoryTempTable;

    public RssArticleService(RssArticleRepositoryTempTable articleRepositoryTempTable){
        this.articleRepositoryTempTable = articleRepositoryTempTable;
    }

    public List<RssArticleTempTable> findAll(){
        return articleRepositoryTempTable.findAll();
    }

    public void saveToTempTable(List<RssArticleTempTable> articles){
        articleRepositoryTempTable.saveAll(articles);
    }
}
