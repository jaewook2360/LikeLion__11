package likelion.springbootcillian.controller;

import likelion.springbootcillian.domain.Item;
import likelion.springbootcillian.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
//@RequiredArgsConstructor : final이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@Controller
@RequiredArgsConstructor
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("itemForm", new Item());
        return "items/createItemForm";
    }

    @PostMapping("new")
    public String create(Item item) {
        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("")
    public String findAll(Model model) {
        List<Item> itemList = itemService.findAll();
        model.addAttribute("itemList", itemList);
        return "items/itemList";
    }

    @GetMapping("{id}/update")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("itemFormUpdate", itemService.findById(id));
        return "items/updateItemForm";
    }

    @PostMapping("update")
    public String update(Item item) {
        itemService.update(item.getId(), item);
        return "redirect:/items";
    }
}
