package dev.atrobertsmith.approvaltests.ui;

import dev.atrobertsmith.approvaltests.domain.IBookDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final IBookDO bookDO;

    public HomeController(IBookDO bookDO) {
        this.bookDO = bookDO;
    }

    @GetMapping("/")
    String get(Model model) {
        var books = bookDO.fetchBooks();
        LOGGER.info("Loading home with: {}", books);

        model.addAttribute("books", books);
        return "index";
    }
}
