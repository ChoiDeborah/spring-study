# REST API MEMO
### Event 생성 API 구현  

스프링 부트 슬라이스 테스트

- @WebMvcTest  
    - MockMvc 빈을 자동 설정 해준다. 따라서 그냥 가져와서 쓰면 됨.  
    - 웹 관련 빈만 등록해 준다. (슬라이스)

MockMvc  
  - 스프링 MVC 테스트 핵심 클래스  
  - 웹 서버를 띄우지 않고도 스프링 MVC (DispatcherServlet)가 요청을 처리하는 과정을 확인할 수 있기 때문에 컨트롤러 테스트용으로 자주 쓰임
    