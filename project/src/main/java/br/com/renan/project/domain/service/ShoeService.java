package br.com.renan.project.domain.service;

import br.com.renan.project.domain.entity.Shoe;
import br.com.renan.project.domain.exception.EntityNotFoundException;
import br.com.renan.project.domain.query.Query;
import br.com.renan.project.domain.query.Sorter;
import br.com.renan.project.domain.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Renan.
 */
@Service
public class ShoeService implements Serializable {

    @Autowired
    private ShoeRepository shoeRepository;

    public List<Shoe> find(String sortProperty, Sorter.Direction sortDirection, Long page) {
        // Constrói a query para a entidade Shoe
        final Query<Shoe> query = Query.<Shoe>builder()
                .sort(Sorter.<Shoe>by(sortProperty)
                        .direction(sortDirection))
                .page(page).build();

        return shoeRepository.find(query);
    }

    public Shoe findById(Long id) {
        return shoeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Shoe field) {
        shoeRepository.create(field);
    }

    @Transactional
    public void update(Shoe field) {
        shoeRepository.update(field);
    }

    @Transactional
    public void remove(Long id) {
        shoeRepository.removeById(id);
    }
}
