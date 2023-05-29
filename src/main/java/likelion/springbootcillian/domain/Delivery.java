package likelion.springbootcillian.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

import static lombok.AccessLevel.PROTECTED;

@Entity
//무분별한 객체 생성에 대해 한번 더 체크할 수 있다.
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    //Order 엔티티에 있는 delivery 필드와 OneToOne 매핑
    @OneToOne(mappedBy = "delivery")
    private Order order;

    //enum 이름값을 DB에 저장
    @Enumerated(STRING)
    private DeliveryStatus deliveryStatus;

    private String city;
    private String state;
    private String street;
    private String zipcode;



        //주어진 주문 정보와 배송지 관련 정보를 사용하여 새로운 Delivery 객체를 생성하고 반환
        public static Delivery createDelivery(Order order, String city, String state, String street, String zipcode) {
            Delivery delivery = new Delivery();
            delivery.order = order;
            delivery.deliveryStatus = DeliveryStatus.ESTABLISHED;
            delivery.city = city;
            delivery.state = state;
            delivery.street = street;
            delivery.zipcode = zipcode;
            return delivery;
        }

        //배송 상태 표현
        public enum DeliveryStatus {
            ESTABLISHED, PROGRESS, DONE
        }

}
