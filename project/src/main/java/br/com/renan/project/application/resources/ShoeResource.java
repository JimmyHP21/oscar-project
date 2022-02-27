package br.com.renan.project.application.resources;

import br.com.renan.project.domain.entity.Shoe;
import br.com.renan.project.domain.query.Sorter;
import br.com.renan.project.domain.service.ShoeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/shoe")
@Api(value = "Api padrao para operacoes da api do Calcado")
public class ShoeResource {
    
    @Autowired
    private ShoeService shoeService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "Listagem de todos os calçados")
    private ResponseEntity<List<Shoe>> findAllShoes(@RequestParam(value = "sortBy", required = false) String sortProperty,
                                                      @RequestParam(value = "sortDirection", required = false) Sorter.Direction sortDirection,
                                                      @RequestParam(value = "page", required = false) Long page){

        return ResponseEntity.ok(shoeService.find(sortProperty, sortDirection, page));
    }

    @GetMapping(value = "/{id}/details")
    @ApiOperation(value = "Retorno de um calçado")
    private ResponseEntity<Shoe> findById(@PathVariable(value = "id") Long id ) {
        return ResponseEntity.ok(shoeService.findById(id));
    }

    @ApiOperation(value = "Criação de um calçado")
    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createField(@RequestBody Shoe shoe) {
        shoeService.create(shoe);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(shoe.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Atualização de um calçado")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateForm(@RequestBody Shoe shoe) {
        shoeService.update(shoe);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete de um calçado")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable("id") Long id) {
        shoeService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
