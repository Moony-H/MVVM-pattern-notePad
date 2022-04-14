package com.moony.mvvmstudyrecordapp.viewModels


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moony.mvvmstudyrecordapp.R
import com.moony.mvvmstudyrecordapp.Tag.FragmentTags
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject
@HiltViewModel
class NavigationViewModel @Inject constructor():ViewModel() {
    private val _currentPageTag=MutableLiveData(FragmentTags.PAGE_SCHEDULE)
    val currentPageTag: LiveData<FragmentTags>
        get()=_currentPageTag




    fun setCurrentPage(menuItemId:Int):Boolean{
        val pageTag=getPageTag(menuItemId)
        changeCurrentPage(pageTag)
        return true

    }
    private fun getPageTag(page_num:Int):FragmentTags{
        return when(page_num){
            R.id.item_page_schedule-> FragmentTags.PAGE_SCHEDULE
            R.id.item_page_subject-> FragmentTags.PAGE_SUBJECT_LIST
            R.id.item_page_record-> FragmentTags.PAGE_RECORD_LIST
            else -> throw IllegalArgumentException("bottom navi view not found: fragment tag")
        }

    }

    private fun changeCurrentPage(pageType: FragmentTags) {
        if (_currentPageTag.value == pageType)
            return

        _currentPageTag.value = pageType
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("on","cleared")
    }

}