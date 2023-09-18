# 안드로이드 수업
## 개발 환경
### Android Studio 설치
https://developer.android.com/studio  

### app
버튼 클릭시 Toast가 나오는 기능
```java
btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"aaa",Toast.LENGTH_LONG).show();
            }
        });
```
### myapp02
myapp02
안드로이드 Toast
안드로이드 intent



myapp04
binding
weight
layout
chronometer//스탑워치
calender//달력
timepicker//시간

myapp05
chronometer//스탑워치
tab
webview 
//인터넷 설정
	<uses-permission android:name="android.permission.INTERNET"/>
	android:usesCleartextTraffic="true"
//
menu
contextmenu
dialog
dialog,toast (뷰)

myapp06
intent
ratingBar
main - intent
main2 - ratingbar
main3 - ratingBar + 배열
main4 - main3을 bindng
main5 - 양방향 intent
main6 - 양방향 intent
main7 - 양방향 intent 객체로

myapp07
main - 양방향 intent (launch)
main2 - myapp06.main7을 launch방식으로
main3 - 암시적 intent
main4 - 엑티비티 생명주기
main5 - listview
main6 - listview 동적추가
main7 - gridview
