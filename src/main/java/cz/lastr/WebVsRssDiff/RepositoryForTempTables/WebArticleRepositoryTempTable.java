package cz.lastr.WebVsRssDiff.RepositoryForTempTables;

import cz.lastr.WebVsRssDiff.ModelForTempTable.WebArticleTempTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebArticleRepositoryTempTable extends JpaRepository<WebArticleTempTable, Long> {
}
