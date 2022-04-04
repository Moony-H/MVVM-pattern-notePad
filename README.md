# MVVM-Study-Record-App

* 이 Repogitory는 MVVM 패턴을 연습하기 위한 연습용 Project 입니다.



<br/>

<br/>

## 프로젝트 설명

<br/>

공부 했던 기록을 달력에 적으며 기록할 수 있는 앱 입니다.

과목 별로 어느 날짜에 얼마 만큼 공부 했는 지 볼 수 있고, 날짜 별로 무엇을 공부 했었는지 볼수 있는 앱 입니다.

<br/>

## Specification

| Tech Stack           | Contents                                                     |
| -------------------- | ------------------------------------------------------------ |
| Architecture         | MVVM                                                         |
| Jetpack              | DataBinding, ViewBinding, LiveData, ViewModel, lifeCycle, Room |
| Dependency Injection | dagger-Hilt                                                  |
| Design Pattern       | Repository Pattern                                           |
| Async                | Coroutine                                                    |

<br/>

<br/>

## 핵심 기능 구현

기술을 어떻게 사용했는지, 어떤 방법으로 구현했는지에 대한 설명은 아래의 링크에서 확인하실 수 있습니다.

BottomNavigationView와 Fragment 관리

<br/>

DataBinding

<br/>

전체 데이터 흐름(Coroutine Flow, livedata, Room)

<br/>

Coroutine

<br/>

Dagger-Hilt



## 앱의 기능

<br/>

<br/>

### 1. 네비게이션

네비게이션으로 페이지를 전환할 수 있습니다.

전환된 페이지의 상태는 그대로 남습니다.

<br/>

<br/>

### 2. 과목 등록

과목을 등록할 수 있습니다.

과목 이름, 중요도, 진행도, 구분 지을 색, 과목에 대한 설명을 적어 등록할 수 있습니다.

<br/>

<br/>

### 3.  과목 상세

중요도, 진행률, 메모 등 과목의 상세한 정보를 볼 수 있습니다.

또한 과목을 공부했던 기록도 함께 볼 수 있습니다.

<br/>

<br/>



### 4.  공부 기록

과목을 골라 오늘 공부했던 기록을 남길 수 있습니다.

공부 기록은 핸드폰의 날짜를 기준으로 오늘 날짜로만 기록됩니다.

오늘 진도가 몇 페이지, 혹은 몇 강의까지 들었는지 기록할 수 있으며

공부 시간은 몇시부터 몇시까지,

무엇을 공부했는지 짧게 남길 수 있습니다.

<br/>

<br/>

### 5. 이전 기록 보기

달력에서 날짜를 눌러 어떤 공부를 하였는지 볼 수 있습니다.

오늘 날짜의 공부 기록은 등록한 즉시 반영됩니다.

