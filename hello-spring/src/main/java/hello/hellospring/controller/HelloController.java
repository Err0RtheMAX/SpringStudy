package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // url이 hello 경로 가리켜져 있는지 확인하는 Mapping 작업을 한다.
    public String hello(Model model) { // 스프링이 미리 모델을 만들어서 hello 메소드에 대입된다.
        model.addAttribute("data", "hello?!"); // hello?! 반환 출력 hello.html의 ${data} 부분에 반환된다.
        return "hello"; // hello.html을 찾아서 렌더링하기 위해 반환된다.
    }



    @GetMapping("hello-mvc") // url이 hello-mvc 경로로 가리켜져 있는 지 확인하는 Mapping 작업을 한다.
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        // required false로 하면 넘기는 매개변수가 없어도 동작한다.
        //@RequiestParam으로 name 매개변수를 요청하고 받은요청 변수를 model에다가 쓰고, hello-template.html로 반환시킨다.
        model.addAttribute("name", name);
        // @RequestParam에서 받아온 name 값을 hello-template.html의 ${name}("name")으로 넘긴다.
        return "hello-template";
    }



    @GetMapping("hello-string") // url이 hello-spring 경로로 가리켜져 있는 지 확인하는 Mapping 작업을 한다.
    @ResponseBody // Response
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // hello spring html 문서태그 없이 소스 검사를 하면 문자열만 있다.
    }



    @GetMapping("hello-api") // url이 hello-api 경로로 가리켜져 있는 지 확인한다.
    @ResponseBody // 있으면 url body에 대해 반응하기 위해 resposebody 사용
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // Hello 클래스를 이용하여 hello 인스턴스 새롭게 생성
        hello.setName(name); // hello 인스턴스의 setName 메소드에다가 @ResponseBody로 url에서 가져온 name 매개변수를 대입
        return hello; // 반환
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
