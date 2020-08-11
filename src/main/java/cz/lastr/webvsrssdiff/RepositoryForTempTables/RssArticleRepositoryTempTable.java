package cz.lastr.webvsrssdiff.RepositoryForTempTables;

import cz.lastr.webvsrssdiff.ModelForTempTable.RssArticleTempTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssArticleRepositoryTempTable extends JpaRepository<RssArticleTempTable, Long> {
}
