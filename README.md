# GlobalCodingResearchAndroid
__2021 PNU Field Practice about Smart Farm Android Application Development__

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
 
 (1) ValidateRequest : RegisterActivity에 ID 중복 체크가 있는데 이를 위해 만든 class.
 
 (2) RegisterRequest : URL에 POST 방식으로 파라미터들을 전송하는 역할을 수행한다.
                          회원가입 정보를 PHP 서버에보내서 데이터베이스에 저장시키게 한다.
 (3) LoginRequest : 위와 같은 역할을 한다. 
                          데이터베이스의 정보와 비교해 조건을 결정한다.
 
__* 실행 결과__
 
 ![isSBSettingEnabled false](https://user-images.githubusercontent.com/49744580/104019758-78cad080-51ff-11eb-91dd-43f8bbf80a5f.PNG)

연결이 제대로 되지 않는 모습을 확인할 수 있다. 
이유는 UI Thread로 코드를 작성했기에 통신상 오래 걸린다. 
누구는 안된다고 하는데 이걸 AsyncTask로 doInBackground에서 네트워크에 연결해야 진행이 된다고 누가 댓글을 알려줬다. 
다음 시간에는 AsyncTask로 고쳐서 작성해봐야겠다. 
              
              
21_01_11_MON
-------------   

 - 파일 추가 : BlueTooth, 
 - 기능 추가 : MainActivity에 BlueTooth로 이동하는 기능을 추가했다. 
 - 방향 수정 : 로그인을 하지 않기로 함. php와 SQL, 서버 관리 등 다른 언어를 배워야 하기 때문에 비효율적이라고 판단.
 
 (1) BlueTooth : 블루투스 연결 조작 및 아두이노와 연결된 센서의 값을 받아오는 class. 스마트 팜에 사용되는 센서 "온습도센서", "조도센서", "수분센서", "가스센서", 총 5개의 칸을 만들어 각각의 값을 넣을 예정이다. 
 
 (2) LoginActivity : EditText의 위치가 onCreate 밖에 있어서 제대로 call이 되지 않아 NullPointer Error가 발생했다. 위치를 변경 후 오류가 사라진 모습을 확인할 수 있었다. 
 
 (3) Register : 그래도 나 혼자 php를 사용해 회원등록 및 로그인 기능을 추가할 예정임. 다른 네트워크 처리 방식을 공부해야 할 듯하다. 

         
21_01_12_TUE
-------------

 - 파일 추가 : X
 - 기능 추가 : BlueTooth에 센서 값을 표시할 수 있는 TextView를 만들었다. 
 - 방향 수정 : X
 
 (1) BlueTooth : MainActivity에서 블루투스 연결이 잘 안되는 것을 발견했다. 블루투스 연결 버튼과 Text를 아두이노로 넘기는 버튼을 만들었지만 잘 안됐다. 
 
 (2) MainActivity(2)에서는 스마트폰과 블루투스간 통신이 똑바로 이루어 지고 있다.
 
 (3) 

MainActivity(2)로 블루투스 값이 Toast로 넘겨지는 현상이 발생했다. 예를들어 조도센서 값 : 234, 온도센서 값 : 16, 습도센서 값 : 25가 측정된다면, Toast에 (234,16,25)가 출력이 된다. 
원래 (234,16,25)을 String으로 받아 (,)를 Split해 3개의 배열에 넣은 뒤, arr[0], arr[1], arr[2] 값을 TextView에 나타내는 작업을 하려고 했다. 

뭐가 문제인지 한번 확인해봐야 할 듯 하다. 

21_01_13_WED
-------------
              
레고 제작으로 앱 개발은 쉽니다.
              
21_01_14_THU
-------------

MainActivity(2)의 화면... 잘 전달되고 잘 나오는 것을 확인할 수 있다. 

 <img src="/21_01_14/화면1.jpg" width="400" height="500">  <img src="/21_01_14/화면2.jpg" width="400" height="500">


허나 여전히 MainActivity의 화면에는 블루투스 연결이 안되는 것을 확인할 수 있다. 왜 그러지...

              
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
