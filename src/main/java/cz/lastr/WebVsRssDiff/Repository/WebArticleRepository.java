package cz.lastr.WebVsRssDiff.Repository;

import cz.lastr.WebVsRssDiff.ModelHibernate.ArticleFromWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebArticleRepository extends JpaRepository<ArticleFromWeb, Long> {
}
