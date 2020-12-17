package me.deborah.usingjpa1.service;

import lombok.RequiredArgsConstructor;
import me.deborah.usingjpa1.domain.item.Book;
import me.deborah.usingjpa1.domain.item.Item;
import me.deborah.usingjpa1.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // Id를 기반으로 실제 DB의 영속성 Entity를 찾아
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name); // 예제니 setter사용 안좋음
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        // - 변경 감지 기능 사용 (추천)
        // 트랜잭션 안에서 엔티티를 다시 조회,
        // 변경할 값 선택 트랜잭션 커밋 시점에 변경 감지(Dirty Checking) 이 동작해서
        // 데이터베이스에 UPDATE SQL 실행
        // (Transactional의해 커밋 됨. JPA는 flush 날림 (영속성인 애들 중에 변경된 애들 찾아서 업데이트 쿼리 날림))

        // setter 지우고 매소드를 사용해서 업데이트가 일어나는 것을 명시적으로 해주는 것이 좋음.
        // 값이 어디서 바뀌는지 모른다......!
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
