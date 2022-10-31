package com.example;

import static com.example.CycleType.A;

import com.vladmihalcea.hibernate.query.SQLExtractor;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public abstract class UserRepo implements CrudRepository<User, Integer> {

  private static final Logger log = LoggerFactory.getLogger(UserRepo.class);

  @Inject EntityManager em;

  public List<User> get() {
    var cb = em.getCriteriaBuilder();
    CriteriaQuery<User> query = cb.createQuery(User.class);
    Root<User> root = query.from(User.class);
    root.alias("user");

    Path<String> name = root.get("name");
    Predicate first = cb.equal(name.as(String.class), "a");
    Path<Integer> id = root.get("id");

    Root<Cycle> subRoot = query.from(Cycle.class);
    subRoot.alias("cycle");
    Path<Integer> subPath = subRoot.get("id");
    Predicate second = cb.equal(id.as(Integer.class), subPath.as(Integer.class));

    Path<CycleType> subName = subRoot.get("type");
    Predicate third = cb.equal(subName.as(CycleType.class), A);

    Path<LocalDate> subCreated = subRoot.get("created");
    Predicate fourth = cb.equal(subCreated.as(LocalDate.class), LocalDate.now());

    Predicate restrictions = cb.and(first, second, third, fourth);

    CriteriaQuery<User> where = query.select(root).where(restrictions);

    TypedQuery<User> userTypedQuery = em.createQuery(where).setFirstResult(0).setMaxResults(10);

    String sql = SQLExtractor.from(userTypedQuery);
    log.info("Query: {}", sql);

    return userTypedQuery.getResultList();
  }
}
