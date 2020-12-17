package me.deborah.usingjpa1.controller;

import lombok.RequiredArgsConstructor;
import me.deborah.usingjpa1.domain.item.Book;
import me.deborah.usingjpa1.domain.item.Item;
import me.deborah.usingjpa1.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    // 상품 등록
    // 상품 등록 폼에서 데이터를 입력하고 Submit 버튼을 클릭하면
    // /items/new 를 POST 방식으로 요청 상품 저장이 끝나면 상품 목록 화면( redirect:/items )으로 리다이렉트

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
    // model에 담아둔 상품 목록인 items 를 꺼내서 상품 정보를 출력

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        // 아래처럼 캐스팅 해서 쓰면 안됨. 예제임
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
        // 준영속 엔티티?
        // 영속성 컨텍스트가 더는 관리하지 않는 엔티티를 말한다.
        // 여기서는 itemService.saveItem(book) 에서 수정을 시도하는 Book 객체다.
        // Book 객체는 이미 DB 에 한번 저장되어서 식별자가 존재한다.
        // 이렇게 임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면 준 영속 엔티티로 볼 수 있다.

        // 문제 ? JPA가 관리하지 않기 때문에 아무리 값을 바꿔도 업데이트가 안됨.
        // 준영속 엔티티를 수정하는 2가지 방법 변경 감지 기능 사용
        // 병합( merge ) 사용

        // book.setId(form.getId());
        // book.setName(form.getName());
        // book.setPrice(form.getPrice());
        // book.setStockQuantity(form.getStockQuantity());
        // book.setAuthor(form.getAuthor());
        // book.setIsbn(form.getIsbn());

        // itemService.saveItem(book); // 결과적으로 itemRepository에서 merge 호출

        // - 가장 좋은 해결 방법
        // 엔티티를 변경할 때는 항상 변경 감지를 사용하세요
        // 컨트롤러에서 어설프게 엔티티를 생성하지 마세요.
        // 트랜잭션이 있는 서비스 계층에 식별자( id )와 변경할 데이터를 명확하게 전달하세요.(파라미터 or 넘겨줘야하는 파라미터가 많으면 UpdateItemDataTransObject)
        // 트랜잭션이 있는 서비스 계층에서 영속 상태의 엔티티를 조회하고, 엔티티의 데이터를 직접 변경하세요.
        // 트랜잭션 커밋 시점에 변경 감지가 실행됩니다.

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
}
