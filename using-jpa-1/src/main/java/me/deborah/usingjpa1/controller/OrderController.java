package me.deborah.usingjpa1.controller;

import lombok.RequiredArgsConstructor;
import me.deborah.usingjpa1.domain.Member;
import me.deborah.usingjpa1.domain.item.Item;
import me.deborah.usingjpa1.service.ItemService;
import me.deborah.usingjpa1.service.MemberService;
import me.deborah.usingjpa1.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        // 핵심 비지니스 로직이 있는 경우에는 컨트롤러에서는 값만 넘기고,
        // 트렌젝션이 있는 서비스에서 처리하는게 나음
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
}
