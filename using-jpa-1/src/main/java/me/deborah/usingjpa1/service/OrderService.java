package me.deborah.usingjpa1.service;

import lombok.RequiredArgsConstructor;
import me.deborah.usingjpa1.domain.Delivery;
import me.deborah.usingjpa1.domain.Member;
import me.deborah.usingjpa1.domain.Order;
import me.deborah.usingjpa1.domain.OrderItem;
import me.deborah.usingjpa1.domain.item.Item;
import me.deborah.usingjpa1.repository.ItemRepository;
import me.deborah.usingjpa1.repository.MemberRepository;
import me.deborah.usingjpa1.repository.OrderRepository;
import me.deborah.usingjpa1.repository.OrderSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 주문
     */
    // 주문하는 회원 식별자, 상품 식별자, 주문 수량 정보를 받아서 실제 주문 엔티티를 생성한 후 저장한다.
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    // 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }

    //  참고: 주문 서비스의 주문과 주문 취소 메서드를 보면 비즈니스 로직 대부분이 엔티티에 있다.
    //  서비스 계층 은 단순히 엔티티에 필요한 요청을 위임하는 역할을 한다.
    //  이처럼 엔티티가 비즈니스 로직을 가지고 객체 지 향의 특성을 적극 활용하는 것을
    //  도메인 모델 패턴(http://martinfowler.com/eaaCatalog/ domainModel.html)이라 한다.
    //  반대로 엔티티에는 비즈니스 로직이 거의 없고 서비스 계층에서 대부분 의 비즈니스 로직을 처리하는 것을
    //  트랜잭션 스크립트 패턴(http://martinfowler.com/eaaCatalog/ transactionScript.html)이라 한다
}
