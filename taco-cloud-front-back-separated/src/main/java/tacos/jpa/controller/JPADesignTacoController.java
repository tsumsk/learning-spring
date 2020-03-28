package tacos.jpa.controller;

import java.util.Optional;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.jpa.data.Taco;
import tacos.jpa.repository.TacoRepository;

@Slf4j
@RestController
@RequestMapping(path = "/design", produces = "application/json")
public class JPADesignTacoController {

    private TacoRepository tacoRepository;

    @Autowired
    public JPADesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco processDesign(@RequestBody @Valid Taco taco) {

        log.info("Processing design: " + taco);
        return tacoRepository.save(taco);
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());

        return tacoRepository.findAll(pageable).getContent();
    }

    /*
    @GetMapping("/{id}")
    public Taco tacoById(@PathVariable("id") Long id) {
        Optional<Taco> result = tacoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
     */

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> result = tacoRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
