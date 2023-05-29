package likelion.springbootcillian.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//설정자 자동 생성 어노테이션, 멤버 변수에 값을 설정 가능
@Setter
//파라미터가 없는 기본 생성자 생성해주는 어노테이션
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "item")
    //ArrayList 클래스는 초기화된 빈 리스트로 orderItem 필드를 초기화
    private List<OrderItem> orderItem = new ArrayList<>();

    private String brand;
    private String name;
    private Integer price;
    private Integer stock;

    //비즈니스 로직
    @Comment("재고 추가")
    public void addStock(int quantity){
        this.stock += quantity;
    }

    @Comment("재고 감소")
    public void removeStock(int stockQuantity){
        //재고 수량 계산
        int restStock = this.stock - stockQuantity;
        //재고 부족한 경우 조건문
        if (restStock < 0){
            throw new IllegalStateException("need more stock");
        }
        //stock 필드를 restStock 값으로 재고 감소량 업데이트
        this.stock = restStock;
    }



}