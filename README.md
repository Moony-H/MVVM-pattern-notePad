# MVVM-pattern-notePad

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

## 규칙



이 App을 짜며, **스스로 정한 규칙들** 입니다. **MVVM 패턴**의 약속을 지키고, 모든 recource, 데이터 이름, 함수 이름들은 같은 규칙에 맞춰 짜여 있습니다.



1. View에 속하는 Activity, Fragment, xml은 그저 View Model을 **관찰** 합니다.

   관찰에 사용된 것은 **Live data**, **Data Binding** 입니다.

   

   
   
   *fragment\_subject\_add.xml (Data Binding 사용 예시)  
   
   https://github.com/Moony-H/MVVM-pattern-notePad/blob/master/app/src/main/res/layout/fragment_subject_add.xml
   
   ``` xml
   <layout
       xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto">
       <data>
           <variable
               name="viewModel"
               type="com.moony.mvvm_pattern_notepad.viewModels.SubjectAddFragmentViewModel" />
       </data>
       <androidx.constraintlayout.widget.ConstraintLayout
   
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           >
           <androidx.constraintlayout.widget.ConstraintLayout
               android:id="@+id/fragment_subject_add_sub_layout"
               android:layout_width="0dp"
               android:layout_height="0dp"
               app:layout_constraintLeft_toLeftOf="parent"
               app:layout_constraintRight_toRightOf="parent"
               app:layout_constraintTop_toTopOf="parent"
               app:layout_constraintBottom_toBottomOf="parent"
               android:layout_margin="@dimen/fragment_subject_detail_sub_layout_margin_all"
               android:background="@drawable/rectangle_round"
               android:backgroundTint="@{viewModel.currentValue.color_int}"
   
   
               >
               <EditText
                   android:id="@+id/fragment_subject_add_name"
                   android:layout_width="0dp"
                   android:layout_height="wrap_content"
                   app:layout_constraintTop_toTopOf="parent"
                   app:layout_constraintLeft_toLeftOf="parent"
                   app:layout_constraintRight_toLeftOf="@id/fragment_subject_add_save_button"
                   android:layout_marginTop="20dp"
                   android:layout_marginLeft="27dp"
                   android:layout_marginStart="27dp"
                   android:layout_marginRight="20dp"
                   android:layout_marginEnd="20dp"
                   android:hint="과목 이름"
                   android:textColorHint="@color/white_transparent"
                   android:textColor="@color/white"
                   android:textStyle="bold"
                   android:textSize="@dimen/fragment_subject_detail_and_name_text_size"
                   android:text="@={viewModel.currentValue.name}"
                   android:maxLines="1"
                    />
               <!--중략-->
               
       </androidx.constraintlayout.widget.ConstraintLayout>
   </layout>
   ```
   
   
   
   
   
   
   
   *SubjectAddViewModel.kt (Live Data 사용 예시)
   
   https://github.com/Moony-H/MVVM-pattern-notePad/blob/master/app/src/main/java/com/moony/mvvm_pattern_notepad/viewModels/SubjectAddFragmentViewModel.kt
   
   ``` kotlin
   class SubjectAddFragmentViewModel:ViewModel() {
       val currentValue= MutableLiveData<Subject>()
   
       init {
           val color=ApplicationContext.getApplicationContext().resources.getStringArray(R.array.colors)[0]
           val temp=Subject("",0.0F,"",color,Color.parseColor(color))
           currentValue.value=temp
   
       }
   
   
   }
   ```
   
   
   
   
   
   실행 화면
   
   
   
   ![dataBindingLiveData](https://user-images.githubusercontent.com/53536205/159197009-d6db8e83-3172-49b2-bf40-24d708860645.gif)
   
   
   
   
   
2. **View model**은 **Model(Room)**에게 데이터를 요청하고, **Model(Room)**은 **View model**에게 요청받은 데이터를 응답합니다.

    **View model**은 데이터를 가공하여 저장합니다.

   데이터를 가공하고 저장하는 것은 **jetpack**의 **Room**을 사용하였습니다.

   

   **View**에 Input이 들어오면 **View model**에게 **통보(Command)** 합니다.

   

   *SubjectAddViewModel.kt (**Room** 사용 예시)

   https://github.com/Moony-H/MVVM-pattern-notePad/blob/master/app/src/main/java/com/moony/mvvm_pattern_notepad/viewModels/SubjectAddFragmentViewModel.kt

   ``` kotlin
   class SubjectAddFragmentViewModel:ViewModel() {
       val currentValue= MutableLiveData<Subject>()
   
       init {
           val color=ApplicationContext.getApplicationContext().resources.getStringArray(R.array.colors)[0]
           val temp=Subject("",0.0F,"",color,Color.parseColor(color))
           currentValue.value=temp
   
       }
   
       fun insertSubject(){
           CoroutineScope(Dispatchers.IO).launch {
               val db=SubjectDataBase.getInstance(ApplicationContext.getApplicationContext())!!
               currentValue.value?.let {
                   Log.d("not","null")
                   db.subjectDao().insert(it)
               }?:run{
                   Log.d("is","null")
               }
   
           }
       }
   
       fun getAll(){
           CoroutineScope(Dispatchers.IO).launch{
               val db=SubjectDataBase.getInstance(ApplicationContext.getApplicationContext())!!
               val temp=db.subjectDao().getAll()
               Log.d("insert Complete","${temp.size}")
           }
       }
   
   
   }
   ```

   

3.  xml 파일의 규칙은 아래와 같습니다.

   

   

   Activity의 이름은 activity\_이름\_사용처.xml 입니다. 

   

   Fragment의 이름은 fragment_이름\_사용처.xml 입니다. 

   

   

   그 밖의 여러 곳에서 활용 가능한 layout은 source로 시작합니다.

   특히 ListView 계열의 View는 source\_item\_사용처.xml 입니다.

   

   

   view의 아이디는 layout종류\_layout이름\_사용처\_View종류.xml 입니다.

   예를 들어 **List Fragment의 뒤로가기 버튼**의 이름은 다음과 같습니다.

   

   ```xml
   <Button android:id="@+id/fragment_list_back_button"/>
   ```

   

   

   

   화면 크기에 따른 Layout의 비율 변화가 최대한 적게 하기 위해 **Constraint Layout**을 최대한 활용하고 layout_width, layout_height 를 0dp로 설정하여 뷰 간의 관계를 **제약**으로 묶어 둡니다.

   

   밑은 xml 파일 예시 입니다.

   

   *fragment\_subject\_detail

   https://github.com/Moony-H/MVVM-pattern-notePad/blob/master/app/src/main/res/layout/fragment_subject_detail.xml

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <layout
       xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto">
       <androidx.constraintlayout.widget.ConstraintLayout
           android:layout_width="match_parent"
           android:layout_height="match_parent"
           >
           <androidx.constraintlayout.widget.ConstraintLayout
               android:id="@+id/fragment_subject_detail_sub_layout"
               android:layout_width="0dp"
               android:layout_height="0dp"
               app:layout_constraintLeft_toLeftOf="parent"
               app:layout_constraintRight_toRightOf="parent"
               app:layout_constraintTop_toTopOf="parent"
               app:layout_constraintBottom_toBottomOf="parent"
               android:layout_margin="@dimen/fragment_subject_detail_sub_layout_margin_all"
               android:background="@drawable/rectangle_round"
               >
               <TextView
                   android:id="@+id/fragment_subject_detail_name"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   app:layout_constraintTop_toTopOf="parent"
                   app:layout_constraintLeft_toLeftOf="parent"
                   android:layout_marginTop="20dp"
                   android:layout_marginLeft="27dp"
                   android:layout_marginStart="27dp"
                   android:text="@string/text_dynamic_null"
                   android:textColor="@color/white"
                   android:textStyle="bold"
                   android:textSize="@dimen/fragment_subject_detail_and_name_text_size"
                    />
               <RatingBar
                   android:id="@+id/fragment_subject_detail_rating"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   app:layout_constraintTop_toBottomOf="@id/fragment_subject_detail_name"
                   app:layout_constraintLeft_toLeftOf="@id/fragment_subject_detail_name"
                   android:numStars="5"
                   android:rating="5"
                   style="@style/Widget.AppCompat.RatingBar.Indicator"
                   android:stepSize="0.5"
                   android:progressTint="@color/yellow"
                   android:secondaryProgressTint="@color/yellow"
                   android:isIndicator="true"
                   />
               <TextView
                   android:id="@+id/fragment_subject_detail_records_text_view"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   app:layout_constraintLeft_toLeftOf="@id/fragment_subject_detail_name"
                   app:layout_constraintTop_toBottomOf="@id/fragment_subject_detail_rating"
                   android:text="@string/fragment_subject_detail_records_text"
                   android:textColor="@color/white"
                   android:textStyle="bold"
                   android:textSize="25sp"
                   android:layout_marginTop="40dp"
                   />
               <androidx.recyclerview.widget.RecyclerView
                   android:id="@+id/fragment_subject_detail_records_recycler_view"
                   android:layout_width="0dp"
                   android:layout_height="0dp"
                   app:layout_constraintTop_toBottomOf="@id/fragment_subject_detail_records_text_view"
                   app:layout_constraintLeft_toLeftOf="@id/fragment_subject_detail_name"
                   app:layout_constraintRight_toRightOf="parent"
                   app:layout_constraintBottom_toTopOf="@id/fragment_subject_detail_memo_text_view"
                   android:layout_marginTop="10dp"
                   android:layout_marginRight="27dp"
                   android:layout_marginBottom="10dp"
                   android:layout_marginEnd="27dp" />
               <TextView
                   android:id="@+id/fragment_subject_detail_memo_text_view"
                   android:layout_width="wrap_content"
                   android:layout_height="wrap_content"
                   app:layout_constraintLeft_toLeftOf="@id/fragment_subject_detail_name"
                   app:layout_constraintBottom_toTopOf="@id/fragment_subject_detail_memo_edit_text"
                   android:layout_marginTop="10dp"
                   android:text="메모"
                   android:textStyle="bold"
                   android:textSize="25sp"
                   android:textColor="@color/white"
                   />
               <EditText
                   android:id="@+id/fragment_subject_detail_memo_edit_text"
                   android:layout_width="0dp"
                   android:layout_height="200dp"
   
                   app:layout_constraintLeft_toLeftOf="@id/fragment_subject_detail_name"
                   app:layout_constraintRight_toRightOf="@id/fragment_subject_detail_records_recycler_view"
                   app:layout_constraintBottom_toBottomOf="parent"
                   android:layout_marginTop="10dp"
                   android:layout_marginBottom="30dp"
                   android:background="@color/white"
                   />
   
           </androidx.constraintlayout.widget.ConstraintLayout>
   
   
       </androidx.constraintlayout.widget.ConstraintLayout>
   </layout>
   ```

   

