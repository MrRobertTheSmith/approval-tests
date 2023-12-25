package dev.atrobertsmith.approvaltests.ui;

import dev.atrobertsmith.approvaltests.domain.IBookDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final IBookDO bookDO;

    public HomeController(IBookDO bookDO) {
        this.bookDO = bookDO;
    }

    @GetMapping("/")
    String get(Model model) {
        model.addAttribute("books", bookDO.fetchBooks());

        return "index";
    }
}
