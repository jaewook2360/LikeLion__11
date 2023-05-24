package likelion.springbootcillian.controller;

import likelion.springbootcillian.domain.Member;
import likelion.springbootcillian.service.MemberService;
import likelion.springbootcillian.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@Controller 어노테이션으로, 사용자의 요청이 진입하는 지점이며 요청에 따라 어떤 처리를 할지 결정을 Service에 넘겨주는 역할을 한다.
//@RequestMapping은 Controller에서 받은 요청을 특정 method와 매핑하기 위해 사용하는 어노테이션이다.
//MemberController라는 Class를 생성
@Controller
@RequestMapping("members")
public class MemberController {
    //private final을 선언한 변수를 사용하면 해당 필드, 메소드 별로 호출할 때마다 새로이 값을 할당
    private final MemberService memberService;

    //* 이 것은 생성자입니다.
    // * @Autowired라는 어노테이션은 MemberController 객체를 실행해야 할 때 필요한 의존성을 주입해달라고 선언하기 위해 명시하는 어노테이션이며, 생성자 주입 방식을 선언하고 있습니다.
    // * MemberController의 필드를 MemberService 타입으로 선언하였지만, 생성자 paramer에는 MemberServiceImpl이 주입되게 함으로써 느슨한 결합(Loosen Coupling)을 구현하였습니다.
    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberService = memberServiceImpl;
    }

    //Model 객체는 Controller에서 생성된 데이터를 View로 전달할 때 사용하는 객체다. 이를 전달하는 방법이 model.addAttribute()이다.
    //Model 객체에 new Member()를 value로, "memberForm"을 name으로 전달, "members/createMemberForm"의 형태로 반환, 경로는 members/new와 매핑됨.
    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new Member());
        return "members/createMemberForm";
    }

    //Member을 파라미터로 받은 후 memberService 객체의 save() 메소드를 호출하여 member값을 저장
    //redirect 오른쪽의 주소로 리다이렉트한다.
    @PostMapping("new")
    public String create(Member member) {
        memberService.save(member);
        return "redirect:/";
    }

    //memberList 변수안에 memberService.findAll() 메소드를 통해 DB에 있는 모든 값들을 리스트에 저장
    //model 객체에 모든 값들을 조회한 memberList 변수를 "memberList"에 담아줌.
    @GetMapping("")
    public String findAll(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "members/memberList";
    }
}
