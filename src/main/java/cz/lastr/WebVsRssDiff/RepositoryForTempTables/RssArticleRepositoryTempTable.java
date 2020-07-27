package cz.lastr.WebVsRssDiff.RepositoryForTempTables;

import cz.lastr.WebVsRssDiff.ModelForTempTable.RssArticleTempTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssArticleRepositoryTempTable extends JpaRepository<RssArticleTempTable, Long> {
}
