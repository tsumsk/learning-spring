package restful.ws.example;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import restful.ws.example.entities.Singer;

@Controller
@RequestMapping(value = "/singer")
public class SingerController {
  @Autowired
  private SingerService singerService;

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public List<Singer> listData() {
    return singerService.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Singer findSingerById(@PathVariable Long id) {
    return singerService.findById(id);
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody
  public Singer create(@RequestBody Singer singer) {
    singerService.save(singer);
    return singer;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public void update(@RequestBody Singer singer, @PathVariable Long id) {
    singerService.save(singer);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public void delete(@PathVariable Long id) {
    Singer singer = singerService.findById(id);
    singerService.delete(singer);
  }

}
