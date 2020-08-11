package cz.lastr.webvsrssdiff.Repository;

import cz.lastr.webvsrssdiff.Model.WebArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebArticleRepository extends JpaRepository<WebArticle, Long> {
}
