package controller;

import model.TODO;
import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import dao.TODODao;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TODOController {
    @Autowired
    private TODODao TODODao;

    @RequestMapping(value = "/add-TODO", method = RequestMethod.POST)
    public String addQuestion(@ModelAttribute("TODO") TODO TODO) {
        System.out.println("ADD TODO:" + TODO.getId() + " " + TODO.getName());
        TODODao.addTODO(TODO);
        return "redirect:/get-TODOs";
    }

    @RequestMapping(value = "/delete-TODO", method = RequestMethod.POST)
    public String deleteQuestion(@ModelAttribute("TODO") TODO TODO) {
        System.out.println("DELETE TODO:" + TODO.getId() + " " + TODO.getName());
        TODODao.deleteTODO(TODO);
        return "redirect:/get-TODOs";
    }

    @RequestMapping(value = "/get-TODOs", method = RequestMethod.GET)
    public String getTODOs(ModelMap map) {
        prepareModelMap(map, TODODao.getTODOs());
        return "index";
    }

    @RequestMapping(value = "/add-TASK", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("TASK") Task task) {
        System.out.println("ADD task:" + task.getTODOID() + " " + task.getName());
        TODODao.addTask(task);
        return "redirect:/get-TODOs";
    }

    @RequestMapping(value = "/delete-TASK", method = RequestMethod.POST)
    public String deleteTask(@RequestParam("taskname") String name, @RequestParam("todoid") int todoid) {
        System.out.println("DELETE task:" + name);
        TODODao.deleteTask(new Task(todoid, name));
        return "redirect:/get-TODOs";
    }

    private void prepareModelMap(ModelMap map, List<TODO> TODOs) {
        map.addAttribute("TODOs", TODOs);
        map.addAttribute("TODO", new TODO());
        map.addAttribute("TASK", new Task());
    }
}
