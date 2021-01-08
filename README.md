# GlobalCodingResearchAndroid
2021 Field Practice
21_01_06_WED
-------------

- 4개 xml을 만들어 1번 화면, 2번 화면, 3번 화면, 4번 화면의 특징

(1) 1번 화면(Toast, Change Text View) : Toast, Intent, 2 Buttons(Toast + Change Text View, Next View), Text View 
              
              
(2) 2번 화면(Sub Layout View) : 2 Buttons(Home, Login View), Text View
              
              
(3) 3번 화면(Login View) : Put Extra, Edit Text(ID, PSW), 2 Buttons(LOGIN, Home)
              
              
(4) 4번 화면(Check ID, PSW View) : Get Extra, View Text(ID, PSW), 1 Button(Home)


21_01_07_THU
-------------
              
- Page 이름 변경 및 추가 : Main, Sub, Login, Register, Check_ID_PSW
- 각 Page 이름 표시

(1) Main 화면 : sub 페이지 이동 버튼 , TextView 바꾸기 및 Toast 출력 버튼

(2) Sub 화면 : Home 페이지 이동 버튼, Login 페이지 이동 버튼, Register 페이지 이동 버튼

(3) Login 화면 : Home 페이지 이동 버튼, EditText로 ID, Password 입력, Check_ID_Password 페이지 이동 버튼

(4) Register 화면 : Home 페이지 이동 버튼, EditText로 ID, Password, Name, Mail 입력, Login 페이지 이동 및 이동 후 Toast 출력 버튼 
(향후 호스팅, 테이블, SQL, PHP 등을 사용해 DB에 저장 할 예정)

(5) Check_ID_Password 화면 : Home 페이지 이동 버튼, ID, Password를 TextView에 출력, 

           
21_01_08_FRI
-------------
 
 - myapplication 이름 변경 : RegisterActivity, LoginActivity
 - 파일 추가 : ValidateRequest, RegisterRequest, LoginRequest
 - 기능 추가 : Activity 들어간 파일에 Volley 라이브러리로 php 서버와 통신을 가능하게 만들었다.
              버튼 뒤 Listener를 연결한다.
 
 (1) : ValidateRequest : RegisterActivity에 ID 중복 체크가 있는데 이를 위해 만든 class.
 
 (2) : RegisterRequest : URL에 POST 방식으로 파라미터들을 전송하는 역할을 수행한다.
                          회원가입 정보를 PHP 서버에보내서 데이터베이스에 저장시키게 한다.
 (3) : LoginRequest : 위와 같은 역할을 한다. 
                          데이터베이스의 정보와 비교해 조건을 결정한다.
 
__* 실행 결과__
 
 ![isSBSettingEnabled false](https://user-images.githubusercontent.com/49744580/104019758-78cad080-51ff-11eb-91dd-43f8bbf80a5f.PNG)

연결이 제대로 되지 않는 모습을 확인할 수 있다. 
이유는 UI Thread로 코드를 작성했기에 통신상 오래 걸린다. 
누구는 안된다고 하는데 이걸 AsyncTask로 doInBackground에서 네트워크에 연결해야 진행이 된다고 누가 댓글을 알려줬다. 
다음 시간에는 AsyncTask로 고쳐서 작성해봐야겠다. 
              
              
21_01_11_MON
-------------   
              
21_01_12_TUE
-------------

21_01_13_WED
-------------
              
21_01_14_THU
-------------
              
21_01_15_FRI
-------------   
              
21_01_18_MON
-------------

21_01_19_TUE
-------------
              
21_01_20_WED
-------------
              
21_01_21_THU
-------------   
              
21_01_22_FRI
-------------
