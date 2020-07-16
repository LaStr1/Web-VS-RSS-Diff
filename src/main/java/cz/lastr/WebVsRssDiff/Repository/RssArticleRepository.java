package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.Model.RssArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssArticleRepository extends JpaRepository<RssArticle, Long> {
}
