package com.example.springbootjpa.model.dto;

import com.example.springbootjpa.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
/*
* builder Pattern
1. 필요한 데이터만 설정할 수 있다.
    - 생성자 또는 정적 메소드와 비교하여 테스트용 객체를 생성할 때 용이하게 해준다.
2. 유연성을 확보할 수 있다.
    - 기존의 로직에는 변수가 1개만 추가되어도 모든 로직을 수정해주거나 생성자를 따로 추가해줘야 했다.
    - 빌더 패턴은 기존의 코드는 수정할 필요 X, 유연하게 객체의 값을 설정할 수 있도록 도와주기 때문
3. 가독성을 높일 수 있다.
    - 직관적으로 어떤 데이터에 어떤 값이 설정되는지 쉽게 파악하여 가독성을 높일 수 있다.
4. 변경 가능성을 최소화 할 수 있다.
    - 변경 가능성을 최소화하는 가장 좋은 방법은 변수를 final로 선언함으로써 불변성을 확보하는 것

객체를 생성하는 대부분의 경우에는 빌더 패턴을 적용하는 것이 좋다.
1. 객체의 생성을 라이브러리로 위임하는 경우
2. 변수의 개수가 2개 이하이며, 변경 가능성이 없는 경우
위의 두 경우는 빌더를 구현 할 필요가 없다.
 */

