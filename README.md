# MVVM-pattern-notePad





* 이 Repogitory는 MVVM 패턴을 연습하기 위한 연습용 Project 입니다.
* **개발 중인 미완성 App 입니다.**





## 프로젝트 설명



공부 했던 기록을 달력에 적으며 기록할 수 있는 앱 입니다.

과목 별로 어느 날짜에 얼마 만큼 공부 했는 지 볼 수 있고, 날짜 별로 무엇을 공부 했었는지 볼수 있는 앱 입니다.



## Specification

| Tech Stack           | Contents                                                     |
| -------------------- | ------------------------------------------------------------ |
| Architecture         | MVVM                                                         |
| Jetpack              | DataBinding, ViewBinding, LiveData, ViewModel, lifeCycle, Room |
| Dependency Injection | dagger-Hilt **(추가 예정)**                                  |
| Design Pattern       | Repository Pattern                                           |
| Async                | Coroutine                                                    |



## 규칙



1. View에 속하는 Activity, Fragment, xml은 그저 View Model을 **관찰** 합니다.

   관찰에 사용된 것은 **Live data**, **Data Binding** 입니다.

   

   
   
   *fragment_subject_add.xml (Data Binding 사용 예시)  
   
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
   
   
   
   
   
2. 

