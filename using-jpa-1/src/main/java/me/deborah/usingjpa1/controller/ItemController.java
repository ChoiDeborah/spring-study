package me.deborah.usingjpa1.controller;

import lombok.RequiredArgsConstructor;
import me.deborah.usingjpa1.domain.item.Book;
import me.deborah.usingjpa1.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        // setter 제거 후 파라미터 받아서 만드는 매소드 가 바람직함.
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor((form.getAuthor()));
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }
}
