package cz.lastr.webvsrssdiff.RepositoryForTempTables;

import cz.lastr.webvsrssdiff.ModelForTempTable.WebArticleTempTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebArticleRepositoryTempTable extends JpaRepository<WebArticleTempTable, Long> {
}
