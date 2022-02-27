package br.com.renan.project.infra.persistence.jpa.repository;

import br.com.renan.project.domain.entity.QShoe;
import br.com.renan.project.domain.entity.Shoe;
import br.com.renan.project.domain.query.Query;
import br.com.renan.project.domain.repository.ShoeRepository;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório da entidade Sidebar
 *
 * @author Renan Peres
 */
@Repository
public class ShoeJpaRepository extends AbstractJpaRepository<Shoe, Long> implements ShoeRepository {
    @Override
    public List<Shoe> find(Query<Shoe> query) {
        return this.findShoe(query, Boolean.TRUE).fetch();
    }

    private JPAQuery<Shoe> findShoe(Query<Shoe> query, Boolean sortAndPaging) {
        QShoe qshoe = QShoe.shoe;

        final PathBuilder<Shoe> path = new PathBuilder<>(Shoe.class, "shoe");

        final JPAQuery<Shoe> jpaQuery =  new JPAQueryFactory(entityManager)
                .selectDistinct(qshoe)
                .from(qshoe);

        // Utilizado para paginar
        if (sortAndPaging) {

            // sort
            sort(query, jpaQuery, path);

            // page
            if (Optional.ofNullable(query.getPage()).orElse(0L) > 0L) {
                page(jpaQuery, query);
            }

            // limit
            getLimit(jpaQuery, query);
        }

        return jpaQuery.where(qshoe.brand.eq("Nike"));
    }

    @Override
    public long count(Query<Shoe> query) {
        return 0;
    }

    public JPAQuery<Shoe> sort(Query<Shoe> query, JPAQuery<Shoe> jpaQuery, PathBuilder<Shoe> path) {
        if (query.getSorter() != null && StringUtils.isNotBlank(query.getSorter().getProperty())) {
            if (query.getSorter().isDesc()) {
                jpaQuery.orderBy(path.getString(query.getSorter().getProperty()).desc());
            } else {
                jpaQuery.orderBy(path.getString(query.getSorter().getProperty()).asc());
            }
        } else {
            jpaQuery.orderBy(path.getString("id").asc());
        }

        return jpaQuery;
    }

    public JPAQuery<Shoe> page(JPAQuery<Shoe> jpaQuery, Query<Shoe> query) {
        return jpaQuery.offset(((query.getPage() - 1L) * query.getLimit()));
    }

    public JPAQuery<Shoe> getLimit(JPAQuery<Shoe> jpaQuery, Query<Shoe> query) {
        return jpaQuery.limit(query.getLimit());
    }
}
