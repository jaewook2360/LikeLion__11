package likelion.springbootcillian.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

//날짜, 시간 정보
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
@Entity
//엔티티와 매핑할 테이블의 이름 지정
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = PROTECTED)

public class Order {
    //@Id 특정속성을 기본키로 설정하는 어노테이션
    @Id
    @GeneratedValue
    private Long id;
    // LAZY fetch Type(지연로딩) : 엔티티 조회시에 바로 가져오지 않고, 연관관계에 있는 엔티티를 참조할 때 그때 가지고 온다.
    @ManyToOne(fetch = LAZY)
    //@JoinColum : 외래 키 맵핑할 때 사용, name 속성에는 매핑할 외래 키 컬럼명을 지정
    @JoinColumn(name = "member_id")
    private Member member;
    //cascade = CascadeType.ALL는 Order 엔티티의 변경이 Delivery 엔티티에도 전파되도록 설정
    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    private LocalDateTime orderDate;
    //자바 enum타입을 매핑할 때 사용
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void setMember(Member member){
        this.member = member;
        //member 객체의 getOrderList() 메서드를 호출하여 현재 객체(this)를 OrderList에 추가
        member.getOrderList().add(this);
    }

    public static Order createOrder(Member member, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.orderDate = LocalDateTime.now();
        order.orderStatus = OrderStatus.ORDERED;
        order.delivery = Delivery.createDelivery(order, member.getAddress().getCity(),
                member.getAddress().getState(),
                member.getAddress().getStreet(),
                member.getAddress().getZipcode());
        for (OrderItem orderItem : orderItems) {
            order.orderItemList.add(orderItem);
            orderItem.setOrder(order);
        }
        return order;
    }

    //주문 취소
    public void cancel() {
        //배송 상태 확인 조건문
        if (delivery.getDeliveryStatus() == Delivery.DeliveryStatus.DONE) {
            throw new IllegalStateException("배송 완료");
        }
        //주문 상태 취소로 변경
        this.orderStatus = OrderStatus.CANCELED;
        //주문 목록을 읽으면서 cancal메서드 호출
        for (OrderItem orderItem : orderItemList) {
            orderItem.cancel();
        }
    }

    //주문의 총 가격 계산 후 반환
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
