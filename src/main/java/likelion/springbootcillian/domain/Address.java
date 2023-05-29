package likelion.springbootcillian.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
//@Data 어노테이션은 @Getter, @Setter, @RequiredArgConstructor, @ToString, @EqualsAndHashCode를 한꺼번에 설정해주는 어노테이션
@Data
//@AllArgConstructor 어노테이션은 모든 필드 값을 파라미터로 받는 생성자를 만들어줌.
@AllArgsConstructor
//@NoArgConstructor 어노테이션은 파라미터가 없는 기본 생성자를 생성해줌.
@NoArgsConstructor
public class Address {
    private String city;
    private String state;
    private String street;
    private String zipcode;
}
