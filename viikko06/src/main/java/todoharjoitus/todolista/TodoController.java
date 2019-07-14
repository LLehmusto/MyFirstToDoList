package todoharjoitus.todolista;


import todoharjoitus.todolista.ToDo;
import todoharjoitus.todolista.dao.ToDoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/todot")

public class TodoController {
    private ToDoDao dao;

    @Autowired
    public TodoController(@Qualifier("jdbc") ToDoDao dao) {
        this.dao = dao;
    }

    @GetMapping("")
    public List<ToDo> palautaKaikki() {
        List<ToDo> kaikki = dao.palautaKaikki();
        return kaikki;
    }

    @PostMapping("")
    public ResponseEntity<?> luoUuusi(@RequestBody ToDo uusi) {
        int id = dao.lisaaTehtava(uusi);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(uusi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> poista(@PathVariable (name = "id") int id) {
        ToDo poistettu = dao.poista(id);
        if (poistettu != null)
            return ResponseEntity.ok(poistettu);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(String.format("Id %d ei ole olemassa: ei poistettu", id));
    }

}