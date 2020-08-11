package cz.lastr.webvsrssdiff.Repository;

import cz.lastr.webvsrssdiff.Model.RssArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssArticleRepository extends JpaRepository<RssArticle, Long> {
}
